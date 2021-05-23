package Exceptions;

public class PionNonExistant extends Exception{
   
    public PionNonExistant(){
        super("Il semblerait que ce pion ne vous appartienne pas...");
    }
}
