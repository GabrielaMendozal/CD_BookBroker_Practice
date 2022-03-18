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
				<div class="col-7">
					
					<h1 class="title">Welcome,<c:out value = "${usuario.getName()}"></c:out> </h1>
					
					<h4>This is your dashboard. Nothing to see here yet </h4>
				</div>
				<div class="col">
					<div class="col-2">
						<a href="/logout"> Logout </a>
					</div>
					<div class="col-4">
						<a href="/books/new">+ Add to my shelf!</a>
					</div>	
					<div class="col-4">
						<a href="/bookmarket">bookmarket</a>
					</div>					
				</div>
			</div>
			<div class="table-responsive">
					<table class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col">ID</th>
					      <th scope="col">Title</th>
					      <th scope="col">Author Name</th>
					      <th scope="col">Posted By</th>
					    </tr>
					  </thead>
					  <tbody>
						  <c:forEach var="book" items="${listaBooks}">
							<tr>
							  <td>${book.getId()}</td>	
						      <td><a href="/books/${book.id}">${book.getTitle()}</a></td>
						      <td>${book.getAuthor()}</td>
						      <td>${book.getUsuario().getName()}</td>
						    </tr>
						</c:forEach>
					  </tbody>
					</table>
				</div>
		</div>
	</body>
</html>