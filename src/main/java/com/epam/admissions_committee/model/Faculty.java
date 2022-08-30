package com.epam.admissions_committee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;
    private int stFundedPlaces;
    private int totPlaces;
    private int isEieMath;
    private int isEieUkLang;
    private int isEiePhysics;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="faculty", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("faculty")
    private List<FacultyTranslate> facultyTranslates;          //for faculty_translate
    private Instant writtenOn;
    @ManyToMany(mappedBy = "faculties")
    @JsonIgnore
    Set<User> users;                                           //for user_faculty
    @OneToMany(mappedBy = "faculty")
    @JsonIgnore
    List<Statement> statements;                                //for Statement entity
}
