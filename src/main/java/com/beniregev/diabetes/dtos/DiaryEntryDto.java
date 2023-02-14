package com.beniregev.diabetes.dtos;

import com.beniregev.diabetes.enums.MeasurementEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryEntryDto {
    private LocalDateTime dateTime;
    private MeasurementEnum measurement;
    private int result;
}
