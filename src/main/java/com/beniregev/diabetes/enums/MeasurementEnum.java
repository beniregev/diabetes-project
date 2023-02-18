package com.beniregev.diabetes.enums;

import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MeasurementEnum {
    MORNING_WAKEUP("wakeup", LocalTime.of(7, 0),"As You Wakeup"),
    MORNING_BEFORE_BREAKFAST("beforeBreakfast", LocalTime.of(7, 30), "Just Before Breakfast"),
    MORNING_AFTER_BREAKFAST_2HOURS("afterBreakfast", LocalTime.of (9, 30), "2 hours After Breakfast"),
    NOON_BEFORE_LUNCH("beforeLunch", LocalTime.of (13, 30), "Just Before Lunch"),
    NOON_AFTER_LUNCH_2HOURS("afterLunch", LocalTime.of (15, 30), "2 hours After Lunch"),
    EVENING_BEFORE_SUPPER("beforeSupper", LocalTime.of (18, 30), "Just Before Supper"),
    EVENING_AFTER_SUPPER_2HOURS("afterSupper", LocalTime.of (20, 30), "2 hours After Supper"),
    NIGHT_BEFORE_SLEEP("beforeSleep", LocalTime.of (22, 30), "Before Sleep"),
    OTHER("other", LocalTime.NOON, "Another measurement, not in the schedule/planning");

    private final String name;
    private final LocalTime defaultTime;
    private final String description;

    public static final Map<String, MeasurementEnum> mapValues = Stream.of(values())
            .collect( Collectors.toMap(k -> k.name.toLowerCase(), v -> v) );
    public static final Map<String, String> mapDescriptions = Stream.of(values())
            .collect( Collectors.toMap(k -> k.name.toLowerCase(), v -> v.description) );

    MeasurementEnum(final String name, final LocalTime defaultTime, final String description) {
        this.name = name;
        this.defaultTime = defaultTime;
        this.description = description;
    }

    public LocalTime getDefaultTime() {
        return defaultTime;
    }
}
