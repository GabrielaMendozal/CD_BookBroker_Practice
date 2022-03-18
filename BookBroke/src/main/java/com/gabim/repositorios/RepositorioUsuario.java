package com.gabim.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gabim.modelos.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario,Long> {
	
List<Usuario> findAll();   /*select from usuarios*///si fuera insertar save
	
	//inserta values(objeto con todos los datos
	@SuppressWarnings("unchecked")
	Usuario save(Usuario nuevoUsuario);
	
	
	List<Usuario> findById(long id);
	
	//Usuario findByEmail(String email);
	
	List<Usuario> findByEmailAndPassword(String email,String password);
	
	List<Usuario> findByEmail(String email);
}
