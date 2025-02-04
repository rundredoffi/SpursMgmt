package UTILISATEURS;

public class UTILISATEUR {
	private String username;
	private String habilitations;
	/**
	 * Utilisateur de l'application
	 * 
	 * @param username
	 * @param password
	 * @param habilitations
	 */
	public UTILISATEUR(String username, String habilitations) {
		super();
		this.username = username;
		this.habilitations = habilitations;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHabilitations() {
		return habilitations;
	}
	public void setHabilitations(String habilitations) {
		this.habilitations = habilitations;
	}
	@Override
	public String toString() {
		return "UTILISATEUR [username=" + username + ", habilitations=" + habilitations
				+ "]";
	}
	
}
