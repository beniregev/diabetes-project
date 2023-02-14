package com.beniregev.diabetes.controller;

import com.beniregev.diabetes.dtos.DiaryEntryDto;
import com.beniregev.diabetes.dtos.DiaryEntryPostRequest;
import com.beniregev.diabetes.enums.MeasurementEnum;
import com.beniregev.diabetes.model.DiaryEntry;
import com.beniregev.diabetes.service.DiaryEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/")
@RestController
public class DiaryEntryController {

    private final DiaryEntryService diaryEntryService;

    public DiaryEntryController(DiaryEntryService diaryEntryService) {
        this.diaryEntryService = diaryEntryService;
    }

    @GetMapping("/v1/diary")
    public ResponseEntity<List<DiaryEntry>> getAll() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getAll();
        return new ResponseEntity<>(listDiaryEntries, HttpStatus.OK);
    }

    @GetMapping("/v1/diary/{id}")
    public ResponseEntity<DiaryEntry> getById(@PathVariable final int id) {
        DiaryEntry diaryEntry = diaryEntryService.getById(id);
        HttpStatus httpStatus = diaryEntry != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(diaryEntry, httpStatus);
    }

    @GetMapping("/v1/diary/today")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesToday() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesToday();
        return new ResponseEntity<>(listDiaryEntries, getHttpStatus(listDiaryEntries));
    }

    @GetMapping("/v1/diary/7days")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesLast7Days() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesLast7Days();
        return new ResponseEntity<>(listDiaryEntries, getHttpStatus(listDiaryEntries));
    }

    @GetMapping("/v1/diary/14days")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesLast14Days() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesLast14Days();
        return new ResponseEntity<>(listDiaryEntries, getHttpStatus(listDiaryEntries));
    }

    @GetMapping("/v1/diary/30days")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesLast30Days() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesLast30Days();
        return new ResponseEntity<>(listDiaryEntries, getHttpStatus(listDiaryEntries));
    }

    @GetMapping("/v1/diary/90days")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesLast90Days() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesLast90Days();
        return new ResponseEntity<>(listDiaryEntries, getHttpStatus(listDiaryEntries));
    }

    @GetMapping("/v1/data/when")
    public ResponseEntity<Map<String, String>> getMapNameDescription() {
        Map<String, String> mapValues = diaryEntryService.getMapNameDescription();
        return new ResponseEntity<>(mapValues, HttpStatus.OK);
    }

    @GetMapping("/v1/data/{when}")
    public ResponseEntity<String> getMeasurementDescriptionByName(@PathVariable String when) {
        String description = diaryEntryService.getMeasurementDescriptionByName(when);
        return new ResponseEntity<>(description, getHttpStatus(description));
    }

    @PostMapping("/v1/diary/{when}/{count}")
    public ResponseEntity<DiaryEntryDto> createDiaryEntry(@PathVariable String when, @PathVariable int count) {
        DiaryEntryPostRequest diaryEntryPostRequest = DiaryEntryPostRequest.builder()
                .dateTime(LocalDateTime.now())
                .measurement(MeasurementEnum.mapValues.get(when.toLowerCase()))
                .result(count)
                .build();
        DiaryEntryDto created = diaryEntryService.create(diaryEntryPostRequest);
        return new ResponseEntity<>(created, getHttpStatus(created));
    }

    @DeleteMapping("/v1/diary")
    public ResponseEntity<String> deleteAll() {
        diaryEntryService.deleteAll();
        return ResponseEntity.ok("All diary entries deleted.");
    }

    @GetMapping(path = { "/v1/ping", "/v1/say-hello", "/v1/health-check"})
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Server is up and running, ready to process requests...");
    }

    private HttpStatus getHttpStatus(final String toCheck) {
        if (toCheck == null) return HttpStatus.NOT_FOUND;
        if (toCheck.isBlank()) return HttpStatus.NOT_ACCEPTABLE;    //  Value contains only whitespaces
        if (toCheck.isEmpty()) return HttpStatus.NO_CONTENT;
        return HttpStatus.OK;
    }

    private HttpStatus getHttpStatus(final DiaryEntryDto diaryEntryDto) {
        if (diaryEntryDto == null) return HttpStatus.NOT_FOUND;
        return HttpStatus.OK;
    }

    private HttpStatus getHttpStatus(final List<DiaryEntry> list) {
        if (list == null) return HttpStatus.NOT_FOUND;
        if (list.isEmpty()) return HttpStatus.NO_CONTENT;
        return HttpStatus.OK;
    }

    private HttpStatus getHttpStatus(final Map<String, String> map) {
        if (map == null) return HttpStatus.NOT_FOUND;
        if (map.isEmpty()) return HttpStatus.NO_CONTENT;
        return HttpStatus.OK;
    }

}
