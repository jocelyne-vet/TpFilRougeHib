<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mon établissement</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/etablissement.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/menuclient.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/message.css">
</head>
<body>
	<%@ include file="fragments/fragmentMenu.jsp"%>
	<%@ include file="fragments/fragmentMessage.jsp" %>
	<div class="content">

		<div class="infoCinema">
			<figure>
				<img src="${cinema.affiche }" alt="Mon établissement" width="300">
				<figcaption>
					<c:out value="${cinema.nom}"></c:out>
				</figcaption>
			</figure>
			<div class="adresse">
				<span>Adresse : <c:out value="${cinema.adresse.numero }"></c:out>
					<c:out value="${cinema.adresse.typeRue }"></c:out> <c:out
						value="${cinema.adresse.nomRue }"></c:out> <c:out
						value="${cinema.adresse.cpo }"></c:out>
				</span> <span><c:out value="${cinema.adresse.ville }"></c:out></span>
			</div>

		</div>
		<div class="infoSalles">
			<h1>Mes salles</h1>
			<%-- <p>
				<c:out value="${message }">
				</c:out>
			</p> --%>
			<c:if test="${ cinema.salles.size() ge 1}">
				<c:forEach items="${cinema.salles}" var="current">
					<div>

						<div class="imgSalle">
							<img src="img/imgCinema/salle.jpg" alt="Salle de cinema"
								width="70">
						</div>
						<div class="descSalle">

							<span class="descSalle2"> Salle numéro <c:out
									value="${current.numero }"></c:out> :<c:out
									value="${current.nombreDePlaces }"></c:out> places
							</span>
						</div>
						<div class="actions">
							<form action="${pageContext.request.contextPath}/formulaireSalle"
								method="get">
								<input type="hidden" name="afficheCinema" id="afficheCinema"
									value="${cinema.affiche }"> <input type="hidden"
									name="idCinema" id="idCinema" value="${cinema.id }"> <input
									type="hidden" name="idSalle" id="idSalle"
									value="${current.id }">
								<!-- <input type="submit" value="Modifier" class="btn"> -->
								<input type="image" src="img/modifier.jpg" alt="modifier"
									width="40">

							</form>
							<form action="${pageContext.request.contextPath}/supprimerSalle"
								method="post"
								onSubmit="return confirm('Voulez-vous supprimer la salle numéro ${current.numero }?');">
								<input type="hidden" name="numero" id="numero"
									value="${current.numero }"> <input type="hidden"
									name="idSalle" id="idSalle" value="${current.id }">
								<!-- <input type="submit"
								value="Supprimer" class="btn" > -->
								<input type="image" src="img/croix.jpg" alt="supprimer"
									width="40">

							</form>
							<form
								action="${pageContext.request.contextPath}/consulterFilmsSalle"
								method="get">
								<input type="hidden" name="idSalle" id="idSalle"
									value="${current.id }">
								<!-- <input type="submit"
								value="Liste des films" class="btn"> -->
								<input type="image" src="img/film.jpg" alt="liste de films"
									width="50">
							</form>

						</div>

					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="ajoutSalle">
			<form action="${pageContext.request.contextPath}/formulaireSalle"
				method="get">
				<input type="submit" value="Ajouter une salle" class="btn">
				<input type="hidden" name="afficheCinema" id="afficheCinema"
					value="${cinema.affiche }"> <input type="hidden"
					name="idCinema" id="idCinema" value="${cinema.id }">
			</form>
		</div>

	</div>

</body>
</html>