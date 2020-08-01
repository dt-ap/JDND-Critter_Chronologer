package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  List<Employee> findBySkillsInAndDaysAvailableIn(Set<EmployeeSkill> skillsUnavailable, Set<DayOfWeek> daysAvailable);

  @Query("FROM Employee e LEFT JOIN e.skills s LEFT JOIN e.daysAvailable d " +
      "WHERE s IN :skills AND d IN :dows GROUP BY e HAVING COUNT(s) = :skillCount") // Relational Division
  List<Employee> findForService(Set<EmployeeSkill> skills, Long skillCount, Set<DayOfWeek> dows);
}
