package services.exceptions;

public class RequiredFieldMissedException extends  RuntimeException{
    public RequiredFieldMissedException() {
        super("Warning! Missing fields");
    }
    public RequiredFieldMissedException(String message) {
        super(message);
    }
}
