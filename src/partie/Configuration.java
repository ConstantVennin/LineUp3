package partie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Configuration {
	private static String chemin = System.getProperty("user.dir") + File.separator + "res" + File.separator;
	private static String nom = "configuration.txt";
	
	private static String readConfig(int nbConfig) throws FileNotFoundException, IOException {
		String res = "";
		try(BufferedReader br = new BufferedReader(new FileReader(chemin+nom))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while(line != null) {
			sb.append(line);
			line = br.readLine();
			}
			res = sb.toString();
		} catch(FileNotFoundException e) {
			System.out.println("File not found: ");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("Reading error: " + e.getMessage());
			e.printStackTrace();
		}
		String[] tab = res.split(":");
		String config = tab[nbConfig];
		if(nbConfig>5) {
			config = config.substring(0, config.indexOf("-"));
		}else {
			config = config.substring(0, config.indexOf("/"));
		}
		
		return config;
	}
	

	
	public static int getNbPiegeCases() throws NumberFormatException, FileNotFoundException, IOException {
		if(readConfig(1)!=null && !readConfig(1).equals("")) {
			return Integer.parseInt(readConfig(1));
		}
		return 0;
	}
	
	public static int getNbPiegeArcs() throws NumberFormatException, FileNotFoundException, IOException {
		if(readConfig(2)!=null && !readConfig(2).equals("")) {
			return Integer.parseInt(readConfig(2));
		}
		return 0;
	}
	
	public static int getNbPionParPers() throws NumberFormatException, FileNotFoundException, IOException {
		if(readConfig(3)!=null && !readConfig(3).equals("")) {
			return Integer.parseInt(readConfig(3));
		}
		return 0;
	}
	
	public static int getNbDeSommets() throws NumberFormatException, FileNotFoundException, IOException {
		if(readConfig(4)!=null && !readConfig(4).equals("")) {
			return Integer.parseInt(readConfig(4));
		}
		return 0;
	}
	
	public static int getNbDeCouches() throws NumberFormatException, FileNotFoundException, IOException {
		if(readConfig(5)!=null && !readConfig(5).equals("")) {
			return Integer.parseInt(readConfig(5));
		}
		return 0;
	}
	
	public static boolean jouerContreIA() throws FileNotFoundException, IOException {
		String choixIA = readConfig(6).toUpperCase();
		if(choixIA.equals("OUI")) {
			return true;
		}else {
			return false;
		}
	}
	
//	tests du bon fonctionnement
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("piege cases : " + getNbPiegeCases());
		System.out.println("pieges arcs : " + getNbPiegeArcs());
		System.out.println("pions par personne : " + getNbPionParPers());
		System.out.println("sommets : " + getNbDeSommets());
		System.out.println("couches : " + getNbDeCouches());
		System.out.println("jouer Contre une IA : " + jouerContreIA());
	}
}
