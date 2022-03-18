package com.gabim.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gabim.modelos.Book;

@Repository
public interface RepositorioBook extends CrudRepository<Book,Long>{
List<Book>findAll();
	
	@SuppressWarnings("unchecked")
	Book save(Book nuevoBook); 
	
	//aqui se me fue el internet
	List<Book> findById(long id);
	
	//para tabla prestamos
	List<Book> findAllByBuserId(Long id);
	
	@Transactional
	Long deleteById(long id);
}
