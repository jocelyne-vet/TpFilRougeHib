<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des cinemas</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/listeCinemas.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/message.css">
</head>
<body>
	<%@ include file="../fragments/fragmentMenu.jsp"%>
	<%@ include file="../fragments/fragmentMessage.jsp"%>

	<div class="content">
		<h1>Les cinémas</h1>

		<div class="creer">
			<form action="${pageContext.request.contextPath}/formulaireCinema"
				method="get">
				<input type="submit" value="Créer un cinéma ">
			</form>
		</div>
		
		<div>
			<c:forEach items="${cinemas }" var="currentCinema">
				<div class="tr">
					<div class="affiche">
						<figure>
							<img src="${currentCinema.affiche }" alt="photos cinémas"
								width="200">
							<figcaption>
								<c:out value="${currentCinema.nom }"></c:out>
							</figcaption>
						</figure>
					</div>
					<div class="info">
						<div class="adresse">
							<span> Adresse :</span>
							<c:out value="${currentCinema.adresse.numero }"></c:out>
							<c:out value="${currentCinema.adresse.typeRue }"></c:out>
							<c:out value="${currentCinema.adresse.nomRue }"></c:out>
							<c:out value="${currentCinema.adresse.cpo }"></c:out>
							<c:out value="${currentCinema.adresse.ville }"></c:out>
						</div>
						<div class="gerant">
							<span>Gérant :</span>
							<c:out value="${currentCinema.gerant.nom }"></c:out>
							<c:out value="${currentCinema.gerant.prenom }"></c:out>
						</div>
					</div>
					<div class="images">
						<form action="${pageContext.request.contextPath}/formulaireCinema"
							method="get">
							<input type="image" src="img/modifier.jpg" alt="modifier"
								width="30"> <input type="hidden" name="idCinema"
								value="${currentCinema.id }"><input type="hidden"
								name="idGerant" value="${currentCinema.gerant.id }">
						</form>
						<form action="${pageContext.request.contextPath}/supprimerCinema"
							method="post"
							onsubmit="return confirm('Voulez-vous supprimer le cinéma  ${currentCinema.nom }?');">
							<input type="image" src="img/croix.jpg" alt="supprimer"
								width="30"><input type="hidden" name="idCinema"
								value="${currentCinema.id }"><input type="hidden"
								name="idGerant" value="${currentCinema.gerant.id }">
								<input type="hidden" name="nomCinema" value="${currentCinema.nom }">
						</form>
						<form action="${pageContext.request.contextPath}/etablissement">
							<input type="image" src="img/imgCinema/salle.jpg" alt="modifier"
								width="40"> <input type="hidden" name="idCinema"
								value="${currentCinema.id }"><input type="hidden"
								name="idGerant" value="${currentCinema.gerant.id }">
						</form>
					</div>
				</div>
			</c:forEach>
		</div>


	</div>

</body>
</html>