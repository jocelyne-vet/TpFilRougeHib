<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuclient.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/message.css">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script> --%>
</head>
<body>
	<%@ include file="../fragments/fragmentMenu.jsp"%>
	<%@ include file="../fragments/fragmentMessageErreur.jsp" %>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<div class="content">
			<h1>Je m'identifie</h1>
			<div>

				<input type="email" name="email" id="email" placeholder="Email"
					class="btn" required="required">
			</div>
			<div>
				<input type="password" name="mdp" id="mdp"
					placeholder="Mot de passe" class="btn" required="required">
			</div>
			<div>
				<input type="checkbox" name="seSouvenir" id="seSouvenir"><label
					for="seSouvenir">Se souvenir de moi</label>
			</div>
			<div id="right">
				<input type="submit" value="Je me connecte" class="btnConnexion">
			</div>
		
		</div>
	</form>
</body>
</html>