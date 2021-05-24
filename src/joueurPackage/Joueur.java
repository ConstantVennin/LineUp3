package joueurPackage;

import java.util.ArrayList;
import java.util.List;

import Exceptions.PionNonExistant;
import plateauPackage.Position;

public class Joueur{
	
	private String name;
	private static int id=0;
	private final int joueurId;
	private int nbPions;
	private List<Pion> pions;
	private int piegeArc;
	private int piegeCase;
	
	public Joueur(String name, int nbPions) {
		this.name = name;
		this.joueurId=id;
		this.nbPions=nbPions;
		id++;
		pions=new ArrayList<Pion>();
		this.initialiserPions();
	}

	private void initialiserPions(){

		for(int indice=0;indice<nbPions;indice++){
			this.pions.add(new Pion(this));
		}
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

	public Pion getPion(int indice){
		return pions.get(indice);
	}

	public Pion getPion(Position position) throws PionNonExistant{
		for(Pion p : this.pions){
			if(p.getPosition().equals(position)){
				return p;
			}
		}
	throw new PionNonExistant();
	}

	public int getNbPions(){
		return this.nbPions;
	}
	
    public void setPosition(Pion pion,Position position){
        pion.setPosition(position);
    }

	public void diminuerPionsRestants(){
		this.nbPions--;
	}
	
	public List<Position> getPositions(){
		List<Position> res = new ArrayList<Position>();
		for(Pion p : this.pions) {
			res.add(p.getPosition());
		}
	return res;
	}
	
}
