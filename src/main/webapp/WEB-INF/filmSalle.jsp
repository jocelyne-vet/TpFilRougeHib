<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Film</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/filmSalle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/message.css">
</head>
<body>
	<%@ include file="fragments/fragmentMenu.jsp"%>
	<%@ include file="fragments/fragmentMessage.jsp"%>
	<div class="content">
		<div class="film">
			<div class="info">
				<p>
				<h2>
					<c:out value="${film.nom }"></c:out>
				</h2>
				<p>
					<span class="contentInfo">Durée :</span>
					<c:out value="${film.duree }"></c:out>
					min

				</p>
				<span class="contentInfo">Synopsis :</span>
				<p><c:out value="${film.description }"></c:out></p>
				<span class="contentInfo">Age minimum :</span><c:out value="${film.ageMinimum }"></c:out> ans
			</div>
			<c:if test="${empty film.affiche }">
				<img src="img/imgFilm/affichevide.jpg" alt="Affiche du film"
					width="300">
			</c:if>
			<c:if test="${!empty film.affiche }">
				<img src="${film.affiche }" alt="Affiche du film" width="300">
			</c:if>
			<div class="action">
				<form action="${pageContext.request.contextPath}/formulaireFilm" method="get" >
					<input type="hidden" name="idFilm" value="${film.id }">
					<input type="hidden" name="idSalle" value= "${idSalle }">
					<input type="submit" value="Modifier" id="modifier">
				</form>
				<form action="${pageContext.request.contextPath}/supprimerFilm" method="post" onSubmit = "return confirm('Voulez-vous supprimer le film ${film.nom }?');">
					<input type="hidden" name="idFilm" value="${film.id}">
					<input type="hidden" name="idSalle" value= "${idSalle }">
					<input type="hidden" name="nomFilm" value= "${ film.nom }">
					<input type="submit" value="Supprimer" id="supprimer">
				</form>
			</div>


		</div>
		<div>
			<h2>Prochaines séances</h2>

			<div class="seances">
				<c:forEach items="${seances}" var="currentSeance" varStatus="status">
					<c:if test="${ status.index eq 0}">

						<h3>

							<fmt:formatDate type="date" dateStyle="long" timeStyle="long"
								value="${currentSeance.heureDebutDate}" />

						</h3>

						<div class="blocseance">
							<c:out value="${currentSeance.getHeureDebut().toLocalTime() }"></c:out>

						</div>

					</c:if>
					<c:if test="${ status.index gt 0}">

						<c:if
							test="${currentSeance.heureDebut.toLocalDate().compareTo(seances.get(status.index-1).heureDebut.toLocalDate()) ne 0 }">
							<h3>
								<%-- <c:out value="${currentSeance.heureDebut.toLocalDate() }"></c:out> --%>
								<fmt:formatDate type="date" dateStyle="long" timeStyle="long"
									value="${currentSeance.heureDebutDate}" />
							</h3>
							<div class="blocseance">
								<c:out value="${currentSeance.getHeureDebut().toLocalTime() }"></c:out>
							</div>
						</c:if>


						<c:if
							test="${currentSeance.heureDebut.toLocalDate().compareTo(seances.get(status.index-1).heureDebut.toLocalDate()) eq 0 }">

							<div class="blocseance">
								<c:out value="${currentSeance.getHeureDebut().toLocalTime() }"></c:out>
							</div>
						</c:if>

					</c:if>
				</c:forEach>

			</div>
		</div>
	</div>

</body>
</html>