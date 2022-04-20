<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp"%>
<body>

	<div class="blocInscription">
		<h1>Inscription</h1>
	</div>
	
	
	<!-- FORMULAIRE -->
	<!-- Données : pseudo - nom - prenom - email - tel - rue - codePostal - ville - motDePasse - motDepasseConfirm -->
	
	<form class="inscription" action="./inscription" method="POST">
		<div class="blocInscription blocChamps">
			<div class="pseudoNom blocInputs">
				<div class="blocLabelinput">
					<label for="pseudo">Pseudo</label>
					<input type="text" name="pseudo" id="pseudo" value="${utilisateur.pseudo}">
				</div>
				<div class="blocLabelinput">
					<label for="nom">Nom</label>
					<input type="text" name="nom" id="nom" value="${utilisateur.nom}">
				</div>
			</div>
			<div class="prenomEmail blocInputs">
				<div class="blocLabelinput">
					<label for="prenom">Prénom</label>
					<input type="text" name="prenom" id="prenom" value="${utilisateur.prenom}">
				</div>
				<div class="blocLabelinput">
					<label for="email">Email</label>
					<input type="email" name="email" id="email" value="${utilisateur.email}">
				</div>
			</div>
			<div class="telRue blocInputs">
				<div class="blocLabelinput">
					<label for="tel">Téléphone</label>
					<input type="tel" name="tel" id="tel" value="${utilisateur.telephone}">
				</div>
				<div class="blocLabelinput">
					<label for="rue">Rue</label>
					<input type="text" name="rue" id="rue" value="${utilisateur.rue}">
				</div>
			</div>
			<div class="cpVille blocInputs">
				<div class="blocLabelinput">
					<label for="codePostal">Code Postal</label>
					<input type="number" name="codePostal" id="codePostal" value="${utilisateur.codePostal}">
				</div>
				<div class="blocLabelinput">
					<label for="ville">Ville</label>
					<input type="text" name="ville" id="ville" value="${utilisateur.ville}">
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
		<div class="blocInscription blocBoutonsInscription">
			<button class="boutonInscription" type="submit">Créer</button>
			<a class="boutonInscription" href="./">Annuler</a>
		</div>
		
		<div class="erreurs">
			<p>${messageErreur}</p>
		</div>
		
	</form>

<%@ include file="./include/footer.jsp"%>