package com.gabim.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gabim.modelos.Book;
import com.gabim.modelos.Usuario;
import com.gabim.servicios.ServicioBook;
import com.gabim.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {
	
	private final ServicioUsuario servicioUsuario;
	private final ServicioBook servicioBook;
	
	public ControladorUsuario(ServicioUsuario servicioUsuario, ServicioBook servicioBook) {
		this.servicioUsuario = servicioUsuario;
		this.servicioBook = servicioBook;
	}
	
	@RequestMapping( value="/", method=RequestMethod.GET)
	public String despliegaRegistroyLogin(@ModelAttribute("usuario") Usuario nuevoUsuario) {
		//System.out.println("si estoy");
		return "index.jsp";
	}
	
	@RequestMapping( value= "/registrado", method = RequestMethod.POST)
	public String registrarUsuario(@Valid @ModelAttribute("usuario")Usuario nuevoUsuario,BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "index.jsp";
		}
		else {
			//String hash = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
			//System.out.println (hash);
			//nuevoUsuario.setPassword(hash);
			Usuario usuarioObtenido = servicioUsuario.insertIntoUsuarios(nuevoUsuario, result);//en nuevo usuario va password tecleado previo a setter debo enciptar 
			if(usuarioObtenido == null) {
				return "index.jsp";
			}
			session.setAttribute("id", nuevoUsuario.getId());
			//System.out.println(nuevoUsuario.getId());
			session.setAttribute( "name", nuevoUsuario.getName());
			session.setAttribute( "email", nuevoUsuario.getEmail());
			session.setAttribute( "password", nuevoUsuario.getPassword());
			return "redirect:/books";
		}
			
	}
	
	@RequestMapping( value = "/books", method = RequestMethod.GET)
	public String despliegaDashboard(Model model, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/";
		}
		else {
			Long usuarioEncontrado = (Long) session.getAttribute("id");
			Usuario usuario = servicioUsuario.findUsuario(usuarioEncontrado);
	    	model.addAttribute("usuario", usuario);
	    	List<Book> listaBooks = servicioBook.selectFromAllBooks();
	    	model.addAttribute("listaBooks",listaBooks);
			return "dashboard.jsp";
		}
	}
	
	@RequestMapping( value = "/logout", method = RequestMethod.GET)
	public String logout(  HttpSession session) {
		session.removeAttribute("id");
		session.removeAttribute("name");
		session.removeAttribute("email");
		session.removeAttribute("password");
		return "redirect:/";
	}
	
	@RequestMapping( value= "/login", method =RequestMethod.POST)
	public String login(@RequestParam( value = "email") String email,
						@RequestParam( value = "password") String password,
						HttpSession session,
						RedirectAttributes flash) {
		
		Usuario usuarioEncontrado = servicioUsuario.selectUsuarioWhereEmail(email);
		if( usuarioEncontrado == null) {
			if (email.equals("")) {
				flash.addFlashAttribute("errorLoginEmail", "Por favor proporciona tu email.");
			}
			if (password.equals("")) {
				flash.addFlashAttribute("errorLoginPassword", "Por favor proporciona tu password de usuario.");
			}
			flash.addFlashAttribute("LoginError", "Credenciales incorrectas." );
			//System.out.println("Credenciales invalidas");
			return "redirect:/";
		}
		else {
			if (BCrypt.checkpw(password, usuarioEncontrado.getPassword())) {
				//System.out.println("id " + usuarioEncontrado.getId());
				//System.out.println("userName " + usuarioEncontrado.getUserName());
				session.setAttribute( "id", usuarioEncontrado.getId());
				session.setAttribute( "name", usuarioEncontrado.getName());
				session.setAttribute( "email", usuarioEncontrado.getEmail());
				session.setAttribute( "password", usuarioEncontrado.getPassword());
				return "redirect:/books";
			}
			else {
				flash.addFlashAttribute("LoginError", "Credenciales incorrectas." );
				//System.out.println("Credenciales invalidas");
				return "redirect:/";
			}	
		}	
	}
}
