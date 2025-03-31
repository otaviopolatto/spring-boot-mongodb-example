package br.com.mongodb.api.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "users")
public class User {

	private String id;
	
	@NotBlank
    @Size(max = 20)
	private String name;
	
	@NotBlank
    @Size(max = 255)
    @Email
	private String email;

}
