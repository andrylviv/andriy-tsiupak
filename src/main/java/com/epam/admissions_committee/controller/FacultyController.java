package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.service.FacultyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/faculty")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Log4j2
public class FacultyController {
    private final FacultyService facultyService;

    @ApiOperation("Add faculty")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/language/{lang}")
    public FacultyDto createFaculty(@PathVariable("lang") String lang, @RequestBody  @Valid FacultyDto facultyDto) {
        return facultyService.createFaculty(facultyDto, lang);
    }

    @ApiOperation("Get all faculties")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.listFaculty();
    }

    @ApiOperation("Get all faculties paged and sorted by name")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/pas-name/page/{page}/size/{size}/lang/{lang}")
    public List<Faculty> getAllFacultiesByName(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("lang") String lang) {
        return facultyService.listPagedAndSortingFacultyByName(page, size, lang);
    }

    @ApiOperation("Get all faculties paged and sorted by state founded places ")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/pas-s-f-p-l/page/{page}/size/{size}")
    public Page<Faculty> getAllFacultiesSFPl(@PathVariable("page") int page, @PathVariable("size") int size) {
        return facultyService.listPagedAndSortingFacultyByStFoundPl(page, size);
    }

    @ApiOperation("Get all faculties paged and sorted by total places ")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/pas-n-s-f-p-l/page/{page}/size/{size}")
    public Page<Faculty> getAllFacultiesNSFPl(@PathVariable("page") int page, @PathVariable("size") int size) {
        return facultyService.listPagedAndSortingFacultyByTotPl(page, size);
    }

    @ApiOperation("Get faculty")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{name}")
    public FacultyDto getFaculty(@PathVariable String name) {
        log.info("getFaculty by name {}", name);
        return facultyService.getFaculty(name);
    }

    @ApiOperation("Update faculty")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{name}")
    public FacultyDto updateFaculty(@PathVariable("name") String name, @RequestBody  @Valid FacultyDto facultyDto) {
        return facultyService.updateFaculty(name, facultyDto);
    }

    @ApiOperation("Delete faculty")
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String name) {
        facultyService.deleteFaculty(name);
        return ResponseEntity.noContent().build();
    }
}
