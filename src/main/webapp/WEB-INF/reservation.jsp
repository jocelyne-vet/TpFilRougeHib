<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservation de places</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reserver.css">
<script src="js/reservation.js"></script>
</head>
<body>

	<%@ include file="fragments/fragmentMenu.jsp"%>

	<div class="content">
		<h1>Ma réservation</h1>
		
		<div class="film">
			<figure>
				<img src="${seance.film.affiche }" alt="" width="350">
				<figcaption>
					<c:out value="${seance.film.nom }"></c:out>
				</figcaption>
			</figure>
			<div class="info">
				<p>
					Salle :
					<c:out value="${seance.salle.numero }"></c:out>
				</p>
				<p>
					Durée :
					<c:out value="${seance.film.duree }"></c:out>
				</p>
				<p>
					Age minimum :
					<c:out value="${seance.film.ageMinimum }"></c:out>
				</p>
				<div class="blocseance">
					<p>
						Seance de :
						<c:out value = "${seance.getHeureDebut().toLocalTime() }"></c:out>
					</p>
				</div>
			</div>

		</div>
		<div class="reservation">
			<h2>Combien de places voulez-vous réserver?</h2>
			<p class="message"> <c:out value="${messageErreur }"></c:out></p>
			<form action="reserver" method="post" onsubmit="return valider('${sessionScope.user.dateNaissance}',${seance.film.ageMinimum})">
				<div class="res">
					<label for="nbdeplacesres">Nombres de places réservées</label> <input
						type="number" name="nbdeplacesres" id="nbdeplacesres" min="1" value="${nbdeplacesres }" >
				</div>
				<div class="res">
					<input type="hidden" id="isseance" name="idseance"
						value="${seance.id }"> <input type="submit"
						value="Commander">
				</div>
			</form>
		</div>

	</div>

</body>
</html>