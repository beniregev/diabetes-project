package com.beniregev.diabetes.service.impl;

import com.beniregev.diabetes.dtos.DiaryEntryDto;
import com.beniregev.diabetes.dtos.DiaryEntryPostRequest;
import com.beniregev.diabetes.enums.MeasurementEnum;
import com.beniregev.diabetes.model.DiaryEntry;
import com.beniregev.diabetes.model.DiaryEntryMapper;
import com.beniregev.diabetes.repository.DiaryEntryRepository;
import com.beniregev.diabetes.service.DiaryEntryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class DiaryEntryServiceImpl implements DiaryEntryService {

    private final LocalDate TODAY = LocalDate.now();
    private final LocalDateTime EOD_TODAY = LocalDateTime.of(TODAY, LocalTime.MAX);
    private final DiaryEntryRepository diaryEntryRepository;

    public DiaryEntryServiceImpl(DiaryEntryRepository diaryEntryRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
    }

    public List<DiaryEntry> getAll() {
        return diaryEntryRepository.findAll();
    }

    public DiaryEntry getById(final int id) {
        return diaryEntryRepository.findById(id).orElse(null);
    }

    public List<DiaryEntry> getDiaryEntriesToday() {
        LocalDateTime from = LocalDateTime.of(TODAY, LocalTime.MIDNIGHT);
        return diaryEntryRepository.findDiaryEntriesByDateTimeBetween(from, EOD_TODAY);
    }

    public List<DiaryEntry> getDiaryEntriesLast7Days() {
        LocalDateTime from = LocalDateTime.of(TODAY.minusDays(7L), LocalTime.MIDNIGHT);
        return diaryEntryRepository.findDiaryEntriesByDateTimeBetween(from, EOD_TODAY);
    }

    public List<DiaryEntry> getDiaryEntriesLast14Days() {
        LocalDateTime from = LocalDateTime.of(TODAY.minusDays(14L), LocalTime.MIDNIGHT);
        return diaryEntryRepository.findDiaryEntriesByDateTimeBetween(from, EOD_TODAY);
    }

    public List<DiaryEntry> getDiaryEntriesLast30Days() {
        LocalDateTime from = LocalDateTime.of(TODAY.minusDays(30L), LocalTime.MIDNIGHT);
        return diaryEntryRepository.findDiaryEntriesByDateTimeBetween(from, EOD_TODAY);
    }

    public List<DiaryEntry> getDiaryEntriesLast90Days() {
        LocalDateTime from = LocalDateTime.of(TODAY.minusDays(90L), LocalTime.MIDNIGHT);
        return diaryEntryRepository.findDiaryEntriesByDateTimeBetween(from, EOD_TODAY);
    }

    public Map<String, String> getMapNameDescription() {
        return MeasurementEnum.mapDescriptions;
    }

    public String getMeasurementDescriptionByName(final String when) {
        return MeasurementEnum.mapDescriptions.get(when);
    }

    public DiaryEntryDto create(final DiaryEntryPostRequest newEntry) {
        DiaryEntry created = diaryEntryRepository.save(DiaryEntryMapper.INSTANCE.diaryEntryPostRequestToDiaryEntry(newEntry));
        return DiaryEntryMapper.INSTANCE.diaryEntryToDiaryEntryDto(created);
    }

    public void deleteAll() {
        diaryEntryRepository.deleteAll();
    }

}
