<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulaire cinéma</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compte.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/menuclient.css">
</head>
<body>
	<%@ include file="fragments/fragmentMenu.jsp"%>
	<div class="par">
		<h3>Les cinémas</h3>
		<h1>Rilletthes</h1>
	</div>
	<div class="content">
		<p>Mon cinéma :</p>
		<form action="${pageContext.request.contextPath}/formulaireCinema" method="post">
			<div class="info">
				<label for="nom">Nom* : </label><input type="text" name="nom"
					id="nom" required="required" value="${cinema.nom }">
			</div>
			<div class="info">
				<label for="gerant">Gérant* : </label> <select name="gerant"
					id="gerant" >
					<c:forEach items="${gerants}" var="currentGerant">
						<c:if test="${currentGerant.id eq cinema.gerant.id }">
							<option value="${currentGerant.id }" selected>
								<c:out value="${currentGerant.nom }"></c:out>
								<c:out value="${currentGerant.prenom }"></c:out></option>
						</c:if>
						<c:if test="${currentGerant.id ne cinema.gerant.id }">
							<option value="${currentGerant.id }" >
								<c:out value="${currentGerant.nom }"></c:out>
								<c:out value="${currentGerant.prenom }"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="info">
				<label for="affiche">Affiche : </label><input type="url"
					name="affiche" id="affiche" value="${cinema.affiche }">
			</div>


			<div class="info">
				<label for="numero_voie">Numéro de voie* : </label><input
					type="number" name="numero_voie" id="numero_voie"
					required="required" value = "${cinema.adresse.numero}">
			</div>
			<div class="info">
				<label for="type_voie">Type de voie* : </label> <input type="text"
					name="type_voie" id="type_voie" placeholder="rue"
					required="required" value = "${cinema.adresse.typeRue}">
			</div>
			<div class="info">
				<label for="nom_voie">Nom de la voie* : </label><input type="text"
					name="nom_voie" id="nom_voie" placeholder="nom de la rue"
					required="required" value = "${cinema.adresse.nomRue}">
			</div>
			<div class="info">
				<label for="code_postal">Code Postal* : </label><input type="text"
					name="code_postal" id="code_postal" required="required" value = "${cinema.adresse.cpo}">
			</div>
			<div class="info">
				<label for="ville">Ville* : </label><input type="text" name="ville"
					id="ville" value = "${cinema.adresse.ville}">
			</div>



			<div class="info">
				<input type="submit" value="Valider">
				<input type="hidden" name="idCinema" value="${cinema.id}">
				<input type="hidden" name="idAdresse" value="${cinema.adresse.id}">
			</div>
		</form>
	</div>

</body>
</html>