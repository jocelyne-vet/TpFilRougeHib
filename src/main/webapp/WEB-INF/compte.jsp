<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Je m'inscris</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/compte.css">
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
		<p>Je m'inscris :</p>
		<form action="${pageContext.request.contextPath}/compte" method="post">
			<div class="info">
				<label for="nom">Nom* : </label><input type="text" name="nom"
					id="nom" required="required" value="${sessionScope.user.nom }">
			</div>
			<div class="info">
				<label for="prenom">Prenom* : </label><input type="text"
					name="prenom" id="prenom" required="required"
					value="${sessionScope.user.prenom }">
			</div>
			<div class="info">
				<label for="dateNaissance">Date de naissance* : </label><input
					type="date" name="dateNaissance" id="dateNaissance"
					required="required" value="${sessionScope.user.dateNaissance }">
			</div>
			<div class="info">
				<label for="email">Email* : </label><input type="email" name="email"
					id="email" required="required" value="${sessionScope.user.email }">
			</div>
			<div class="info">
				<label for="pwd">Mot de passe* : </label><input type="password"
					name="pwd" id="pwd" required="required"
					value="${sessionScope.user.motdePasse }">
			</div>

			<div class="info">
				<label for="numero_voie">Numéro de voie* : </label><input
					type="number" name="numero_voie" id="numero_voie"
					required="required" value="${sessionScope.user.adresse.numero }">
			</div>
			<div class="info">
				<label for="type_voie">Type de voie* : </label> <input type="text"
					name="type_voie" id="type_voie" placeholder="rue"
					required="required" value="${sessionScope.user.adresse.typeRue }">
			</div>
			<div class="info">
				<label for="nom_voie">Nom de la voie* : </label><input type="text"
					name="nom_voie" id="nom_voie" placeholder="nom de la rue"
					required="required" value="${sessionScope.user.adresse.nomRue }">
			</div>
			<div class="info">
				<label for="code_postal">Code Postal* : </label><input type="text"
					name="code_postal" id="code_postal" required="required"
					value="${sessionScope.user.adresse.cpo }">
			</div>
			<div class="info">
				<label for="ville">Ville* : </label><input type="text" name="ville"
					id="ville" value="${sessionScope.user.adresse.ville }">
			</div>


			<div class="info">
				<input type="hidden" id="idAdresse" name="idAdresse"
					value="${sessionScope.user.adresse.id }"> <input
					type="hidden" id="idPersonne" name="idPersonne"
					value="${sessionScope.user.id}"> <input type="hidden"
					id="role" name="role" value="${sessionScope.user.roleLibelle}">
				<input type="submit" value="Valider">

			</div>
		</form>
		<c:if test="${!empty sessionScope.user }">
			<div id="supprimer">
				<form
					action="${pageContext.request.contextPath}/supprimerUtilisateur"
					method="post">
					<input type="hidden" id="idPersonne" name="idPersonne"
						value="${sessionScope.user.id}"> <input type="hidden"
						id="page" name="page"  value="compte">
					<input type="submit" value="Supprimer">

				</form>

			</div>
		</c:if>
	</div>
</body>
</html>