package joueurPackage;

public class Joueur{
	
	private String name;
	//private Couleur color;
	private static int id;
	private final int joueurId;
	
	/*public Joueur(String name,Couleur color) {
		this.name = name;
		this.color = color;
		this.joueurId=id;
		id++;
	}*/

	public Joueur(){
		this.joueurId=id;
		id++;
	}
	
	/*public Joueur(Couleur color) {
		this("default", color);
	}*/
	
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
