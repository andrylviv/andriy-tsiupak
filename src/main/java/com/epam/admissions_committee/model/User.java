package com.epam.admissions_committee.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private int isAdmin;
    private int isBlocked;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userInfo_id", referencedColumnName = "id")
    @JsonManagedReference
    private UserInfo userInfo;
    private Instant writtenOn;
    @ManyToMany
    @JoinTable(
            name = "user_faculty",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    Set<Faculty> faculties;                                                 //for user_faculty
    @OneToMany(mappedBy = "user")
    List<Statement> statements;                                              //for Statement entity
}
