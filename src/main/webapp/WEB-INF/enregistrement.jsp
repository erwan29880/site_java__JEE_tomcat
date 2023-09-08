<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Accueil</title>
	<link href="assets/css/style.css" rel="stylesheet">
</head>
<body>

<div class="containeur">

<%@ include file="menu.jsp" %>

	<h1>Créez votre compte membre !</h1>

		<div class="formIndex">
		
			<p class="error">
				<c:if test="${ !empty error }"><c:out value="${ error }" /></c:if>
			</p>
		    
		
			<form action="enregistrement" method="post" class="formUtilisateur">
				
				<c:if test="${ !empty sessionScope.pseudo }">
				    <label for="supprimer">Supprimer mon compte</label>
				    <input type="checkbox" id="supprimer" name="supprimer" value="1" />
				</c:if> 
				<div class="champsText">
				    <label for="pseudo">Pseudo</label>
				    <input type="text" name="pseudo" required />
			    </div>
			    <div class="champsText">
				    <label for="pwd">Mot de passe</label>
				    <input type="password" name="pwd" required 
				    autocomplete="off"/>
				</div>
				<div class="champsSubmit">
			    	<input type="submit" value="envoyer" />
				</div>
			</form>
		</div>
	
</div>

<script>
let suppr = document.getElementById("supprimer");
suppr.addEventListener("change", () => {
	if (suppr.checked == true) {
		alert("Voulez-vous vraiment supprimer votre compte ?");	
	}
})
</script>

</body>
</html>