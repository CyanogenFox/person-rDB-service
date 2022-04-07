package telran.java41.person.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import telran.java41.person.dto.AddressDto;
import telran.java41.person.dto.PersonDto;
import telran.java41.person.service.PersonService;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

	PersonService personService;

	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public PersonDto findPerson(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@DeleteMapping("/{id}")
	public PersonDto removePerson(@PathVariable Integer id) {
		return personService.removePerson(id);
	}

	@PutMapping("/{id}/name/{name}")
	public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updatePersonName(id, name);
	}

	@PutMapping("/{id}/address")
	public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		return personService.updatePersonAddress(id, addressDto);
	}

	@GetMapping("/name/{name}")
	public List<PersonDto> findPersonByName(@PathVariable String name) {
		return personService.findPersonByName(name);
	}

	@GetMapping("/city/{city}")
	public List<PersonDto> findPersonByCity(@PathVariable String city) {
		return personService.findPersonByCity(city);
	}

	@GetMapping("/ages/{from}/{to}")
	public List<PersonDto> findPersonByAges(@PathVariable Integer from, @PathVariable Integer to) {
		return personService.findPersonByAges(LocalDate.now().minusYears(to), LocalDate.now().minusYears(from));
	}
}
