package com.epam.admissions_committee.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FacultyTranslate {
    private Long id;
    private Faculty faculty;
    private Language language;
    private String facultyName;
}
