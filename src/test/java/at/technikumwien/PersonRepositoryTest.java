package at.technikumwien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Tag("integration-test")
public class PersonRepositoryTest {
	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testSave() {
		var countBefore = personRepository.count();

		var person = personRepository
				.save(new Person(null, Sex.MALE, "Maxi", "Musterkind", LocalDate.of(2010, 1, 1), true));

		assertNotNull(person.getId());
		assertEquals(countBefore + 1, personRepository.count());
	}

	@Test
	public void testFindAllByActiveTrue() {
		var persons = personRepository.findAllByActiveTrue();

		assertEquals(1, persons.size());
	}

	// TODO add tests
}
