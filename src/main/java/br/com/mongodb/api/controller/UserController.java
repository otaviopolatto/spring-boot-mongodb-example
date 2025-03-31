package br.com.mongodb.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mongodb.api.domain.User;
import br.com.mongodb.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Operações relacionadas ao recurso Usuário")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping
	@Operation(summary = "Busca de lista de usuários", description = "Retorna uma lista de todos os usuários")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca de um usuário por ID", description = "Retorna o usuário com o ID informado")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("email")
    @Operation(summary = "Busca de um usuário por e-mail", description = "Retorna o usuário com o e-mail informado")
    public User getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
    
    @GetMapping("/pesquisa")
    @Operation(summary = "Pesquisa de um usuário", description = "Retorna lista de usuários de acordo com o filtro de nome informado")
    public ResponseEntity<List<User>> getUserByName(@RequestParam String name) {        
    	List<User> userList = userService.getUsersByName(name);        
    	return ResponseEntity.ok(userList);    	
    }

    @PostMapping
    @Operation(summary = "Salva um novo registro de usuário", description = "Retorna o usuário salvo no banco de dados")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário já existente", description = "Retorna o usuário atualizado no banco de dados")
    public User updateUser(@PathVariable String id, @Valid @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário por ID", description = "Retorna status da operação")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

}
