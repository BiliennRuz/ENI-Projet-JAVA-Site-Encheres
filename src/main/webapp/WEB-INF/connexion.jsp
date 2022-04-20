<%@ include file="./include/head.jsp"%>

	<div class="blocInscription">
		<h1>Connexion</h1>
	</div>
	
	<form class="blocConnexion" action="./connexion" method="POST">
		<div class="blocConnexionInputs">
			<div class="labelInput">
				<label for="login">Identifiant : </label>
				<input type="text" name="login" id="login">
			</div>
			<br>
			<div class="labelInput">
				<label for="motDePasse">Mot de passe : </label>
				<input type="password" name="motDePasse" id="motDePasse">
			</div>
		</div>
		<div class="blocConnexionBoutons">
			<div class="blocConnexionBouton">
				<button type="submit">Connexion</button>
			</div>
			<div class="blocConnexionOptions">
				<div class="souvenir">
					<input type="checkbox" name="souvenir" id="souvenir">
					<label for="souvenir">Se souvenir de moi</label>
				</div>
				<a href="">Mot de passe oublié</a>
			</div>
		</div>
	</form>
	
	<c:if test="${erreurMessage != null }">
		<div class="erreur">${erreurMessage}</div>
	</c:if>
	
	<section class="creationCompte">
		<a class="boutonInscription" href="./inscription">Créer un compte</a>
	</section>

<%@ include file="./include/footer.jsp"%>