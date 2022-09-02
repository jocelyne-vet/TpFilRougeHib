<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accueil.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/message.css">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script> --%>
</head>



<body>

	<%@ include file="fragments/fragmentMenu.jsp"%>
	<%@ include file="fragments/fragmentMessage.jsp"%>

	<div class="recherche">

		<form action="${pageContext.request.contextPath}/accueil"
			method="post">
			<h1>Cinémas Rilletthes</h1>
			<input type="search" name="critere" id="critere"
				placeholder="Rechercher" size="100">
		</form>
	</div>
	</div>
	<c:if test="${!empty cinemas }">
		<div id="contenu">
			<c:forEach items="${cinemas}" var="current">
				<div class="cinemaContent">
					<form action="${pageContext.request.contextPath}/cinema"
						method="get">
						<img src="${current.affiche }" alt="Affiche du cinema" width="250">
						<div class="information">
							<input type="hidden" name="idcinema" value="${current.id }">

							<input type="submit" value="${current.nom }" class="btnCinema" />
							<div id="adresse">
								<span>Adresse : <c:out value="${current.adresse.numero }"></c:out>
									<c:out value="${current.adresse.typeRue }"></c:out> <c:out
										value="${current.adresse.nomRue }"></c:out> <c:out
										value="${current.adresse.cpo }"></c:out> <span> <span><c:out
												value="${current.adresse.ville }"></c:out></span>
							</div>
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
	</c:if>
</body>

</html>