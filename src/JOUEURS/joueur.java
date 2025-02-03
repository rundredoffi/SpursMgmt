package JOUEURS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import APP.parametres;

public class joueur {
	private String nom;
	private String prenom;
	private Integer age;
	private Integer poid;
	private Integer taille;
	private String photoPath;
	public String getNom() {
		return nom;
	}
	/**
	 * Objet représentant un joueur
	 * 
	 * @param nom
	 * @param prenom
	 * @param age
	 * @param poid
	 * @param taille
	 * @param photo
	 * @author jouin-n
	 */
	public joueur(String nom, String prenom, Integer age, Integer poid, Integer taille, String photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.poid = poid;
		this.taille = taille;
		this.photoPath = photo;
	}

	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getPoid() {
		return poid;
	}
	public void setPoid(Integer poid) {
		this.poid = poid;
	}
	public Integer getTaille() {
		return taille;
	}
	public void setTaille(Integer taille) {
		this.taille = taille;
	}
	/**
	 * Méthode poru afficher transformé un joueur en ligne de texte
	 */
	@Override
	public String toString() {
		return "Nom : " + nom +"\n Prenom :" + prenom + "\n Age : " + age + " ans\n Poid : " + poid + "\n Taille : " + taille
				+ " cm\n";
	}
	/**
	 * Méthode pour insérer l'objet joueur en base de données
	 * @author jouin-n
	 */
	public void putInBdd() { // Envoie du joueur en base de données
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = DriverManager.getConnection(parametres.getValeur("bdd", "cheminbd") + "user=" + parametres.getValeur("bdd", "id") + "&password=" + parametres.getValeur("bdd", "psw") + "&serverTimezone=UTC");
	        String sqlInsert = "INSERT INTO `joueurs` (`nom`, `prénom`, `age`, `poid`, `taille`, `photo`) VALUES (?, ?, ?, ?, ?, ?);";
	        PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
	        preparedStatement.setString(1, nom); // Correspond à la 1ère colonne
	        preparedStatement.setString(2, prenom); // Correspond à la 2ème colonne
	        preparedStatement.setInt(3, age);    // Correspond à la 3ème colonne
	        preparedStatement.setInt(4, poid); // Correspond à la 4ème colonne
	        preparedStatement.setInt(5, taille); // Correspond à la 5ème colonne
	        preparedStatement.setString(6, photoPath); // Correspond à la 6ème colonne
	        int rowsAffected = preparedStatement.executeUpdate();
	        // Vérification si l'insertion a réussi
	        if (rowsAffected > 0) {
	            System.out.println("Le joueur " + nom + " " + prenom + " a été ajoutée à la base");
	        } else {
	            System.out.println("Aucune ligne n'a été insérée.");
	        }
	    } catch (SQLException ex) {
	        // handle any errors
	        System.out.println("SQLException: " + ex.getMessage());
	        System.out.println("SQLState: " + ex.getSQLState());
	        System.out.println("VendorError: " + ex.getErrorCode());
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException sqlEx) { } // ignore
	            rs = null;
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException sqlEx) { } // ignore
	            stmt = null;
	        }
	    }
	}
}