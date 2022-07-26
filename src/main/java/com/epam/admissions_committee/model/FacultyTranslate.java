package com.epam.admissions_committee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacultyTranslate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="faculty_id", referencedColumnName = "facultyId")
    @JsonIgnoreProperties("facultyTranslates")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name="language_id", nullable=false)
    @JsonIgnoreProperties("ft")
    private Language language;
    private String facultyName;
}
