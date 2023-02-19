package com.beniregev.diabetes.controller;

import com.beniregev.diabetes.dtos.DiaryEntryDto;
import com.beniregev.diabetes.dtos.DiaryEntryPostRequest;
import com.beniregev.diabetes.enums.MeasurementEnum;
import com.beniregev.diabetes.mappers.DiaryEntryMapper;
import com.beniregev.diabetes.model.DiaryEntry;
import com.beniregev.diabetes.service.DiaryEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequestMapping("/api")
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
        return new ResponseEntity<>(listDiaryEntries,
                getHttpStatus(DiaryEntryMapper.INSTANCE.listDiaryEntryToListDiaryEntryDto(listDiaryEntries)));
    }

    @GetMapping("/v1/diary/7days")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesLast7Days() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesLast7Days();
        return new ResponseEntity<>(listDiaryEntries,
                getHttpStatus(DiaryEntryMapper.INSTANCE.listDiaryEntryToListDiaryEntryDto(listDiaryEntries)));
    }

    @GetMapping("/v1/diary/14days")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesLast14Days() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesLast14Days();
        return new ResponseEntity<>(listDiaryEntries,
                getHttpStatus(DiaryEntryMapper.INSTANCE.listDiaryEntryToListDiaryEntryDto(listDiaryEntries)));
    }

    @GetMapping("/v1/diary/30days")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesLast30Days() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesLast30Days();
        return new ResponseEntity<>(listDiaryEntries,
                getHttpStatus(DiaryEntryMapper.INSTANCE.listDiaryEntryToListDiaryEntryDto(listDiaryEntries)));
    }

    @GetMapping("/v1/diary/90days")
    public ResponseEntity<List<DiaryEntry>> getDiaryEntriesLast90Days() {
        List<DiaryEntry> listDiaryEntries = diaryEntryService.getDiaryEntriesLast90Days();
        return new ResponseEntity<>(listDiaryEntries,
                getHttpStatus(DiaryEntryMapper.INSTANCE.listDiaryEntryToListDiaryEntryDto(listDiaryEntries)));
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

    /**
     *  [
     *     {
     *         "measurement": "MORNING_WAKEUP",
     *         "result": 91
     *     },
     *     {
     *         "measurement": "MORNING_BEFORE_BREAKFAST",
     *         "result": 92
     *     },
     *     {
     *         "measurement": "MORNING_AFTER_BREAKFAST_2HOURS",
     *         "result": 93
     *     },
     *     {
     *         "measurement": "NOON_BEFORE_LUNCH",
     *         "result": 94
     *     },
     *     {
     *         "measurement": "NOON_AFTER_LUNCH_2HOURS",
     *         "result": 95
     *     },
     *     {
     *         "measurement": "EVENING_BEFORE_SUPPER",
     *         "result": 96
     *     },
     *     {
     *         "measurement": "EVENING_AFTER_SUPPER_2HOURS",
     *         "result": 97
     *     },
     *     {
     *         "measurement": "NIGHT_BEFORE_SLEEP",
     *         "result": 98
     *     },
     *     {
     *         "measurement": "OTHER",
     *         "result": 99
     *     }
     * ]
     * @param localDate value of {@link LocalDate} in which to create all the {@code DiaryEntry} instances in the
     *                  {@link List}. example value: 2022-12-16
     *                  {@code http(s)://domain-host:port/api/v1/diary/2022-12-16}
     * @param listDiaryEntriesPostRequest {@link List} of {@link DiaryEntryPostRequest}s to be created in the database.
     *                                                In this request the value of {@link DiaryEntryPostRequest} in each
     *                                                instance will be replaced by the combination of {@code localDate}
     *                                                parameter and the value of {@code defaultTime} field in the
     *                                                {@link MeasurementEnum} enum.
     * @return {@link List} of {@link DiaryEntryDto}s that were created in the database.
     */
    @PostMapping("/v1/diary/{localDate}")
    public ResponseEntity<List<DiaryEntryDto>> createDiaryEntry(@PathVariable LocalDate localDate,
                                                                @RequestBody List<DiaryEntryPostRequest> listDiaryEntriesPostRequest) {
        List<DiaryEntryDto> created = diaryEntryService.create(localDate, listDiaryEntriesPostRequest);
        return new ResponseEntity<>(created, getHttpStatus(created));
    }

    /**
     *  [
     *     {
     *         "dateTime": "2023-02-14T08:00:09.067479",
     *         "measurement": "MORNING_WAKEUP",
     *         "result": 91
     *     },
     *     {
     *         "dateTime": "2023-02-14T08:30:09.067479",
     *         "measurement": "MORNING_BEFORE_BREAKFAST",
     *         "result": 92
     *     },
     *     {
     *         "dateTime": "2023-02-14T10:30:09.067479",
     *         "measurement": "MORNING_AFTER_BREAKFAST_2HOURS",
     *         "result": 93
     *     },
     *     {
     *         "dateTime": "2023-02-14T14:00:09.067479",
     *         "measurement": "NOON_BEFORE_LUNCH",
     *         "result": 94
     *     },
     *     {
     *         "dateTime": "2023-02-14T16:30:09.067479",
     *         "measurement": "NOON_AFTER_LUNCH_2HOURS",
     *         "result": 95
     *     },
     *     {
     *         "dateTime": "2023-02-14T19:30:09.067479",
     *         "measurement": "EVENING_BEFORE_SUPPER",
     *         "result": 96
     *     },
     *     {
     *         "dateTime": "2023-02-14T22:00:09.067479",
     *         "measurement": "EVENING_AFTER_SUPPER_2HOURS",
     *         "result": 97
     *     },
     *     {
     *         "dateTime": "2023-02-14T23:00:09.067479",
     *         "measurement": "NIGHT_BEFORE_SLEEP",
     *         "result": 98
     *     },
     *     {
     *         "dateTime": "2023-02-14T12:34:56.789012",
     *         "measurement": "OTHER",
     *         "result": 99
     *     }
     * ]
     * @param listDiaryEntriesPostRequest {@link List} of {@link DiaryEntryPostRequest}s to be created
     *                                                in the database. In this request each
     *                                                {@link DiaryEntryPostRequest} has to contain all
     *                                                the data, including the {@code dataTime}.
     * @return {@link List} of {@link DiaryEntryDto}s that were created in the database.
     */
    @PostMapping("/v1/diary")
    public ResponseEntity<List<DiaryEntryDto>> createDiaryEntry(@RequestBody List<DiaryEntryPostRequest> listDiaryEntriesPostRequest) {
        if (atLeastOneDiaryEntryPostRequestIsNull(listDiaryEntriesPostRequest))
            return new ResponseEntity<>(
                    DiaryEntryMapper.INSTANCE.listDiaryEntryPostRequestToListDiaryEntryDto(listDiaryEntriesPostRequest),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        List<DiaryEntryDto> created = diaryEntryService.create(listDiaryEntriesPostRequest);
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

    private HttpStatus getHttpStatus(final List<DiaryEntryDto> list) {
        if (list == null) return HttpStatus.NOT_FOUND;
        if (list.isEmpty()) return HttpStatus.NO_CONTENT;
        return HttpStatus.OK;
    }

    private boolean atLeastOneDiaryEntryPostRequestIsNull(List<DiaryEntryPostRequest> listDiaryEntriesPostRequest) {
        return listDiaryEntriesPostRequest.stream()
                .anyMatch(x -> x.getDateTime() == null || x.getMeasurement() == null || x.getResult() < 50);
    }

}
