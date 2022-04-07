package telran.java41.person.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import telran.java41.person.dto.AddressDto;
import telran.java41.person.dto.PersonDto;

public interface PersonService {
	boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	PersonDto removePerson(Integer id);

	PersonDto updatePersonName(Integer id, String name);

	PersonDto updatePersonAddress(Integer id, AddressDto addressDto);

	List<PersonDto> findPersonByCity(String city);

	@Transactional(readOnly = true) // what is it?
	List<PersonDto> findPersonByAges(LocalDate from, LocalDate to);

	List<PersonDto> findPersonByName(String name);

}
