package med.jsrdev.api.infra.exceptions;

public class ValidationIntegrity extends RuntimeException {
    public ValidationIntegrity(String idNotFound) {
        super(idNotFound);
    }
}
