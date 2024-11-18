package APP;
import java.io.*;
import java.util.*;

public class parametres {
	
	// Map pour stocker le contenu du fichier
	private static Map<String, Map<String, String>> data = new HashMap<>();
	
	// Méthode pour charger les clé du fichier
	public static void loadFichierIni(String cheminFichier) throws IOException{
		data.clear();
		try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))){
			String ligne;
			String ligneCourant = null;
			
			while((ligne = reader.readLine())!= null) {
				ligne = ligne.trim(); // Suppression des espaces
				
				// Ignorer les commentaires => Continue = skip ligne
				if (ligne.startsWith("//")|| ligne.startsWith(";")|| ligne.isEmpty()) {
					continue;
				}
				if (ligne.startsWith("[") && ligne.endsWith("]")) {
					ligneCourant = ligne.substring(1,ligne.length() - 1).trim(); // Récupérer le titre du type de données
					data.put(ligneCourant, new HashMap<>());
				} else if(ligne.contains("=") && ligneCourant != null) {
					String [] cleValeur = ligne.split("=",2);// Division de la ligne par 2 au niveau du séparateur "="
					String cle = cleValeur[0].trim();
					String valeur = cleValeur[1].trim();
					data.get(ligneCourant).put(cle, valeur);
				}
			}
		}
	}
	public static String getValeur(String section, String cle) {
		if(data.containsKey(section)&& data.get(section).containsKey(cle)) {
			return data.get(section).get(cle);
		}
		return null;
	}
}
