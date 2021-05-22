package joueurPackage;

public class Joueur{
	
	private String name;
	//private Couleur color;
	private static int id=1;
	private final int joueurId;
	
	public Joueur(String name) {
		this.name = name;
		this.joueurId=id;
		id++;
	}

	public Joueur(){
		this.joueurId=id;
		id++;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getJoueurId(){
		return this.joueurId;
	}
	
}
