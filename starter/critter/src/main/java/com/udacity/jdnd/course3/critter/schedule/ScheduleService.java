package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ScheduleService {

  private final ScheduleRepository repository;
  private final EmployeeRepository empRepository;
  private final PetRepository petRepository;

  public ScheduleService(ScheduleRepository repository, EmployeeRepository empRepository, PetRepository petRepository) {
    this.repository = repository;
    this.empRepository = empRepository;
    this.petRepository = petRepository;
  }

  public List<Schedule> getAll() {
    return repository.findAll();
  }

  public List<Schedule> getAllForPet(long petId) {
    return repository.findAllByPetsId(petId);
  }

  public List<Schedule> getAllForEmployee(long employeeId) {
    return repository.findAllByEmployeesId(employeeId);
  }

  public List<Schedule> getAllForCustomer(long customerId) {
    return repository.findAllByPetsOwnerId(customerId);
  }

  @Transactional
  public Schedule createSchedule(Schedule schedule, List<Long> empIds, List<Long> petIds) {
    var employees = empRepository.findAllById(empIds);
    var pets = petRepository.findAllById(petIds);
    schedule.setEmployees(employees);
    schedule.setPets(pets);
    return repository.save(schedule);
  }
}
