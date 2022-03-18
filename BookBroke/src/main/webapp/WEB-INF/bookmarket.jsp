<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Book Lender Dashboard</title>
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	</head>
	<body>
		<div class="row mt-2">
            <div class="col-6 offset-3">
                <%-- header --%>
                <div class="col d-flex justify-content-between">
                    <span>Hello, ${name}. Welcome to...</span>
                    <%-- logout --%>
                    <div class="align-self-center">
                        <a href="/books" class="btn btn-sm btn-outline-warning">back to shelves</a>
                    </div>
                </div>
                    <div class="col d-flex justify-content-between">
                        <span class="fs-1 fw-bold align-self-center">The Book Broker!</span>
                    </div>
            </div>
        </div>
        <%-- body --%>
        <%-- Available Books to Borrow Table --%>
        <div class="col-6 offset-3 mt-3">
            <span class="align-self-center">Available Books to Borrow</span>
        </div>
        <div class="container border col-6 mt-3">
            <div class="container col-12 bg-white p-3">
                <table class="table table-striped p-2">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Title</th>
                            <th scope="col">Author Name</th>
                            <th scope="col">Owner</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${books}">
                            <tr>
                                <td><c:out value="${book.id}" /></td>
                                <td><a href="/books/${book.id}"><c:out value="${book.title}" /></a></td>
                                <td><c:out value="${book.author}" /></td>
                                <td><c:out value="${book.getUsuario().getName()}" /></td>
                                <td>
                                    <c:if test="${(book.buser.id eq null) and (book.usuario.id ne userId)}">
                                        <form action="/books/${book.id}/borrow" method="post">
                                            <button type="submit" class="btn btn-sm btn-outline-info">borrow</button>
                                        </form>
                                    </c:if>
                                    <c:if test="${book.getUsuario().getId() eq userId}">
                                        <div class="btn-group" role="group">
                                            <form action="/books/${book.id}/edit">
                                                <button class="btn btn-sm btn-outline-primary">edit</button>
                                            </form>
                                            <form action="/books/${book.id}/delete" method="post">
                                                <input type="hidden" name="_method" value="delete">
                                                <button type="submit" class="btn btn-sm btn-outline-danger">delete</button>
                                            </form>
                                        </div>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- Books I'm Borrowing Table --%>
        <div class="col-6 offset-3 mt-5">
            <span class="align-self-center">Books I'm Borrowing...</span>
        </div>
        <div class="container border col-6 mt-3">
            <div class="container col-12 bg-white p-3">
                <table class="table table-striped p-2">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Title</th>
                            <th scope="col">Author Name</th>
                            <th scope="col">Owner</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="bbook" items="${bbooks}">
                            <tr>
                                <td><c:out value="${bbook.id}" /></td>
                                <td><a href="/books/${bbook.id}"><c:out value="${bbook.title}" /></a></td>
                                <td><c:out value="${bbook.author}" /></td>
                                <td><c:out value="${bbook.usuario.name}" /></td>
                                <td>
                                    <form action="/books/${bbook.id}/return" method="post">
                                        <button type="submit" class="btn btn-sm btn-outline-info">return</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
	</body>
</html>