package edu.config;

import edu.model.scheduler.CallbackTask;
import edu.repository.entity.Order;
import edu.repository.entity.embeddable.Path;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;


@Configuration
public class Config {

    private final Random random = new Random();

    @Bean
    public Supplier<LocalDateTime> localDateSupplier() {
        return LocalDateTime::now;
    }

    @Bean
    public Timer timer() {
        return new Timer();
    }

    @Bean
    public Supplier<Order> orderSupplier(Supplier<Path> pathSupplier, Supplier<LocalDateTime> localDateSupplier) {
        return () -> new Order(pathSupplier.get(), random.nextInt(10), Timestamp.valueOf(localDateSupplier.get()));
    }

    @Bean
    public Function<Runnable, TimerTask> intervalTimerFunction() {
        return CallbackTask::new;
    }
}
