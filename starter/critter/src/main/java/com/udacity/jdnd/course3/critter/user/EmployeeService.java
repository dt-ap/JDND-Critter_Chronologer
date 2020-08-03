package com.udacity.jdnd.course3.critter.user;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

  private final EmployeeRepository repository;

  public EmployeeService(EmployeeRepository repository) {
    this.repository = repository;
  }

  public Employee create(Employee employee) {
    return repository.save(employee);
  }

  public Employee getById(Long id) {
    return repository.findById(id).orElseThrow(RuntimeException::new);
  }

  public List<Employee> getManyForService(Set<EmployeeSkill> skills, LocalDate date) {
    var dows = Sets.newHashSet(DayOfWeek.from(date));
    return repository.findForService(skills, (long) skills.size(), dows);
  }

  public Employee setEmployeeAvailability(Long id, Set<DayOfWeek> dows) throws RuntimeException {
    var employee = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee with id: '" + id + "' does not exist."));
    employee.addDaysAvailable(dows);
    return employee;
  }
}
