package telran.java41.person.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java41.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	Stream<Person> findAllByBirthDateBetween(LocalDate from, LocalDate to);
}
