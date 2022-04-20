<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

   <!-- HEADER AVEC TITRE ET LIENS CONNEXION -->

<header>
	<div class="titre">
		<h2><a href="./">ENI-Enchères</a></h2>
	</div>
	
	<div class="connexion">
		<c:choose>
			<c:when test="${connexion}">
				<%@ include file="./nav.jsp"%>
			</c:when>
			<c:otherwise>
				<a href="./connexion" class="lienConnexion">S'inscrire - Se connecter</a>
			</c:otherwise>
		</c:choose>
	</div>
	
</header>

<c:if test="${erreurMessage != null }">
	<span class="messageErreur">${erreurMessage}</span>
</c:if>

<c:if test="${confirmationMessage != null }">
	<div class="messageConfirmation">
		<span>${confirmationMessage} </span>
	</div>
</c:if>