package br.com.mongodb.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.mongodb.api.domain.User;
import br.com.mongodb.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.mongodb.api.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public List<User> getUsersByName(String name) {
		return userRepository.findByName(name);
	}	

	public User getUserById(String id) {
		return userRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow( () -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(String id, User userDetails) {
		
		User user = userRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
		
		if (user != null) {
			user.setName(userDetails.getName());
			user.setEmail(userDetails.getEmail());
			return userRepository.save(user);
		}
		
		return null;
	}
	
	public void deleteUser(String id) {
		
		userRepository.findById(id)
				.orElseThrow( () -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
		
		userRepository.deleteById(id);
	}

}
