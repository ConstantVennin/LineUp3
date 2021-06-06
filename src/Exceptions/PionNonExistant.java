package Exceptions;

/**Exception pour pion non existant
 */
public class PionNonExistant extends Exception{
	
	/**PionNonExistant Methode
	 */
    public PionNonExistant(){
        super("Il semblerait que ce pion ne vous appartienne pas...");
    }
}
