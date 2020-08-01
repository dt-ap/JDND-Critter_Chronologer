package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
  @Id
  @GeneratedValue
  private Long id;
  private LocalDate date;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "employee_id")
  private List<Employee> employees = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "employee_id")
  private List<Pet> pets = new ArrayList<>();

  @ElementCollection(targetClass = EmployeeSkill.class)
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Set<EmployeeSkill> activities = new HashSet<>();
}
