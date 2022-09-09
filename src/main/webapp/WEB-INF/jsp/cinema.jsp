<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cinema</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cinema.css">
</head>
<body>
	<%@ include file="../fragments/fragmentMenu.jsp"%>

	<c:if test="${empty cinema.nom }">
		<p>Il n'y a pas de films associer à ce cinéma pour l'instant.</p>
	</c:if>
	<c:if test="${!empty cinema.nom }">
		<div class="recherche">

			<form action="${pageContext.request.contextPath}/cinema"
				method="post">
				<h1>${cinema.nom}:</h1>
				<input type="hidden" name="nomCinema" id="nomCinema"
					value="${cinema.nom }"> <input type="hidden"
					name="idCinema" id="idCinema" value="${cinema.id }"> <input
					type="search" name="critere" id="critere"
					placeholder="Entrer le nom d'un film" size="100">
			</form>
		</div>
		<c:if test="${!empty films }">
			<div class="content">
				<c:forEach items="${films}" var="currentFilm">

					<div class="film">
						<form action="${pageContext.request.contextPath}/film"
							method="get">

							<c:if test="${empty currentFilm.affiche }">
								<img src="img/imgFilm/affichevide.jpg" alt="Affiche du film"
									width="250">
							</c:if>
							<c:if test="${!empty currentFilm.affiche }">
								<img src="${currentFilm.affiche }" alt="Affiche du film"
									width="250">
							</c:if>

							<div class="resume">
								<input type="hidden" name="idcinema" id="idcinema"
									value="${cinema.id }"> <input type="hidden"
									name="idfilm" id="idfilm" value="${currentFilm.id }"> <input
									type="submit" value="${currentFilm.nom }" class="btnFilm">

								<p>
									<c:out value="${currentFilm.description }"></c:out>
								</p>
							</div>
						</form>
					</div>

				</c:forEach>
			</div>
		</c:if>
	</c:if>


</body>
</html>