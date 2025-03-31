package br.com.mongodb.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.mongodb.api.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	// Consulta por nome usando JSON nativo do MongoDB
    @Query("{ 'name' : /?0/ }")
    List<User> findByName(String name);
    
    // Consulta por e-mail usando JSON nativo do MongoDB
    @Query("{ 'email' : ?0 }")
    Optional<User> findByEmail(String email);
    
}
