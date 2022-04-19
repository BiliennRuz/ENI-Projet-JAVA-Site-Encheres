package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Utilisateur;

/**

 * Implémentation des fonctionnalités de mon interface UtilisateurDAO avec JDBC (en base de donnée)

 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	// on définit nos requêtes SQL d'insertion/select avec des ? qu'on remplira par la suite

	private final static String SELECT_UTILISATEUR = "select * from UTILISATEURS;";
	private final static String SELECT_UTILISATEUR_BY_ID = "select * from UTILISATEURS WHERE no_utilisateur=?;";
	private final static String INSERT_UTILISATEUR = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";
	private final static String UPDATE_UTILISATEUR = "update UTILISATEURS set no_utilisateur=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE pseudo=?;";
	private final static String CHECK_UTILISATEUR = "select * from UTILISATEURS where (pseudo=? or email=?) and mot_de_passe=?;";
	private final static String DELETE_UTILISATEUR = "delete from UTILISATEURS where pseudo=?;";
	
	/**
	 * getUser() : recupère la liste des user depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public List<Utilisateur> getUser() throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		
		// 2 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = stmt.executeQuery(SELECT_UTILISATEUR);
		
		// 3 - j'initialise la liste des user que je vais renvoyer
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		
		// 4 - je parcours mes resultats pour remplir ma liste des user que je vais renvoyer
		// tant qu'il y a des lignes de resultats
		while (rs.next()) {
			// pour chaque ligne , j'ajoute le user correspondant à ma liste
			Utilisateur user = new Utilisateur(
					rs.getString("pseudo"),
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("email"),
					rs.getString("rue"),
					rs.getString("ville"),
					rs.getString("mot_de_passe"),
					rs.getInt("no_utilisateur"),
					rs.getString("telephone"),
					rs.getString("code_postal"),
					rs.getFloat("credit"),
					rs.getBoolean("administrateur")
					);
			listeUtilisateurs.add(user); // une fois le user créé je l'ajoute à ma liste
		}
		
		return listeUtilisateurs; // pour finir je renvoie ma liste remplie precédemment
	}
	
	/**
	 * getUserById() : recupère le user a partir de son Id depuis la base de donnée
	 * @throws SQLException 
	 */
	@Override
	public Utilisateur getUserById(int idUtilisateur) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		// 1 - on crée une "requête" standard car pas besoin de changer de ? avec des valeurs de variables
		Statement stmt = cnx.createStatement();
		// 2 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_ID);
		pStmt.setInt(1, idUtilisateur);
		// 3 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = pStmt.executeQuery();
		// 4 - je parcours mes resultats pour remplir ma liste des user que je vais renvoyer
		rs.next();
		Utilisateur user = new Utilisateur(
				rs.getString("pseudo"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getString("email"),
				rs.getString("rue"),
				rs.getString("ville"),
				rs.getString("mot_de_passe"),
				rs.getInt("no_utilisateur"),
				rs.getString("telephone"),
				rs.getString("code_postal"),
				rs.getFloat("credit"),
				rs.getBoolean("administrateur")
				);
		return user;		
	}
	
	
	
	/**
	 * add(Utilisateur user) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : UtilisateurManager)
	 */
	@Override
	public void addUser(Utilisateur user) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_UTILISATEUR,Statement.RETURN_GENERATED_KEYS);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet Utilisateur		
		//pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur
		pStmt.setString(1, user.getPseudo());
		pStmt.setString(2, user.getNom());
		pStmt.setString(3, user.getPrenom());
		pStmt.setString(4, user.getEmail());
		pStmt.setString(5, user.getTelephone());
		pStmt.setString(6, user.getRue());
		pStmt.setString(7, user.getCodePostal());
		pStmt.setString(8, user.getVille());
		pStmt.setString(9, user.getMotDePasse());
		pStmt.setFloat(10, user.getCredit());
		pStmt.setBoolean(11, user.isAdministrateur());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
		
		// 4 -  je recupère l'id genéré et je met à jour l'objet Utilisateur
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) { // si jamais il y a un resultat
			user.setIdUtilisateur(rs.getInt(1)); //alors on utilise sa valeur pour mettre à jour l'id de l'avis
		}
		
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
	/**
	 * update(Utilisateur user) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : UtilisateurManager)
	 */
	@Override
	public void updateUser(Utilisateur user) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		
		// je remplace le premier ? de ma requête par la date de mon objet Utilisateur		
		//update UTILISATEURS set id=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=?"
		pStmt.setInt(1, user.getIdUtilisateur());
		pStmt.setString(2, user.getNom());
		pStmt.setString(3, user.getPrenom());
		pStmt.setString(4, user.getEmail());
		pStmt.setString(5, user.getTelephone());
		pStmt.setString(6, user.getRue());
		pStmt.setString(7, user.getCodePostal());
		pStmt.setString(8, user.getVille());
		pStmt.setString(9, user.getMotDePasse());
		pStmt.setFloat(10, user.getCredit());
		pStmt.setBoolean(11, user.isAdministrateur());
		pStmt.setString(12, user.getPseudo());
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
				
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}

	@Override
	public Utilisateur checkConnectUser(String login, String password) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();

		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(CHECK_UTILISATEUR);
		pStmt.setString(1, login);
		pStmt.setString(2, login);
		pStmt.setString(3, password);
		
		// 2 - je l'execute et je recupère une réference sur les resultats dans un ResultSet
		ResultSet rs = pStmt.executeQuery();

		// 4 - je parcours mes resultats pour remplir ma liste des user que je vais renvoyer
		rs.next();
		Utilisateur user = new Utilisateur(
				rs.getString("pseudo"),
				rs.getString("nom"),
				rs.getString("prenom"),
				rs.getString("email"),
				rs.getString("rue"),
				rs.getString("ville"),
				rs.getString("mot_de_passe"),
				rs.getInt("no_utilisateur"),
				rs.getString("telephone"),
				rs.getString("code_postal"),
				rs.getFloat("credit"),
				rs.getBoolean("administrateur")
				);
		return user;		
	}

	/**
	 * delete(String pseudo) peut lancer potentiellement des exception de type SQLException (il faudra le gérer dans la classe qui appelle le DAO : UtilisateurManager)
	 */
	@Override
	public void deleteUser(String pseudo) throws SQLException {
		// On fait appel à la classe ConnectionProvider pour recupérer une connexion depuis notre pool
		Connection cnx = ConnectionProvider.getConnection();
		
		// 1 - on crée une "requête préparée" à partir de la connexion recupérée et de notre template de requête SQL ( attribut INSERT)
		PreparedStatement pStmt = cnx.prepareStatement(DELETE_UTILISATEUR);
		
		// 2 - je remplace les ? de ma requête par les valeurs correspondantes
		//update UTILISATEURS set pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=?"
		pStmt.setString(1, pseudo);
		
		// 3 - j'execute la requête SQL
		pStmt.executeUpdate(); // ici , il faut faire executeUpdate() et pas executeQuery() parce qu'on modifie des données
				
		// on ferme la connexion quand tout a été ajouté
		cnx.close();
	}
	
}
