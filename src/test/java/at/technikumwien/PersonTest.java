package at.technikumwien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
	private Person person;

	@BeforeEach
	public void setUp() {
		person = new Person(1L, Sex.MALE, "Markus", "Mustermann", LocalDate.of(1990, 1, 1), true);
	}

	@Test
	public void testGetName() {

		assertEquals("Markus Mustermann", person.getName());
	}

	@Test
	public void testgetNameWithFirstNameNull() {
		person.setFirstName(null);

		assertThrows(IllegalArgumentException.class, () -> person.getName());
	}
	
	@Test
	public void testgetNameWithFirstNameEmpty() {
		person.setFirstName(" ");

		assertThrows(IllegalArgumentException.class, () -> person.getName());
	}
	
	// TODO add more tests

}