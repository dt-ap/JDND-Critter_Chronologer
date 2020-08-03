package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.domain.BaseEntity;
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
public class Schedule extends BaseEntity {
  private LocalDate date;

  @ManyToMany(cascade = CascadeType.ALL, targetEntity = Employee.class)
  private List<Employee> employees = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL, targetEntity = Pet.class)
  private List<Pet> pets = new ArrayList<>();

  @ElementCollection(targetClass = EmployeeSkill.class)
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Set<EmployeeSkill> activities = new HashSet<>();

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public List<Pet> getPets() {
    return pets;
  }

  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }

  public Set<EmployeeSkill> getActivities() {
    return activities;
  }

  public void setActivities(Set<EmployeeSkill> activities) {
    this.activities = activities;
  }

  public void addEmployees(List<Employee> employees) {
    if (this.employees == null) {
      this.employees = new ArrayList<>();
    }
    this.employees.addAll(employees);
  }

  public void addPets(List<Pet> pets) {
    if (this.pets == null) {
      this.pets = new ArrayList<>();
    }
    this.pets.addAll(pets);
  }

  public void addActivities(Set<EmployeeSkill> skill) {
    if (activities == null) {
      activities = new HashSet<>();
    }
    activities.addAll(skill);
  }

}
