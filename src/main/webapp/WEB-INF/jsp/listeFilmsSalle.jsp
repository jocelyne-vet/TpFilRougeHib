<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listes des films de la salle</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/listeFilmsSalle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/message.css">
</head>
<body>
	<%@ include file="../fragments/fragmentMenu.jsp"%>
	<%@ include file="../fragments/fragmentMessage.jsp"%>
	<div class="content">
		<div>
			<h1>Liste des films programmés de la salle <c:out value = "${salle.numero }"></c:out> :</h1>
			<span class="espace">&nbsp;</span>
			<form action="${pageContext.request.contextPath}/formulaireFilm" method="get" id="ajouterForm">
				<input type="hidden" name="idSalle" value="${salle.id }"> <input type="submit"
					value="Ajouter un film">
			</form>
		</div>
		<%-- <c:if test="${empty films }">
			<p>Il n'y a pas de films programmés dans cette salle.</p>
		</c:if> --%>
		<c:forEach items="${films}" var="currentFilm">


			<div class="film">
				<form action="${pageContext.request.contextPath}/consultFilmSalle">
					<input type="hidden" name="idSalle" id="idSalle" value="${salle.id }">
					<input type="hidden" name="idFilm" id="idFilm" value="${currentFilm.id }">
					<c:if test="${empty currentFilm.affiche }">
						<input type="image" src="img/imgFilm/afficheVide.jpg" alt="Submit"
						width="175" height="250">
					</c:if>
					<c:if test="${!empty currentFilm.affiche }">
					<input type="image" src="${currentFilm.affiche }" alt="Submit"
						width="175" height="250">
					</c:if>
					<h2><c:out value="${currentFilm.nom }"></c:out></h2>
				</form>

			</div>
		</c:forEach>



	</div>

</body>
</html>