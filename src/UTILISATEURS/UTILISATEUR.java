package UTILISATEURS;

public class UTILISATEUR {
	private String username;
	private String password;
	private String habilitations;
	/**
	 * Utilisateur de l'application
	 * 
	 * @param username
	 * @param password
	 * @param habilitations
	 */
	public UTILISATEUR(String username, String password, String habilitations) {
		super();
		this.username = username;
		this.password = password;
		this.habilitations = habilitations;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHabilitations() {
		return habilitations;
	}
	public void setHabilitations(String habilitations) {
		this.habilitations = habilitations;
	}
	@Override
	public String toString() {
		return "UTILISATEUR [username=" + username + ", password=" + password + ", habilitations=" + habilitations
				+ "]";
	}
	
}
