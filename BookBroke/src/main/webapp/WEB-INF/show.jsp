<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Read Share</title>
		<link rel="stylesheet" href="/css/style.css"/>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div>
					<h1>${book.getTitle()}</h1>
				</div>
				<div>
					<a href="/books">back to the shelves</a>
				</div>
				
				<div>
				<c:choose>
			    <c:when test="${book.getUsuario().getId().equals(usuarioSession)}">
			        <h4><span class="name">You</span> read
					<span class="book">${book.getTitle()} </span> by
					<span class="author">${book.getAuthor()}.</span></h4>
					<h5>Here are your's thoughts:</h5>
					<p>${book.getThoughts()}
					</p> 
			        <br />
			    </c:when>    
			    <c:otherwise>
			       <span class="name">"${book.getUsuario().getName()}"</span> read
					<h4><span class="book">${book.getTitle()} </span> by
					<span class="author">${book.getAuthor()}.</span></h4>
					<h5>Here are ${book.getUsuario().getName()}'s thoughts:</h5>
					<p>${book.getThoughts()}
					</p>	
			    </c:otherwise>
				</c:choose>
				</div>
				<c:if test = "${book.getUsuario().getId().equals(usuarioSession)}">
					<form action="/books/${book.getId()}/edit">
						<button type="submit" class="btn btn-primary">
							Editar
						</button>
					</form>
				</c:if>
				
			</div>
			
			
		</div>
		
		
	</body>
</html>
		