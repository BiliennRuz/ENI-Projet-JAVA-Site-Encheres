<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Connexion</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<!-- HEADER AVEC TITRE -->
	
	<header>
		<div class="titre">
			<h2>ENI-Enchères</h2>
		</div>
	</header>


	<!-- FORMULAIRE -->
	<!-- Données : pseudo - motDepasse - souvenir -->
	
	<form class="blocConnexion" action="" method="POST">
		<div class="blocConnexionInputs">
			<div class="labelInput">
				<label for="pseudo">Identifiant : </label>
				<input type="text" name="pseudo" id="pseudo">
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
	
	<section class="creationCompte">
		<a href="./inscription">Créer un compte</a>
	</section>

</body>
</html>