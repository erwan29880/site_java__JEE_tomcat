<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Connection</title>
	<link href="assets/css/style.css" rel="stylesheet">
	<script src="assets/js/utils.js"></script></head>
<body>

<div class="containeur">
<%@ include file="menu.jsp" %>
	
	<h1>Connectez-vous à l'espace membre !</h1>
	
	
	<c:if test="${ empty sessionScope.pseudo }">
    	<!-- connexion et enregistrement session -->
    
		<div class="formIndex">
			
			<p class="error">
				<c:if test="${ !empty error }"><c:out value="${ error }" /></c:if>
			</p>
		
			<form action="connection" method="post" class="formUtilisateur">
				<div class="champsText">
				    <label for="pseudo">Pseudo</label>
				    <input type="text" name="pseudo" required />
			    </div>
			    <div class="champsText">
				    <label for="pwd">Mot de passe</label>
				    <input type="password" name="pwd" required autocomplete="off" />
				</div>
				<div class="champsSubmit">
			    	<input type="submit" value="envoyer" />
				</div>
			</form>
		</div>
	
	</c:if>
	
	
	<c:if test="${ !empty sessionScope.pseudo }">
		<!-- si l'utilisateur a une session active -->
			
		<p><span id="pseudo">${ sessionScope.pseudo }</span>, vous êtes authentifié(e) !</p>
		<p>Vous serez déconnecté(e) en fermant votre navigateur !</p>
		
		
	</c:if>


</div>
<script>
let pseudo = document.getElementById("pseudo");
capitalizeFirstLetter(pseudo);
</script>



<body>

</body>
</html>