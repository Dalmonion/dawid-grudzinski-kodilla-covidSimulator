package com.kodilla.covid;

import java.text.DecimalFormat;

public class Stopwatch {

    private DecimalFormat df = new DecimalFormat("#.##");
    private double startTime = 0;
    private double secondsPassed = 0;
    private boolean isRunning = false;

    public void start() {
        this.startTime = System.currentTimeMillis();
        isRunning = true;
    }

    public String getDuration() {
        return (df.format((System.currentTimeMillis() - this.startTime) / 1000.0 + secondsPassed));
    }

    public void stop() {
        if (isRunning) {
            secondsPassed += ((System.currentTimeMillis() - this.startTime) / 1000.0);
            isRunning = false;
            System.out.println(secondsPassed);
        }
    }
}
