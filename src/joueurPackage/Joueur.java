package joueurPackage;

import java.util.ArrayList;
import java.util.List;

import Exceptions.PionNonExistant;
import plateauPackage.Position;

/**Classe Joueur, pour manipuler des objets de type Joueur, dans le cadre du jeu LineUp3
 * @author Victor_Bastien_Constant
 */
public class Joueur{
	
	private String name;
	private static int id=0;
	private int joueurId;
	private int nbPions;
	private List<Pion> pions;
	private int piegeArc=1;
	private int piegeCase=2;
	private int nbPionsPhaseDeploiement;
	List<Pion> pionsBloques;

	/**
	 * Constructeur Joueur
	 * @param name
	 * @param nbPions
	 */
	public Joueur(String name, int nbPions) {
		this.name = name;
		this.joueurId=id;
		this.nbPions=nbPions;
		this.nbPionsPhaseDeploiement=nbPions;
		id++;
		pions=new ArrayList<Pion>();
		pionsBloques=new ArrayList<Pion>();
		this.initialiserPions();
	}

	
	/**
	 * Initialise des pion en les associant au joueur courant
	 */
	private void initialiserPions(){
		for(int indice=0;indice<nbPions;indice++){
			this.pions.add(new Pion(this));
		}
	}

	/**
	 * Getter name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter id du joueur
	 * @return joueurId
	 */
	public int getJoueurId(){
		return this.joueurId;
	}

	/**
	 * Getter Pion spécifique 
	 * @param indice
	 * @return Pion
	 */
	public Pion getPion(int indice){
		return pions.get(indice);
	}

	/**
	 * Getter Pion en fonction d'une position
	 * @param position
	 * @return Pion
	 * @throws PionNonExistant
	 */
	public Pion getPion(Position position) throws PionNonExistant{
		for(Pion p : this.pions){
			if(p.getPosition()!=null){
				if(p.getPosition().equals(position)){
					return p;
				}
			}
		}
	throw new PionNonExistant();
	}

	/**
	 * getter nombre de Pion restants
	 * @return nombre de Pion
	 */
	public int getNbPions(){
		return this.nbPions;
	}
	
	/**
	 * getter nombre de pions restant avec diminution autoùmatique du nombre de pions
	 * @return nombre de pions restants lors de la phase de deploiement
	 */
	public int getNbPionsDeploiement(){
		return this.nbPionsPhaseDeploiement;
	}

	/**
	 * Diminue de nombre de pion lors de la phase de déploiement
	 */
	public void diminuerPionsDeploiement(){
		this.nbPionsPhaseDeploiement--;
	}

	/**
	 * Reset le nombre de pion lors de la phase de déploiement
	 */
	public void resetNbPionsDeploiement(){
		this.nbPionsPhaseDeploiement=nbPions;
	}
	/**
	 * setter d'une position sur un pion
	 * @param pion
	 * @param position
	 */
    public void setPosition(Pion pion,Position position){
        pion.setPosition(position);
    }

    /**
     * Reducteur du nombre de pion posables
     */
	public void diminuerPionsRestants(){
		this.nbPions--;
	}
	
	/**
	 * Retourne la poisition des pion du joueur courant
	 * @return List<Position>
	 */
	public List<Position> getPositions(){
		List<Position> res = new ArrayList<Position>();
		for(Pion p : this.pions) {
			res.add(p.getPosition());
		}
	return res;
	}

	/**
	 * Envoie un Pion du joueur courant dans le néant
	 * @param p
	 */
	public void retirerPion(Pion p){
		pions.remove(p);
		this.nbPions--;
	}
	
	/**
	 * setter joueurId
	 * @param joueurId
	 */
	public void setJoueurId(int joueurId) {
		this.joueurId = joueurId;
	}

	public int getPiegesArc(){
		return this.piegeArc;
	}

	public int getPiegesCase(){
		return this.piegeCase;
	}

	public void diminuerPiegeArc(){
		this.piegeArc--;
	}

	public void diminuerPiegeCase(){
		this.piegeCase--;
	}

	public void bloquerPion(Pion p){
		pionsBloques.add(p);
	}

	public boolean pionBloque(Pion p){
		return pionsBloques.contains(p);
	}
	
}
