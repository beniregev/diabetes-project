package com.beniregev.diabetes.resources;

import java.util.HashMap;
import java.util.Map;

public class InputResources {
    Map<Double, Integer> mapHbA1cToGlucose = null;
    Map<Integer, Double> mapGlucoseToHbA1c = null;

    public InputResources() {
        this.mapHbA1cToGlucose = populateHbA1cToGlucoseMap();
        this.mapGlucoseToHbA1c = populateGlucoseToHbA1cMap();
    }

    public InputResources(final Map<Double, Integer> mapHbA1cToGlucose,
                          final Map<Integer, Double> mapGlucoseToHbA1c) {
        this.mapHbA1cToGlucose = mapHbA1cToGlucose;
        this.mapGlucoseToHbA1c = mapGlucoseToHbA1c;
    }

    private Map<Double, Integer> populateHbA1cToGlucoseMap() {
        Map<Double, Integer> result = new HashMap<>();
        result.put(4.0, 68);    result.put(4.1, 71);    result.put(4.2, 74);    result.put(4.3, 77);
        result.put(4.4, 80);    result.put(4.5, 82);    result.put(4.6, 85);    result.put(4.7, 88);
        result.put(4.8, 91);    result.put(4.9, 94);    result.put(5.0, 97);    result.put(5.1, 100);
        result.put(5.2, 103);   result.put(5.3, 105);   result.put(5.4, 108);   result.put(5.5, 111);
        result.put(5.6, 114);   result.put(5.7, 117);   result.put(5.8, 120);   result.put(5.9, 123);
        result.put(6.0, 125);   result.put(6.1, 128);   result.put(6.2, 131);   result.put(6.3, 134);
        result.put(6.4, 137);   result.put(6.5, 140);   result.put(6.6, 143);   result.put(6.7, 146);
        result.put(6.8, 148);   result.put(6.9, 151);   result.put(7.0, 154);   result.put(7.1, 157);
        result.put(7.2, 160);   result.put(7.3, 163);   result.put(7.4, 166);   result.put(7.5, 169);
        result.put(7.6, 171);   result.put(7.7, 174);   result.put(7.8, 177);   result.put(7.9, 180);
        result.put(8.0, 183);   result.put(8.1, 186);   result.put(8.2, 189);   result.put(8.3, 192);
        result.put(8.4, 194);   result.put(8.5, 197);   result.put(8.6, 200);   result.put(8.7, 203);
        result.put(8.8, 206);   result.put(8.9, 209);   result.put(9.0, 212);   result.put(9.1, 214);
        result.put(9.2, 217);   result.put(9.3, 220);   result.put(9.4, 223);   result.put(9.5, 226);
        result.put(9.6, 229);   result.put(9.7, 232);   result.put(9.8, 235);   result.put(9.9, 237);
        result.put(10.0, 240);  result.put(10.1, 243);  result.put(10.2, 246);  result.put(10.3, 249);
        result.put(10.4, 252);  result.put(10.5, 255);  result.put(10.6, 258);  result.put(10.7, 260);
        result.put(10.8, 263);  result.put(10.9, 266);  result.put(11.0, 269);  result.put(11.1, 272);
        result.put(11.2, 275);  result.put(11.3, 278);  result.put(11.4, 280);  result.put(11.5, 283);
        result.put(11.6, 286);  result.put(11.7, 289);  result.put(11.8, 292);  result.put(11.9, 295);
        result.put(12.0, 298);  result.put(12.1, 301);  result.put(12.2, 303);  result.put(12.3, 306);
        result.put(12.4, 309);  result.put(12.5, 312);  result.put(12.6, 315);  result.put(12.7, 318);
        result.put(12.8, 321);  result.put(12.9, 324);  result.put(13.0, 326);  result.put(13.1, 329);
        result.put(13.2, 332);  result.put(13.3, 335);  result.put(13.4, 338);  result.put(13.5, 341);
        result.put(13.6, 344);  result.put(13.7, 346);  result.put(13.8, 349);  result.put(13.9, 352);

        return result;
    }

    private Map<Integer, Double> populateGlucoseToHbA1cMap() {
        return null;
    }

    public static void main(String[] args) {
        InputResources inputResources = new InputResources();

    }
}
