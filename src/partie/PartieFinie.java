package partie;

import plateauPackage.Plateau;
import plateauPackage.Situations;

public class PartieFinie {
	
	
	
	public boolean partieFinie(Plateau plateau) {
		boolean partieFinie = false;
		
		for(int i = 0 ; i < (plateau.getCouches() - 2) ; i++) {
			for(int j = 0 ; j < (plateau.getSommets() / 2) ; j++) {
				if(verifCoucheVarie( i+2 , (j+1)*2 , plateau)) {
					return true;
				}
			}
		}
		
		for(int i = 0 ; i < plateau.getCouches() ; i++ ) {
			for(int j = 0 ; j < plateau.getSommets() / 2 ; j++) {
				if(verifSommetVarie( i+1 , (j+1)*2 , plateau)) {
					return true;
				}
			}
		}
		return false;	
	}
	
	
	
	
	private boolean verifCoucheVarie(int couche, int sommet, Plateau plateau) {
		
		Situations s = Situations.JOUEUR1;
		
		for(int idx = 0 ; idx < 2 ; idx ++) {
			
			if(plateau.getCase(couche,sommet) == s ) {
				if(plateau.getCase(couche - 1, sommet) == s ) {
					if(plateau.getCase(couche + 1, sommet) == s ) {
						return true;
					}
				}
			}
			s = Situations.JOUEUR2;
		}
		return false;
	}
	
	
	
	private boolean verifSommetVarie(int couche, int sommet, Plateau plateau) {
		
		Situations s = Situations.JOUEUR1;
		
		for(int idx = 0 ; idx < 2 ; idx++) {
			
			if(plateau.getCase(couche, sommet) == s) {
				if(plateau.getCase(couche, sommet - 1) == s) {
					if(plateau.getSommets() == sommet) {
						if(plateau.getCase(couche, 1) == s) {
							return true;
						}
					}else if(plateau.getCase(couche, sommet + 1) == s) {
							return true;
					}
				}
			}
			return false;
		}
	}
	
	
	
}
