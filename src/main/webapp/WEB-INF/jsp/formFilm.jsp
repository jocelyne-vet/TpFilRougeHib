<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Film</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/formFilm.css">
<script src="js/formFilm.js"></script>
</head>
<body>
	<%@ include file="../fragments/fragmentMenu.jsp"%>
	<div class="content">
		<h1>Formulaire du film</h1>
		<p><c:out value = "${messageErreur}"></c:out></p>
		<form action="${pageContext.request.contextPath}/formulaireFilm" method="post" onsubmit ="return valideSeanceBis()">
			<div class="film">
				<div class="infoFilm">
					
					<div class="infos">
						<label for="titre">Titre : </label> <input type="text"
							name="titre"  class="inputFilm" value="${film.nom }" required="required">
					</div>
					<div class="infos">
						<label for="duree">Durée : </label> <input type="number"
							name="duree"  min="0" class="inputFilm" value="${ film.duree}" required="required" placeholder="Durée en min">
					</div>
					<div class="infos" id="synopsis">
						<label for="synopsis">Synopsis : </label>
						<textarea name="synopsis"  cols="30" rows="10" ><c:out value="${film.description }" ></c:out></textarea>
					</div>
					<div class="infos">
						<label for="affiche">Affiche : </label> <input type="url"
							name="affiche" class="inputFilm"
							placeholder="Entrez l'url" value="${film.affiche}">

					</div>
					<div class="infos">
						<label for="ageMinimum">Age minimum : </label> <input type="number"
							name="ageMinimum"  class="inputFilm"
							 value="${film.ageMinimum}" min = 0>

					</div>
				</div>
				<div class="seances">
					<h2>
						Prochaines séances : <input type="button"
							value="Ajouter des Séances" onclick="ajouterSeance()">
					</h2>
					<c:forEach items="${seances}" var="currentSeance"
						varStatus="status">
						<div class="seance">

							<label for="Supprimer">Supprimer</label> <input type="checkbox"
								name="Supprimer" id="Supprimer" onclick="removeSeance(this.id);">
							<label for="dateSeance">Date : </label> <input type="date"
								name="dateSeance"
								id="dateSeance"
								value="${currentSeance.getHeureDebut().toLocalDate()}" required="required">
							<label for="heureSeance"> Heure : </label> 
							<input
								type="time" name="heureSeance" value = "${currentSeance.formatHeureMinute() }"
								id="heureSeance"  required="required"> 
								<input
								type="hidden" name="idSeance"
								value="${currentSeance.id}">


						</div>
					</c:forEach>
					<div id="nouvelleSeance"></div>
				</div>
			</div>
			<div class="enreg">
				<input type="submit" value="Enregistrer" > <input
					type="hidden" name="idFilm" value="${film.id }"> <input type="hidden"
					name="idSalle" value="${idSalle}">
			</div>
		</form>
	</div>


</body>
</html>