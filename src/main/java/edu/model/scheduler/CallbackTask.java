package edu.model.scheduler;

import java.util.TimerTask;


public final class CallbackTask extends TimerTask {

    private final Runnable callback;

    public CallbackTask(Runnable callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        callback.run();
    }
}
