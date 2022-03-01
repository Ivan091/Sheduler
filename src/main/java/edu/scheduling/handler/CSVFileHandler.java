package edu.scheduling.handler;

import edu.model.intensity.SchedulingIntensities;
import edu.model.order.Order;
import edu.service.OrderService;
import edu.service.PathService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;


@Slf4j
@Service
public final class CSVFileHandler implements BiConsumer<SchedulingIntensities, LocalDateTime> {

    @Autowired
    private PathService pathService;

    @Autowired
    private OrderService orderService;

    private String actualName;

    private boolean isCreated;

    @SneakyThrows
    void defineFileName() {
        actualName = generateFileName();
        try (var writer = new BufferedWriter(new FileWriter(actualName))) {
            writer.append(orderService.toCsvHeader());
            writer.newLine();
        }
        isCreated = true;
    }

    @Override
    @SneakyThrows
    public void accept(SchedulingIntensities singleOriginIntensities, LocalDateTime localDateTime) {
        synchronized (this) {
            if (!isCreated) {
                defineFileName();
            }
        }
        log.trace("Planned to {} ", localDateTime);
        var file = new File(actualName);
        try (var writer = new BufferedWriter(new FileWriter(file, true))) {
            var order = Order.of(pathService.generatePath(singleOriginIntensities), 1, localDateTime);
            writer.append(orderService.toCsv(order));
            writer.newLine();
        }
    }

    private String generateFileName() {
        var num = 1;
        var baseName = "data";
        var extension = ".csv";
        var name = baseName + extension;
        var file = new File(name);
        while (file.exists()) {
            name = baseName + (num++) + extension;
            file = new File(name);
        }
        return name;
    }
}
