package exception;

public class ChampionShipException extends Exception {

    public ChampionShipException(String errorMessage) {
        super(errorMessage);
    }

    public ChampionShipException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
