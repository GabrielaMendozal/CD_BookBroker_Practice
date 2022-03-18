package com.gabim.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gabim.modelos.Book;
import com.gabim.modelos.Usuario;
import com.gabim.repositorios.RepositorioBook;
import javax.servlet.http.HttpSession;

@Service
public class ServicioBook {
	
	private final RepositorioBook repositorioBook;

	public ServicioBook(RepositorioBook repositorioBook) {
		this.repositorioBook = repositorioBook;
	}
	
	public Book insertIntoBooks(Book nuevoBook) {
		return repositorioBook.save(nuevoBook);
	}
	
	public List<Book> selectFromAllBooks() {
		return repositorioBook.findAll();
	}
	
	public Book findBook(Long id) {
		Optional<Book> optionalBook = repositorioBook.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}else {
			return null;
			}
	}
	// entre (edicionBook)
	 public Book updatedBook(Long id, String title, String author, String thoughts, Usuario usuario) {
			Book edicionBook = new Book(title, author, thoughts, usuario); // este objeto era el mismo de la linea de abajo //
			edicionBook.setId(id);
			return repositorioBook.save(edicionBook);
	 }
	 
	 public List<Book> allNBBooks() {
	        return repositorioBook.findAllByBuserId(null);
	    }

	    public List<Book> allBBooks(Long id) {
	        return repositorioBook.findAllByBuserId(id);
	    }
	    
	    public void deleteBook(Long id) {
	        repositorioBook.deleteById(id);
	    } 
	   
	    public Book borrowBook(Long id, HttpSession session) {
	        Usuario buser = new Usuario();
	        buser.setId((Long) session.getAttribute("id"));
	        Book book = findBook(id); // aqui podrá faltar algo set.
	        System.out.println(buser.getId());
	        System.out.println(book.getId());
	        if (book != null) {
	            book.setBuser(buser);
	            return repositorioBook.save(book);
	        } else return null;
	    }

	    public Book returnBBook(Long id) {
	        Book book = findBook(id);
	        if (book != null) {
	            book.setBuser(null);
	            return repositorioBook.save(book);
	        } else return null;
	    }
}
