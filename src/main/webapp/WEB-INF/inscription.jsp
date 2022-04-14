<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./include/header.jsp"%>
	
	<div class="blocInscription">
		<h1>Mon Profil</h1>
	</div>
	
	
	<!-- FORMULAIRE -->
	<!-- Données : pseudo - nom - prenom - email - tel - rue - codePostal - ville - motDePasse - motDepasseConfirm -->
	
	<form class="inscription" action="" method="POST">
		<div class="blocInscription blocChamps">
			<div class="pseudoNom blocInputs">
				<div class="blocLabelinput">
					<label for="pseudo">Pseudo</label>
					<input type="text" name="pseudo" id="pseudo">
				</div>
				<div class="blocLabelinput">
					<label for="nom">Nom</label>
					<input type="text" name="nom" id="nom">
				</div>
			</div>
			<div class="prenomEmail blocInputs">
				<div class="blocLabelinput">
					<label for="prenom">Prénom</label>
					<input type="text" name="prenom" id="prenom">
				</div>
				<div class="blocLabelinput">
					<label for="email">Email</label>
					<input type="email" name="email" id="email">
				</div>
			</div>
			<div class="telRue blocInputs">
				<div class="blocLabelinput">
					<label for="tel">Téléphone</label>
					<input type="tel" name="tel" id="tel">
				</div>
				<div class="blocLabelinput">
					<label for="rue">Rue</label>
					<input type="text" name="rue" id="rue">
				</div>
			</div>
			<div class="cpVille blocInputs">
				<div class="blocLabelinput">
					<label for="codePostal">Code Postal</label>
					<input type="number" name="codePostal" id="codePostal">
				</div>
				<div class="blocLabelinput">
					<label for="ville">Ville</label>
					<input type="text" name="ville" id="ville">
				</div>
			</div>
			<div class="mdpConfirm blocInputs">
				<div class="blocLabelinput">
					<label for="motDePasse">Mot de passe</label>
					<input type="password" name="motDePasse" id="motDePasse">
				</div>
				<div class="blocLabelinput">
					<label for="motDePasseConfirm">Confirmation mot de passe</label>
					<input type="password" name="motDePasseConfirm" id="motDePasseConfirm">
				</div>
			</div>
		</div>
		<div class="blocInscription">
			<button type="submit">Créer</button>
			<a href="./">Annuler</a>
		</div>
		
	</form>

</body>
</html>