package at.technikumwien;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sex sex;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false, length = 50)
	private String lastName;
	
	@Column(nullable = false)
	private LocalDate birthdate;
	
	@Column(nullable = false)
	private boolean active;
	
	@JsonIgnore
	public String getName() {
		if(firstName == null || firstName.isBlank()) {
			throw new IllegalArgumentException("first name is null or empty");
		}
		
		if(lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException("last name is null or empty");
		}
		
		return firstName + " " + lastName;
	}

}
