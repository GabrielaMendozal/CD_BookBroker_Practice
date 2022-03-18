package com.gabim.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.gabim.modelos.Usuario;
import com.gabim.repositorios.RepositorioUsuario;

@Service
public class ServicioUsuario {
	
	private final RepositorioUsuario repositorioUsuario;

	public ServicioUsuario(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}
	
	public Usuario insertIntoUsuarios( Usuario nuevoUsuario, BindingResult result ) {
		Usuario usuarioElegido = selectUsuarioWhereEmail(nuevoUsuario.getEmail());
		if(usuarioElegido != null) {
			result.rejectValue("email", "soyUnError", "Usar otro email"); ///1~es del modelo, decido nombre , 3~es el mensaje 
		}
		if(result.hasErrors()) {
			return null;
		}
		//aqui deberia agregaar bcrypt para que no se amplie el password
		String hash = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
		//System.out.println (hash);
		nuevoUsuario.setPassword(hash);
		//
		return repositorioUsuario.save(nuevoUsuario);
	}
	
	public Usuario findUsuario(Long id) {
		Optional<Usuario> optionalUsuario = repositorioUsuario.findById(id);
		if(optionalUsuario.isPresent()) {
			return optionalUsuario.get();
		}else {
			return null;
			}
	}
	/*
	public Usuario findEmail(String email) {
		return repositorioUsuario.findByEmail(email);
	}*/
	
	public Usuario selectUsuarioWhereEmailYPassword(String email,String password) {
		List<Usuario> usuarioEncontrado = repositorioUsuario.findByEmailAndPassword(email, password);
		if (usuarioEncontrado.isEmpty()) {
			return null;
		}
		else {
			return usuarioEncontrado.get(0);
		}
	}
	
	public Usuario selectUsuarioWhereEmail(String email) {
		List<Usuario> usuarioEncontrado = repositorioUsuario.findByEmail(email);
		if (usuarioEncontrado.isEmpty()) {
			return null;
		}
		else {
			return usuarioEncontrado.get(0);
		}
	}
	
}
