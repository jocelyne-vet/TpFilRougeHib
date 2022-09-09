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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/film.css">
</head>
<body>
	<%@ include file="../fragments/fragmentMenu.jsp"%>
	<div class="content">
		<div class="film">
			<div class="info">
				<p>
				<h2>
					<c:out value="${film.nom }"></c:out>
				</h2>
				<p>
					<span class="contentInfo">Durée :</span>
					<c:out value="${film.fDuree }"></c:out>
					min

				</p>
				<span class="contentInfo">Synopsis :</span>
				<p>${film.description }</p>
				<p>
				<span class="contentInfo">Age minimum :</span>
				
				<c:out value="${film.ageMinimum }"></c:out>
				</p>
			</div>
			<c:if test="${empty film.affiche }">
				<img src="img/imgFilm/affichevide.jpg" alt="Affiche du film"
					width="300">
			</c:if>
			<c:if test="${!empty film.affiche }">
				<img src="${film.affiche }" alt="Affiche du film" width="300">
			</c:if>



		</div>
		<div>
			<h2>Prochaines séances</h2>

			<div class="seances">

				<c:if
					test="${empty sessionScope.user || sessionScope.user.roleLibelle eq applicationScope.roleC }">

					<c:forEach items="${seances}" var="currentSeance"
						varStatus="status">
						<c:if test="${ status.index eq 0}">

							<h3>

								<fmt:formatDate type="date" dateStyle="long" timeStyle="long"
									value="${currentSeance.heureDebutDate}" />
								<%-- <c:out value="${currentSeance.heureDebut.toLocalDate()}"></c:out> --%>
							</h3>

							<div class="blocseance">
								<form action="${pageContext.request.contextPath}/reserver"
									method="get">

									<input type="hidden" name="idseance"
										value="${currentSeance.id}"> <input type="submit"
										class="btnSeance"
										value="${currentSeance.getHeureDebut().toLocalTime() }">

								</form>
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
									<form action="${pageContext.request.contextPath}/reserver"
										method="get">

										<input type="hidden" name="idseance"
											value="${currentSeance.id}"> <input type="submit"
											class="btnSeance"
											value="${currentSeance.getHeureDebut().toLocalTime() }">

									</form>
								</div>
							</c:if>


							<c:if
								test="${currentSeance.heureDebut.toLocalDate().compareTo(seances.get(status.index-1).heureDebut.toLocalDate()) eq 0 }">

								<div class="blocseance">
									<form action="${pageContext.request.contextPath}/reserver"
										method="get">

										<input type="hidden" name="idseance"
											value="${currentSeance.id}"> <input type="submit"
											class="btnSeance"
											value="${currentSeance.getHeureDebut().toLocalTime() }">

									</form>
								</div>
							</c:if>

						</c:if>
					</c:forEach>
				</c:if>
				<c:if
					test="${ sessionScope.user.roleLibelle eq applicationScope.roleG || sessionScope.user.roleLibelle eq applicationScope.roleA}">
					<c:forEach items="${seances}" var="currentSeance"
						varStatus="status">
						<c:if test="${ status.index eq 0}">

							<h3>

								<fmt:formatDate type="date" dateStyle="long" timeStyle="long"
									value="${currentSeance.heureDebutDate}" />

							</h3>

							<div class="blocseancebis">
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
								<div class="blocseancebis">
									<c:out value="${currentSeance.getHeureDebut().toLocalTime() }"></c:out>
								</div>
							</c:if>


							<c:if
								test="${currentSeance.heureDebut.toLocalDate().compareTo(seances.get(status.index-1).heureDebut.toLocalDate()) eq 0 }">

								<div class="blocseancebis">
									<c:out value="${currentSeance.getHeureDebut().toLocalTime() }"></c:out>
								</div>
							</c:if>

						</c:if>
					</c:forEach>


				</c:if>
			</div>
		</div>
	</div>
</body>
</html>