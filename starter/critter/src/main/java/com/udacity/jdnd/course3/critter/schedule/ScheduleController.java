package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

  private final ScheduleService service;

  public ScheduleController(ScheduleService service) {
    this.service = service;
  }

  @PostMapping
  public ScheduleDTO createSchedule(@RequestBody ScheduleDTO dto) {
    var schedule = scheduleFromDTO(dto);
    return scheduleToDTOWithRel(service.createSchedule(schedule, dto.getEmployeeIds(), dto.getPetIds()));
  }

  @GetMapping
  public List<ScheduleDTO> getAllSchedules() {
    return service.getAll().stream().map(this::scheduleToDTOWithRel).collect(Collectors.toList());
  }

  @GetMapping("/pet/{petId}")
  public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
    return service.getAllForPet(petId).stream().map(this::scheduleToDTOWithRel).collect(Collectors.toList());
  }

  @GetMapping("/employee/{employeeId}")
  public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
    return service.getAllForEmployee(employeeId).stream().map(this::scheduleToDTOWithRel).collect(Collectors.toList());
  }

  @GetMapping("/customer/{customerId}")
  public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
    return service.getAllForCustomer(customerId).stream().map(this::scheduleToDTOWithRel).collect(Collectors.toList());
  }

  private Schedule scheduleFromDTO(ScheduleDTO dto) {
    var schedule = new Schedule();
    schedule.setId(dto.getId());
    schedule.setDate(dto.getDate());
    schedule.setActivities(dto.getActivities());
    return schedule;
  }

  private ScheduleDTO scheduleToDTO(Schedule schedule) {
    var dto = new ScheduleDTO();
    dto.setId(schedule.getId());
    dto.setDate(schedule.getDate());
    dto.setActivities(schedule.getActivities());
    return dto;
  }

  /**
   * Similar like {@link #scheduleToDTO} but with joined ids.
   *
   * @param schedule Schedule instance.
   * @return DTO with relationship ids.
   */
  private ScheduleDTO scheduleToDTOWithRel(Schedule schedule) {
    var dto = scheduleToDTO(schedule);
    dto.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
    dto.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
    return dto;
  }

}
