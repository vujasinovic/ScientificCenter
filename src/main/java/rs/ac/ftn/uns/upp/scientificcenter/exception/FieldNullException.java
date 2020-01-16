package rs.ac.ftn.uns.upp.scientificcenter.exception;

public class FieldNullException extends RuntimeException {
    private String message;

    public FieldNullException(String message) {
        super(message);
    }
}
