
<div class="header" id="entete">
	<img src="img/index.png" alt="logo cinema" height="60"> <a
		class="menu active" href="${pageContext.request.contextPath}/accueil"
		id="test1">Accueil</a> <span>FR</span>


	<c:if test="${!empty sessionScope.user  }">
		<c:if
			test="${ sessionScope.user.roleLibelle eq applicationScope.roleC }">
			<a class="menu"
				href="${pageContext.request.contextPath}/historiqueReservation">Historique des
				réservations</a>

		</c:if>
        
		<c:if
			test="${ sessionScope.user.roleLibelle eq applicationScope.roleG }">
			<a class="menu"
				href="${pageContext.request.contextPath}/etablissement">Gérer
				mon établissement</a>
		</c:if>
		<c:if
			test="${ sessionScope.user.roleLibelle eq applicationScope.roleA }">
			<a class="menu"
				href="${pageContext.request.contextPath}/listeCinemas">Consulter
				les établissements</a>
			<a class="menu"
				href="${pageContext.request.contextPath}/listeUtilisateurs">Consulter
				les utilisateurs</a>
		</c:if>
	</c:if>
	<div class="user_infos">
		<img class="user_avatar" src="img/avatar.jpg" alt="avatar utilisateur"
			height="60">
		<div class="user_options">
			<a class="menu" href="${pageContext.request.contextPath}/compte">Mon
				compte</a>
			<c:if test="${empty sessionScope.user }">
				<a href="${pageContext.request.contextPath}/login">Connexion</a>
			</c:if>
			<c:if test="${!empty sessionScope.user }">
				<a class="menu"
					href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
			</c:if>
		</div>
	</div>
</div>