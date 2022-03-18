package com.gabim.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.gabim.modelos.Book;
import com.gabim.servicios.ServicioBook;
import com.gabim.servicios.ServicioUsuario;


@Controller
public class ControladorBook {
	
	private final ServicioBook servicioBook;
	private final ServicioUsuario servicioUsuario;

	public ControladorBook(ServicioBook servicioBook, ServicioUsuario servicioUsuario) {
		this.servicioBook = servicioBook;
		this.servicioUsuario = servicioUsuario;
	}
	
	@RequestMapping( value="/books/new", method=RequestMethod.GET)
	public String despliegaRegistroBook(@ModelAttribute("book") Book nuevoBook , HttpSession session) {
		//System.out.println("si estoy");
		if(session.getAttribute("id") == null) {
			return "redirect:/";
		}
		else {
		return "newBook.jsp";
		}
	}

	@RequestMapping( value="/books/registrado", method=RequestMethod.POST)
	public String registrarBook(@Valid @ModelAttribute("book") Book nuevoBook, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			return "newBook.jsp";
		}
		else {
			Long usuarioSession = (Long)session.getAttribute("id");
			nuevoBook.setUsuario(servicioUsuario.findUsuario(usuarioSession));
			model.addAttribute("usuarioSession",usuarioSession);  // aqui envio id a nivel del servidor
			System.out.println(usuarioSession);
			servicioBook.insertIntoBooks(nuevoBook );
			return "redirect:/books";
		}
	}
	
	@RequestMapping( value = "/books/{id}", method= RequestMethod.GET)
	public String despliegaShow(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/";
		}
		else {
			Long usuarioSession = (Long)session.getAttribute("id");
			model.addAttribute("usuarioSession",usuarioSession);
			Book book = servicioBook.findBook(id);
			model.addAttribute("book", book);
			return "show.jsp";
		}
	}

	@RequestMapping( value = "/books/{id}/edit", method= RequestMethod.GET)
	public String despliegaEditBook(@PathVariable("id") Long id, HttpSession session,Model model) {
		if(session.getAttribute("id") == null) {
			return "redirect:/";
		}
		else {
			Book book = servicioBook.findBook(id);
	    	model.addAttribute("book", book);
			return "editBook.jsp";
		}	
	}
	
	@RequestMapping( value= "/books/editado", method= RequestMethod.PUT )
	public String updatedBook(@Valid @ModelAttribute("book") Book editadoBook,  BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			return "editBook.jsp";
		}
		else {
			session.setAttribute( "id", editadoBook.getId());
			session.setAttribute( "title", editadoBook.getTitle());
			session.setAttribute( "author", editadoBook.getAuthor());
			session.setAttribute( "thoughts", editadoBook.getThoughts());
			session.setAttribute( "usuario", editadoBook.getUsuario());
			
			
			//actualizar a traves del servicio y repositorio de la otra forma solo iba(editadoBook)
			servicioBook.updatedBook(editadoBook.getId(),
									editadoBook.getTitle(),
									editadoBook.getAuthor(),
									editadoBook.getThoughts(),
									editadoBook.getUsuario());
	
		return"redirect:/books";
		}
	}
	
	@RequestMapping( value="/bookmarket", method=RequestMethod.GET)
    public String bookmarket(Model model, HttpSession session) {
		
		Object userId = session.getAttribute("id");
        if (userId == null) { 
            System.out.println("aqui soy ERROR");
		return "redirect:/";}
        else {
            List<Book> books = servicioBook.allNBBooks();
            List<Book> bbooks = servicioBook.allBBooks( (Long) userId);
            model.addAttribute("books", books);
            model.addAttribute("bbooks", bbooks);
            model.addAttribute("name", session.getAttribute("name"));
            model.addAttribute("userId", userId);
            System.out.println("aqui soy EXCELENTE");
            return "bookmarket.jsp";
        }
    }
	
	@RequestMapping( value="/books/{id}/delete", method = RequestMethod.DELETE)
	public String eliminarBook(@PathVariable("id") Long id) {
		servicioBook.deleteBook(id);
		return "redirect:/bookmarket";
	}
	
	 
	
	@RequestMapping( value="/books/{id}/borrow", method=RequestMethod.POST)
    public String borrowBook(@PathVariable("id") Long id, HttpSession session) {
        if (servicioBook.borrowBook(id, session) == null) {
        	System.out.println("aqui soy un render BORROW");
        	return "bookmarket.jsp";}
        else {
        	System.out.println("aqui soy un redirect  BORROW");
        	return "redirect:/bookmarket";}
    }

	@RequestMapping( value="/books/{id}/return", method=RequestMethod.POST)
    public String returnBBook(@PathVariable("id") Long id) {
        if (servicioBook.returnBBook(id) == null) {
        	System.out.println("aqui soy un render RETURN");
        	return "bookmarket.jsp";}
        else {
        	System.out.println("aqui Esoy un redirect  RETURN");
        	return "redirect:/bookmarket";}
    }
}
