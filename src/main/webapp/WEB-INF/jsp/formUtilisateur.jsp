<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulaire utilisateur</title>
<link rel="stylesheet" href="css/compte.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/menuclient.css">
</head>
<body>
	<%@ include file="../fragments/fragmentMenu.jsp"%>
	<div class="content">
		<h1>Formulaire Utilisateur :</h1>
		<form action="${pageContext.request.contextPath}/formulaireUtilisateur" method="post">
			<div class="info">
				<label for="nom">Nom* : </label><input type="text" name="nom"
					id="nom" required="required" value="${utilisateur.nom }">
			</div>
			<div class="info">
				<label for="prenom">Prenom* : </label><input type="text"
					name="prenom" id="prenom" required="required"
					value="${utilisateur.prenom }">
			</div>
			<div class="info">
				<label for="dateNaissance">Date de naissance* : </label><input type="date"
					name="dateNaissance" id="dateNaissance" required="required"
					value="${utilisateur.dateNaissance }">
			</div>
			<div class="info">
				<label for="email">Email* : </label><input type="email" name="email"
					id="email" required="required" value="${utilisateur.email }">
			</div>
			<div class="info">
				<label for="pwd">Mot de passe* : </label><input type="password"
					name="pwd" id="pwd" required="required"
					value="${utilisateur.motdePasse }">
			</div>

			<div class="info">
				<label for="numero_voie">Numéro de voie* : </label><input
					type="number" name="numero_voie" id="numero_voie"
					required="required" value="${utilisateur.adresse.numero }">
			</div>
			<div class="info">
				<label for="type_voie">Type de voie* : </label> <input type="text"
					name="type_voie" id="type_voie" placeholder="rue"
					required="required" value="${utilisateur.adresse.typeRue }">
			</div>
			<div class="info">
				<label for="nom_voie">Nom de la voie* : </label><input type="text"
					name="nom_voie" id="nom_voie" placeholder="nom de la rue"
					required="required" value="${utilisateur.adresse.nomRue }">
			</div>
			<div class="info">
				<label for="code_postal">Code Postal* : </label><input type="text"
					name="code_postal" id="code__postal" required="required"
					value="${utilisateur.adresse.cpo }">
			</div>
			<div class="info">
				<label for="ville">Ville* : </label><input type="text" name="ville"
					id="ville" value="${utilisateur.adresse.ville }">
			</div>

			<div>
				<label for="role">Role :</label> <select name="role" id="role">
					<c:if test="${empty utilisateur}">
						<option value="Client" selected>Client</option>
						<option value="Gerant">Gérant</option>
						<option value="Administrateur">Administrateur</option>
					</c:if>
					<c:if test="${utilisateur.roleLibelle eq applicationScope.roleC }">
						<option value="Client" selected>Client</option>
						<option value="Gerant">Gérant</option>
						<option value="Administrateur">Administrateur</option>
					</c:if>
					<c:if test="${utilisateur.roleLibelle eq applicationScope.roleG }">
						<option value="Client" >Client</option>
						<option value="Gerant" selected>Gérant</option>
						<option value="Administrateur">Administrateur</option>
					</c:if>
					<c:if test="${utilisateur.roleLibelle eq applicationScope.roleA  }">
						<option value="Client" >Client</option>
						<option value="Gerant">Gérant</option>
						<option value="Administrateur" selected>Administrateur</option>
					</c:if>

				</select>
			</div>

			<div class="info">
			<input type="hidden" id="idAdresse" name="idAdresse"
					value="${utilisateur.adresse.id }"> <input
					type="hidden" id="idPersonne" name="idPersonne"
					value="${utilisateur.id}">
					
					
				<input type="submit" value="Valider">
			</div>
		</form>
	</div>

</body>
</html>