package com.beniregev.diabetes.enums;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MeasurementEnum {
    MORNING_WAKEUP("wakeup", "As You Wakeup"),
    MORNING_PRE_BREAKFAST("preBreakfast", "Just Before Breakfast"),
    MORNING_2HOURS_POST_BREAKFAST("postBreakfast", "2 hours After Breakfast"),
    NOON_PRE_LUNCH("preLunch", "Just Before Lunch"),
    NOON_2HOURS_POST_LUNCH("postLunch", "2 hours After Lunch"),
    EVENING_PRE_SUPPER("preSupper", "Just Before Supper"),
    EVENING_2HOURS_POST_SUPPER("postSupper", "2 hours After Supper"),
    NIGHT_PRE_SLEEP("preSleep", "Before Sleep"),
    OTHER("other", "Another measurement, not in the schedule/planning");

    private final String name;
    private final String description;

    public static final Map<String, MeasurementEnum> mapValues = Stream.of(values())
            .collect( Collectors.toMap(k -> k.name.toLowerCase(), v -> v) );
    public static final Map<String, String> mapDescriptions = Stream.of(values())
            .collect( Collectors.toMap(k -> k.name.toLowerCase(), v -> v.description) );

    MeasurementEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
