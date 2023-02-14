package com.beniregev.diabetes.model;

import com.beniregev.diabetes.enums.MeasurementEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diary")
public class DiaryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dateTime;
    @Enumerated(value = EnumType.STRING)
    private MeasurementEnum measurement;
    private int result;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryEntry that = (DiaryEntry) o;
        return result == that.result && Objects.equals(id, that.id) && Objects.equals(dateTime, that.dateTime) && measurement == that.measurement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, measurement, result);
    }

    @Override
    public String toString() {
        return "DiaryEntry {" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", measurement=" + measurement +
                ", result=" + result +
                '}';
    }
}
