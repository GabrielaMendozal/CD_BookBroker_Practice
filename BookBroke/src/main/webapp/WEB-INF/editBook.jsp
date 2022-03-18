<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Book Share</title>
		<link rel="stylesheet" href="/css/style.css"/>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="row">
					<h1 class="title">Change your Entry</h1>
					<a href="/books">back to the shelves</a>
				</div>
				<div class="col">
					<form:form method="POST" action="/books/editado" modelAttribute= "book" >
					<input type="hidden" name="_method" value="PUT"/>
						
						<div class="mb-3">
							<form:label  path= "title" for="title">
								Title
							</form:label>
							<form:input path="title" type="text" name="title" id="title" />
							<form:errors path="title" />
						</div>
						<div>
							<form:input path="usuario" type="hidden" value="${book.getUsuario().getId()}"/>
							<form:input path="id" type="hidden" value="${book.getId()}"/>
							
						</div>
						<div class="mb-3">
							<form:label  path= "author" for="author">
								Author
							</form:label>
							<form:input path="author" type="text" name="author" id="author" />
							<form:errors path="author" />
						</div>
						<div class="mb-3">
							<form:label  path= "thoughts" for="thoughts">
								My thoughts
							</form:label>
							<form:input path="thoughts" type="text" name="thoughts" id="thoughts" />
							<form:errors path="thoughts" />
						</div>
						
						<button class="btn btn-primary" type="submit">
							Submit
						</button>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>