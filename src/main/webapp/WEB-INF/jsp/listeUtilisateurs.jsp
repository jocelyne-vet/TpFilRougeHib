<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste utilisateurs</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/listeUtilisateurs.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/message.css">
</head>
<body>
	<%@ include file="../fragments/fragmentMenu.jsp"%>
	<%@ include file="../fragments/fragmentMessage.jsp"%>
	<div class="content">
		<div class="users">

			<div class="table">
				<h1>Liste des utilisateurs</h1>
				<div>
					<div class="tr">
						<div class="th">Nom</div>
						<div class="th">Prénom</div>
						<div class="th">Date de naissance</div>
						<div class="th">Email</div>
						<div class="th">Rôle</div>
						<div class="th">Adresse</div>
						<div class="th">Modifier</div>
						<div class="th">Supprimer</div>
					</div>
				</div>

				<div>
					<c:forEach items="${utilisateurs}" var="currentUser">
						<div class="tr">
							<div class="td">
								<c:out value="${currentUser.nom }"></c:out>
							</div>
							<div class="td">
								<c:out value="${currentUser.prenom }"></c:out>
							</div>
							<div class="td">
								<c:out value="${currentUser.dateNaissance }"></c:out>
							</div>
							<div class="td">
								<c:out value="${currentUser.email }"></c:out>
							</div>
							<div class="td">
								<c:out value="${currentUser.roleLibelle }"></c:out>
							</div>
							<div class="td">
								<c:out value="${currentUser.adresse.numero }"></c:out>
								<c:out value="${currentUser.adresse.typeRue }"></c:out>
								<c:out value="${currentUser.adresse.nomRue }"></c:out>
								<c:out value="${currentUser.adresse.cpo }"></c:out>
								<c:out value="${currentUser.adresse.ville }"></c:out>
							</div>
							<form
								action="${pageContext.request.contextPath}/formulaireUtilisateur"
								method="get">
								<div class="actions">

									<input type="image" src="img/modifier.jpg" alt="modifier"
										width="30"> <input type="hidden" name="id"
										value="${currentUser.id }">

								</div>
							</form>
							<form
								action="${pageContext.request.contextPath}/supprimerUtilisateur"
								method="post"
								onsubmit="return confirm('Voulez-vous supprimer l'utilisateur ${currentUser.nom } ${currentUser.prenom }?');">
								<div class="actions">
									<input type="image" src="img/croix.jpg" alt="supprimer"
										width="30"> <input type="hidden" name="id"
										value="${currentUser.id }">
										<input type="hidden" name="nomUtilisateur" value = "${currentUser.nom }">
										<input type="hidden" name="prenomUtilisateur" value = "${currentUser.prenom }">
								</div>
							</form>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
		<div class="actionCreer">
			<form
				action="${pageContext.request.contextPath}/formulaireUtilisateur">
				<input type="image" src="img/ajouteruser.jpg" alt="submit"
					width="30">
			</form>
		</div>
	</div>

</body>
</html>