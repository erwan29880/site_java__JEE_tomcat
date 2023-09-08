<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Accueil</title>
	<link href="assets/css/style.css" rel="stylesheet">
	<script src="assets/js/utils.js"></script>
	
</head>
<body>

<div class="containeur">

<%@ include file="menu.jsp" %>

<h1>Enregistrer un nouvel utilisateur</h1>

	<c:if test="${ !empty sessionScope.pseudo }">
     	<p><span id="pseudo">${ sessionScope.pseudo }</span>, vous avez accès à cette page</p>

			<div class="formIndex">
			
				<p class="error">
					<c:if test="${ !empty error }"><c:out value="${ error }" /></c:if>
				</p>

		
				<form action="index" method="post" class="formUtilisateur">
					<div class="champsText">
					    <label for="nom">Nom </label>
					    <input type="text" name="nom" required />
				    </div>
				    <div class="champsText">
					    <label for="prenom">Prénom</label>
					    <input type="text" name="prenom" required />
					</div>
					<div class="champsSubmit">
				    	<input type="submit" value="envoyer" />
					</div>
				</form>
			</div>
		
			<div class="listIndex">
			
				<p>Utilisateurs enregistrés</p>
				
				<c:forEach var="user" items="${ users }">
		            <li><c:out value="${ user.prenom }" /> <c:out value="${ user.nom }" /></li>
		        </c:forEach>
			</div>

		</c:if>
		<c:if test="${ empty sessionScope.pseudo }">
			<p class="error">Vous devez vous connecter pour acceder aux données de cette page !</p>
		</c:if>		

</div>

<script>
let pseudo = document.getElementById("pseudo");
capitalizeFirstLetter(pseudo);
</script>


</body>
</html>