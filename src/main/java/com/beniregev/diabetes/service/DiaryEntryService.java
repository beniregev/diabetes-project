package com.beniregev.diabetes.service;

import com.beniregev.diabetes.dtos.DiaryEntryDto;
import com.beniregev.diabetes.dtos.DiaryEntryPostRequest;
import com.beniregev.diabetes.model.DiaryEntry;
import java.util.List;
import java.util.Map;

public interface DiaryEntryService {
    List<DiaryEntry> getAll();
    DiaryEntry getById(final int id);
    List<DiaryEntry> getDiaryEntriesToday();
    List<DiaryEntry> getDiaryEntriesLast7Days();
    List<DiaryEntry> getDiaryEntriesLast14Days();
    List<DiaryEntry> getDiaryEntriesLast30Days();
    List<DiaryEntry> getDiaryEntriesLast90Days();
    Map<String, String> getMapNameDescription();
    String getMeasurementDescriptionByName(final String when);
    DiaryEntryDto create(DiaryEntryPostRequest diaryEntryPostRequest);
    void deleteAll();
}
