package com.udacity.jdnd.course3.critter.pet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

  private final PetService service;

  public PetController(PetService service) {
    this.service = service;
  }

  @PostMapping
  public PetDTO savePet(@RequestBody PetDTO dto) {
    try {
      var pet = service.createPet(petFromDTO(dto), dto.getOwnerId());
      return petToDTOWithRel(pet);
    } catch (RuntimeException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
  }

  @GetMapping("/{petId}")
  public PetDTO getPet(@PathVariable long id) {
    var pet = service.getPetById(id).orElseThrow(RuntimeException::new);
    return petToDTOWithRel(pet);
  }

  @GetMapping
  public List<PetDTO> getPets() {
    throw new UnsupportedOperationException();
  }

  @GetMapping("/owner/{ownerId}")
  public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
    return service.getManyByOwnerId(ownerId).stream().map(this::petToDTOWithRel).collect(Collectors.toList());
  }

  private Pet petFromDTO(PetDTO dto) {
    var pet = new Pet();
    pet.setId(dto.getId());
    pet.setType(dto.getType());
    pet.setName(dto.getName());
    pet.setBirthDate(dto.getBirthDate());
    pet.setNotes(dto.getNotes());
    return pet;
  }

  private PetDTO petToDTO(Pet pet) {
    var dto = new PetDTO();
    dto.setId(pet.getId());
    dto.setType(pet.getType());
    dto.setName(pet.getName());
    dto.setBirthDate(pet.getBirthDate());
    dto.setNotes(pet.getNotes());
    return dto;
  }

  private PetDTO petToDTOWithRel(Pet pet) {
    var dto = petToDTO(pet);
    dto.setOwnerId(pet.getOwner().getId());
    return dto;
  }
}
