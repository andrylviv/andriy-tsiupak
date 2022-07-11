package com.epam.admissions_committee.service.mapper;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FacultyMapper {
    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);
    FacultyDto mapFacultyDto(Faculty faculty);
    Faculty mapFaculty(FacultyDto facultyDto);
}
