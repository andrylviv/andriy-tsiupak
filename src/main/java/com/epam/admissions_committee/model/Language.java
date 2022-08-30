package com.epam.admissions_committee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long languageId;
    private String language;
    @OneToMany(mappedBy="language")
    @JsonIgnoreProperties("language")
    @JsonIgnore
    private Set<FacultyTranslate> ft;
}
