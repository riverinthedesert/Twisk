package twisk.exceptions;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

public abstract class TwiskException extends Exception {

    public TwiskException(){


    }
    public TwiskException(String message){
        super(message);
    }
}
