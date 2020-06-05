package at.technikumwien;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

@Configuration
@Profile("default")
public class DBInitializer {
	@Autowired
	private PersonRepository personRepository;
	
	@EventListener(ApplicationReadyEvent.class)
	public void handleApplicationEvent() {

		personRepository.saveAll(
				List.of(
						new Person(
								null,
								Sex.MALE,
								"Max",
								"Mustermann",
								LocalDate.of(1990, 1, 1),
								true
								),
						new Person(
								null,
								Sex.FEMALE,
								"Martina",
								"Musterfrau",
								LocalDate.of(1990, 2, 1),
								true
								)
				));
	}
}
