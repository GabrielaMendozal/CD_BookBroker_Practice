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
				<h1 class="title">Book Club</h1>
				<h4>A place for friends to share thoughts  of books</h4>
				
				<div class="col">
					<h3>Register</h3>
					<form:form method="POST" action="/registrado" modelAttribute= "usuario" >
						<div class="mb-3">
							<form:label  path= "name" for="name">
								Name:
							</form:label>
							<form:input path="name" type="text" name="name" id="name" />
							<form:errors path="name" />
						</div>
						<div class="mb-3">
							<form:label  path= "email" for="email">
								Email:
							</form:label>
							<form:input path="email" type="text" name="email" id="email" />
							<form:errors path="email" />
						</div>
						<div class="mb-3">
							<form:label  path= "password" for="password">
								Password:
							</form:label>
							<form:input path="password" type="password" name="password" id="password"/>
							<form:errors path="password" />
						</div>
						<div class="mb-3">
							<form:label  path= "confirm" for="confirm">
								Confirm PW:
							</form:label>
							<form:input path="confirm" type="password" name="confirm" id="confirm"/>
							<form:errors path="confirm" />
						</div>
						<button class="btn btn-primary" type="submit">
							Submit
						</button>
					</form:form>
				</div>
				
				<div class="col" >
					<h3>Log in</h3>				
					<form method="POST" action="/login">
						<div class="mb-3">
							<label   for="emailLogin">
								Email:
							</label>
							<input  type="text" name="email" id="emailLogin" /><c:out value= "${errorLoginEmail}"/>
							
						</div>
						<div class="mb-3">
							<label   for="passwordLogin">
								Password:
							</label>
							<input  type="password" name="password" id="passwordLogin"/><c:out value= "${errorLoginPassword}"/>
						</div>
						<button type="submit" class="btn btn-primary">
							Submit
						</button>
					</form>
					<div>
						<c:out value= "${LoginError}"/>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>