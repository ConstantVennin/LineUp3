package partie;


import java.util.ArrayList;
import java.util.Collections;

import plateauPackage.Plateau;
import plateauPackage.Position;
import plateauPackage.Situations;


public class IA {
	public Position[] placerPionIA(Plateau p, Situations s) {
		ArrayList<Position> pionPossede = trouverPion(p,s);
		Position pionBouge = null;
		Position arrive = null;
		Collections.shuffle(pionPossede);
		boolean libreAutour = false;
		int u = 0;
		while(libreAutour || u==pionPossede.size()) {
			pionBouge = pionPossede.get(u);
			for(int i = -1; i<2; i++) {
				for(int j = -1 ; j<2 ; j++) {
					if(!(i==0 && j==0)) {
						
						arrive = new Position(pionBouge.getCouche()+i, pionBouge.getSommet()+j);
						if(!(arrive.getCouche()>p.getCouches() || arrive.getSommet()>p.getSommets())) {
							
							if(p.arcExiste(pionBouge, arrive)){
								if(p.case_libre(arrive)) {
									libreAutour = true;
								}
							}
							
						}
						
					}
				}
			}
			u++;
		}
		Position[] res = new Position[] {pionBouge, arrive};
		return res;
	}
	
	public static ArrayList<Position> trouverPion(Plateau p, Situations s){
		ArrayList<Position> res = new ArrayList<Position>();
		for(int x = 0 ; x<p.getCouches(); x++) {
			for(int y = 0 ; y<p.getSommets(); y++) {
				if(p.getPlateau(x,y) == s) {
					res.add(new Position(x, y));
				}
			}
		}
		return res;
	}
}