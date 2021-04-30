package joueurPackage;

public class Joueur{
	
	private String name;
	private Couleur color;
	
	public Joueur(String name,Couleur color) {
		this.name = name;
		this.color = color;
	}
	
	public Joueur(Couleur color) {
		this("default", color);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
