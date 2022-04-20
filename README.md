# projetEncheres

## Team project :

-   Jerome VASSEUR
-   Yannick BIHEUL
-   Dominique AMPS

## project managment
Lien vers le kanban [Notion](https://www.notion.so/bilienn/32fa5e53fe824dc3a17d3fe22cd31cb9?v=eb5cc1df1cc7408485a85253437b8520)

## pense bete à Dom
### pour mise en forme de la LocalDate  
.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)

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
					
					
										<div>
						<input type="radio" name="achatvente" value="achat" />Achat<br />
						<input type="checkbox" name="enchereouvertes" value="true"/>Enchère ouvertes<br />
						<input type="checkbox" name="mesencheres" value="true"/>Mes enchères<br />
						<input type="checkbox" name="mesencheresremportees" value="true"/>Mes enchères remportées<br />
					<div>
					</div>
						<input type="radio" name="achatvente" value="vente" checked/>Vente<br />
						<input type="checkbox" name="ventenondebutee" value="true" />Vente non débutée<br />
						<input type="checkbox" name="venteencours" value="true" checked/>Vente en cours<br />
						<input type="checkbox" name="venteterminee" value="true" />Vente terminée<br />
					</div>
					