package telran.java41.person.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import telran.java41.person.dao.PersonRepository;
import telran.java41.person.dto.AddressDto;
import telran.java41.person.dto.PersonDto;
import telran.java41.person.dto.exceptions.EntityNotFoundException;
import telran.java41.person.model.Address;
import telran.java41.person.model.Person;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

	PersonRepository personRepository;
	ModelMapper modelMapper;

	@Override
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		personRepository.deleteById(id);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersonName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		person.setAddress(modelMapper.map(addressDto, Address.class));
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findPersonByCity(String city) {
		Address address = new Address();
		Person person = new Person();
		address.setCity(city);
		person.setAddress(address);
		Example<Person> example = Example.of(person);
		List<Person> persons = personRepository.findAll(example);
		return persons.stream().map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PersonDto> findPersonByName(String name) {
		Person person = new Person();
		person.setName(name);
		Example<Person> example = Example.of(person);
		List<Person> persons = personRepository.findAll(example);
		return persons.stream().map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PersonDto> findPersonByAges(LocalDate from, LocalDate to) {
		return personRepository.findAllByBirthDateBetween(from, to).map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

}
