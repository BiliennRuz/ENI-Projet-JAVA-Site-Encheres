<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/head.jsp"%>

	<c:if test="${succes != null }">
		<div class="succes">${succes}</div>
	</c:if>

	<!-- TITRE DE LA PAGE -->
	
	<section class="titrePage">
		<h1>Nouvelle vente</h1>
	</section>
	
	<div class="row">
		<div class="col-6 d-flex justify-content-center">
			<div style="width:300px;height:300px;border: 1px solid #000;"></div>
		</div>
		<div class="col-6">
		
			<form class="mb-5" action="./nouvelleVente" method="POST">
			  <div class="mb-3">
			    <label for="article" class="form-label">Article : </label>
			    <input type="text" class="form-control" name="article" id="article" style="width: 600px;">
			  </div>
			  <div class="mb-3">
			    <label for="description" class="form-label">Description : </label>
			    <textarea class="form-control" name="description" id="description" style="width: 600px;"></textarea>
			  </div>
			  <div class="mb-3">
			    <label for="categorie" class="form-label">Catégorie : </label>
			    <select class="form-select" name="categories" id="floatingSelect" style="width: 600px;">
				    <c:forEach var="element" items="${categories}">
				    	<option value="${element.libelle}">${element.libelle}</option>
				    </c:forEach>
				</select>
			  </div>
			  <div class="mb-3">
					<label for="article">photo de l'article : </label>
					<button class="btn btn-primary">UPLOADER</button>
			  </div>
			  <div class="mb-3">
			    <label for="prix" class="form-label">Mise à prix : </label>
			    <input type="number" class="form-control" name="prix" id="prix" style="width: 600px;">
			  </div>
			  <div class="mb-3">
			    <label for="debut" class="form-label">Début de l'enchère : </label>
			    <input type="date" class="form-control" name="debut" id="debut" style="width: 600px;">
			  </div>
			  <div class="mb-3">
			    <label for="fin" class="form-label">Fin de l'enchère : </label>
			    <input type="date" class="form-control" name="fin" id="fin" style="width: 600px;">
			  </div>
			  <p>Retrait : </p>
			  <div class="mb-3">
			    <label for="rue" class="form-label">Rue : </label>
			    <input type="text" class="form-control" name="rue" id="rue" style="width: 600px;" value="${utilisateur.rue}">
			  </div>
			  <div class="mb-3">
			    <label for="cp" class="form-label">Code Postal : </label>
			    <input type="text" class="form-control" name="codePostal" id="cp" style="width: 600px;" value="${utilisateur.codePostal}">
			  </div>
			  <div class="mb-3">
			    <label for="ville" class="form-label">Ville : </label>
			    <input type="text" class="form-control" name="ville" id="ville" style="width: 600px;" value="${utilisateur.ville}">
			  </div>
			  <button type="submit" class="btn btn-primary">Enregistrer</button>
			  <a href="./" class="btn btn-primary">Annuler</a>
			</form>
			
		</div>
	</div>


<%@ include file="./include/footer.jsp"%>