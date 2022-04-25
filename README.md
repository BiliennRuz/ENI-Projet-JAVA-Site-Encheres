# projetEncheres

## Team project :

-   Jerome VASSEUR
-   Yannick BIHEUL
-   Dominique AMPS

## project managment
Lien vers le kanban [Notion](https://www.notion.so/bilienn/32fa5e53fe824dc3a17d3fe22cd31cb9?v=eb5cc1df1cc7408485a85253437b8520)

## Mise en place du logging
[Tuto d'install et config] (https://howtodoinjava.com/logback/logback-tutorial/)

#### Guide rapide
1. déclarer dans .classpath :

	logback-classic-1.2.11.jar
	logback-core-1.2.11.jar
	slf4j-api-1.7.36.jar
	
2. Importer dans la classe :

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

3. Initialiser le logger en début de class :

	// initialisation du logger
		Logger logger = LoggerFactory.getLogger(VenteManager.class);

4. Puis appeller le logger ou vous voulez :

	logger.info("Appel de getArticle()");
	
5. En modifiant les niveau de log :

- TRACE – the lowest level of information, mostly used for very deep code debugging, usually not included in production logs.
- DEBUG – low level of information used for debugging purposes, usually not included in production logs.
- INFO – a log severity carrying information, like an operation that started or finished.
- WARN – a log level informing about an event that may require our attention, but is not critical and may be expected.
- ERROR – a log level telling that an error, expected or unexpected, usually meaning that part of the system is not working properly.


#### avancement du déploiement
deployé sur :
- VenteManager
- AcceuilServlet

reste à faire :
- implementer le formattage et le type de sortie

## pense bete à Dom
#### pour mise en forme de la LocalDate  
.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)

#### A integrer dans jsp ventedétail
					<c:if test="${utilisateurConnecte != null}"> 
						<td>${articles.nomArticle}</td> 
						<td>${articles.description}</td>
						<td>${articles.categorieArticle.libelle}</td>
						<td>${articles.lastEnchere.montantEnchere}</td>
						<td>${articles.prixInitial}</td>
						<td>${articles.dateFinEncheres}</td>
						<td>${articles.lieuRetrait.rue} ${articles.lieuRetrait.codePostal} ${articles.lieuRetrait.ville}</td>
						<td>${articles.vendeur.pseudo}</td>
					</c:if>
