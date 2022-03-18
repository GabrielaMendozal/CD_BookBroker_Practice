package com.gabim.modelos;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table (name = "books")
public class Book {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotNull
	@NotEmpty (message= "Title is required!")
	@Size(min=3, message= "Solo letras y minimo 3 caracteres" )
	private String title;
	
	@NotNull
	@NotEmpty (message= "Author is required!")
	@Size(min=3, message= "Solo letras y minimo 3 caracteres" )
	private String author;
	
	@NotNull
	@NotEmpty (message= "Thoughts is required!")
	@Size(min=3, message= "Solo letras y minimo 3 caracteres" )
	private String thoughts;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat (pattern="yyyy-MM-dd")
    private Date updatedAt;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn( name = "usuario_id")
	private Usuario usuario;
	
	
	@ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "buser_id")
    private Usuario buser;

	public Book() {
	}

	public Book( String title, String author, String thoughts,Usuario usuario) {
		this.title = title;
		this.author = author;
		this.thoughts = thoughts;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getThoughts() {
		return thoughts;
	}

	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Usuario getBuser() {
		return buser;
	}

	public void setBuser(Usuario buser) {
		this.buser = buser;
	}
	
	
}
