<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulaire salle</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/formSalle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/message.css">
  <script src="js/formSalle.js"></script>
</head>
<body>
<%@ include file="fragments/fragmentMenu.jsp"%>
<%@ include file="fragments/fragmentMessageErreur.jsp"%>
	<div class="content">

		<div class="infoCinema">
			<figure>
				<img src="${afficheCinema }" alt="Mon établissement"
					width="300">
			</figure>

		</div>
		<div class="formSalle">
			<form action="${pageContext.request.contextPath}/formulaireSalle"  method="post" onsubmit="return valider()">
				<h1>Votre salle</h1>
				<%-- <p class="message"><c:out value = "${ message}"></c:out></p> --%>
				<div class="attrSalle">
					<label for="numero">Numéro : </label> <input type="number"
						name="numero" id="numero" min="0" value="${salle.numero }">
				</div>
				<div class="attrSalle">
					<label for="nbplaces">Nb de places : </label> <input type="number"
						name="nbplaces" id="nbplaces" min="0" value ="${salle.nombreDePlaces }">
				</div>
				<div class="attrSalle">
					<input type = "hidden" name="idCinema" id="idCinema" value ="${idCinema }">
					<input type = "hidden" name="idSalle" id="idSalle" value ="${salle.id}">
					<input type = "hidden" name="afficheCinema" id="afficheCinema" value ="${afficheCinema }">
					<input type="submit" value="Enregistrer" class="btn">
				</div>
				
			</form>
		</div>

	</div>
</body>
</html>