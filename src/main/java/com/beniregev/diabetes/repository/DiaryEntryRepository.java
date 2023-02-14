package com.beniregev.diabetes.repository;

import com.beniregev.diabetes.model.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Integer> {
    List<DiaryEntry> findDiaryEntriesByDateTimeBetween(final LocalDateTime from, final LocalDateTime to);
}
