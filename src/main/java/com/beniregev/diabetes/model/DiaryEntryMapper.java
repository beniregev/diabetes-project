package com.beniregev.diabetes.model;

import com.beniregev.diabetes.dtos.DiaryEntryDto;
import com.beniregev.diabetes.dtos.DiaryEntryPostRequest;
import com.beniregev.diabetes.dtos.DiaryEntryPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DiaryEntryMapper {
    DiaryEntryMapper INSTANCE = Mappers.getMapper(DiaryEntryMapper.class);

    DiaryEntry diaryEntryDtoToDiaryEntry(DiaryEntryDto diaryEntryDto);
    DiaryEntryDto diaryEntryToDiaryEntryDto(DiaryEntry diaryEntry);

    DiaryEntry diaryEntryPostRequestToDiaryEntry(DiaryEntryPostRequest source);
    DiaryEntryPostResponse diaryEntryToDiaryEntryPostResponse(DiaryEntry source);

    List<DiaryEntryDto> toListDiaryEntryDto(List<DiaryEntry> listDiaryEntries);
}
