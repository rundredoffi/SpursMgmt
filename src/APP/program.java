package APP;
import java.io.IOException;


public class program {

	public static void main(String[] args) {
		try {
			parametres.loadFichierIni("paramAppli.ini"); // Chargement des paramètres	
			APP.Menu.afficherMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}