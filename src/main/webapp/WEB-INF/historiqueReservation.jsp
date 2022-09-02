<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Historique des réservations</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/historiqueReservation.css">
</head>
<body>
	<%@ include file="fragments/fragmentMenu.jsp"%>

	<c:if test="${empty reservations }">
		<p>Vous n'avez pas encore fait une réservation</p>
	</c:if>
	<c:if test="${!empty reservations }">
		<div class="content">
			<div>
				<div class="caption">Historique des réservations</div>
				<div class="thead">

					<div class="th">Nom du film</div>
					<div class="th">Date de la séance</div>
					<div class="th">Heure de la séance</div>
					<div class="th">Nb de places réservées</div>
				</div>
				<div class="tbody">
					<c:forEach var="reservation" items="${reservations}">
						<div class="tr">

							<div class="td">
								<c:out value="${reservation.key.film.nom}"></c:out>
							</div>
							<div class="td">
								<fmt:formatDate type="date" dateStyle="long" timeStyle="long"
									value="${reservation.key.getHeureDebutDate()}" />
							</div>
							<div class="td">
								<c:out value="${reservation.key.getHeureDebut().toLocalTime()}"></c:out>
							</div>
							<div class="td">
								<c:out value="${reservation.value}"></c:out>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:if>
</body>
</html>