package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee extends BaseUser {

  @ElementCollection(targetClass = EmployeeSkill.class)
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Set<EmployeeSkill> skills = new HashSet<>();

  @ElementCollection(targetClass = DayOfWeek.class)
  @Enumerated
  private Set<DayOfWeek> daysAvailable = new HashSet<>();

  public Set<EmployeeSkill> getSkills() {
    return skills;
  }

  public void setSkills(Set<EmployeeSkill> skills) {
    this.skills = skills;
  }

  public Set<DayOfWeek> getDaysAvailable() {
    return daysAvailable;
  }

  public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
    this.daysAvailable = daysAvailable;
  }

  public void addSkill(EmployeeSkill skill) {
    if (skills == null) {
      skills = new HashSet<>();
    }
    skills.add(skill);
  }

  public void addDaysAvailable(Set<DayOfWeek> dayOfWeek) {
    if (daysAvailable == null) {
      daysAvailable = new HashSet<>();
    }
    daysAvailable.addAll(dayOfWeek);
  }
}
