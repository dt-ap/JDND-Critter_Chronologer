package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private final CustomerService customerService;
  private final EmployeeService employeeService;

  public UserController(CustomerService customerService, EmployeeService employeeService) {
    this.customerService = customerService;
    this.employeeService = employeeService;
  }

  @PostMapping("/customer")
  public CustomerDTO saveCustomer(@RequestBody CustomerDTO dto) {
    var customer = customerService.create(customerFromDTO(dto));
    return customerToDTO(customer);
  }

  @GetMapping("/customer")
  public List<CustomerDTO> getAllCustomers() {
    return customerService.getAll().stream()
        .map(this::customerToDTOWithRel).collect(Collectors.toList());
  }

  @GetMapping("/customer/pet/{petId}")
  public CustomerDTO getOwnerByPet(@PathVariable long petId) {
    var customer = customerService.getOwnerByPetId(petId);
    return customerToDTOWithRel(customer);
  }

  @PostMapping("/employee")
  public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO dto) {
    var employee = employeeService.create(employeeFromDTO(dto));
    return employeeToDTO(employee);
  }

  @PostMapping("/employee/{id}")
  public EmployeeDTO getEmployee(@PathVariable long id) {
    var employee = employeeService.getById(id);
    return employeeToDTO(employee);
  }

  @PutMapping("/employee/{id}")
  public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long id) {
    employeeService.setEmployeeAvailability(id, daysAvailable);
  }

  @GetMapping("/employee/availability")
  public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO requestDTO) {
    return employeeService.getManyForService(requestDTO.getSkills(), requestDTO.getDate()).stream()
        .map(this::employeeToDTOWithRel).collect(Collectors.toList());
  }

  private Customer customerFromDTO(CustomerDTO dto) {
    var customer = new Customer();
    customer.setId(dto.getId());
    customer.setName(dto.getName());
    customer.setNotes(dto.getNotes());
    customer.setPhoneNumber(dto.getPhoneNumber());
    return customer;
  }

  private CustomerDTO customerToDTO(Customer customer) {
    var dto = new CustomerDTO();
    dto.setId(customer.getId());
    dto.setName(customer.getName());
    dto.setNotes(customer.getNotes());
    dto.setPhoneNumber(customer.getPhoneNumber());
    return dto;
  }

  /**
   * Similar like {@link #customerToDTO} but with joined ids.
   *
   * @param customer Customer instance.
   * @return DTO with relationship ids.
   */
  private CustomerDTO customerToDTOWithRel(Customer customer) {
    var dto = customerToDTO(customer);
    dto.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
    return dto;
  }

  private Employee employeeFromDTO(EmployeeDTO dto) {
    var employee = new Employee();
    employee.setId(dto.getId());
    employee.setName(dto.getName());
    employee.setSkills(dto.getSkills());
    employee.setDaysAvailable(dto.getDaysAvailable());
    return employee;
  }

  private EmployeeDTO employeeToDTO(Employee employee) {
    var dto = new EmployeeDTO();
    dto.setId(employee.getId());
    dto.setName(employee.getName());
    dto.setSkills(employee.getSkills());
    dto.setDaysAvailable(employee.getDaysAvailable());
    return dto;
  }

  /**
   * Similar like {@link #customerToDTO} but with joined ids.
   *
   * @param employee Employee instance.
   * @return DTO with relationship ids.
   */
  private EmployeeDTO employeeToDTOWithRel(Employee employee) {
    var dto = employeeToDTO(employee);
    return dto;
  }

}
