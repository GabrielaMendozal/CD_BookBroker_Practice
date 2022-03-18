package com.gabim.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table (name = "usuarios" )
public class Usuario {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotNull
	@NotEmpty (message= "Name is required!")
	@Size(min=3, message= "Solo letras y minimo 3 caracteres" )
	private String name;
	
	@NotEmpty(message="¡Email is required!")
    @Email(message="¡Ingrese un Email válido!")
    private String email;
	
	@NotNull
	@Size( min = 8, max = 100, message = "Password must be between 8 and 128 characters")
	private String password;
	
	@Transient
	@NotEmpty(message="Confirm Password is required!")
	@Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
	private String confirm;
	
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat (pattern="yyyy-MM-dd")
    private Date updatedAt;

    @OneToMany (mappedBy = "usuario",  fetch = FetchType.LAZY) //aqui agregué
	private List<Book> listaBooks;

    
    @OneToMany(mappedBy = "buser",  fetch = FetchType.LAZY)
    private List<Book> bbooks = new ArrayList<>();
    
	public Usuario() {
	}

	public Usuario(String name,String email, String password, String confirm) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Book> getListaBooks() {
		return listaBooks;
	}

	public void setListaBooks(List<Book> listaBooks) {
		this.listaBooks = listaBooks;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
    
    
}
