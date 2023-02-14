package com.beniregev.diabetes.dtos;

import com.beniregev.diabetes.enums.MeasurementEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryEntryPostResponse {
    private LocalDateTime dateTime;
    private MeasurementEnum measurement;
    private int result;

}
