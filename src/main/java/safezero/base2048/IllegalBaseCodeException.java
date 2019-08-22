package safezero.base2048;

public class IllegalBaseCodeException extends Exception {

    public IllegalBaseCodeException(String characterOrWord) {
        super(String.format("Illegal character: %s", characterOrWord));
    }

}
