<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>

	<!-- HEADER AVEC TITRE -->
	
	<header>
		<div class="titre">
			<h2>ENI-Enchères</h2>
		</div>
	</header>
	
	
	<!-- FORMULAIRE -->
	<!-- Données : pseudo - nom - prenom - email - tel - rue - codePostal - ville - motDePasse - motDepasseConfirm -->
	
	<form class="inscription" action="" method="POST">
		<div class="pseudoNom">
			<label for="pseudo">Pseudo</label>
			<input type="text" name="pseudo" id="pseudo">
			<label for="nom">Nom</label>
			<input type="text" name="nom" id="nom">
		</div>
		<div class="prenomEmail">
			<label for="prenom">Prénom</label>
			<input type="text" name="prenom" id="prenom">
			<label for="email">Email</label>
			<input type="email" name="email" id="email">
		</div>
		<div class="telRue">
			<label for="tel">Téléphone</label>
			<input type="tel" name="tel" id="tel">
			<label for="rue">Rue</label>
			<input type="text" name="rue" id="rue">
		</div>
		<div class="cpVille">
			<label for="codePostal">Code Postal</label>
			<input type="number" name="codePostal" id="codePostal">
			<label for="ville">Ville</label>
			<input type="text" name="ville" id="ville">
		</div>
		<div class="mdpConfirm">
			<label for="motDePasse">Mot de passe</label>
			<input type="password" name="motDePasse" id="motDePasse">
			<label for="motDePasseConfirm">Confirmation mot de passe</label>
			<input type="password" name="motDePasseConfirm" id="motDePasseConfirm">
		</div>
		
		<button type="submit">Créer</button>
		<a href="./">Annuler</a>
	</form>

</body>
</html>