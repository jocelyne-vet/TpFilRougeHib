<div class="messageErreur">
	<c:out value="${messageErreur }"></c:out>
	<c:if test="${!empty erreurs }">
		<div class="errors">
			<ul>
				<c:forEach var="msg" items="${erreurs }">
					<li>${msg }</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
</div>