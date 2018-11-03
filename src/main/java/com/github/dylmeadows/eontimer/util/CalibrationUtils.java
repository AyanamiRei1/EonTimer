package com.github.dylmeadows.eontimer.util;

import com.github.dylmeadows.eontimer.model.config.Console;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings({"unused", "WeakerAccess"})
public class CalibrationUtils {

    public int convertToMillis(int delays, Console console) {
        return (int) Math.round(delays * console.getFrameRate());
    }

    public int convertToDelays(int millis, Console console) {
        return (int) Math.round(millis / console.getFrameRate());
    }

    public int createCalibration(int delay, int second, Console console) {
        int delayCalibration = delay - convertToDelays(second * 1000, console);
        return convertToMillis(delayCalibration, console);
    }
}
