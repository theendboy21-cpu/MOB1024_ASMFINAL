package exception;

public class DuplicateIdException extends Exception {
    private static final long serialVersionUID = 1L;

    public DuplicateIdException(String what, Object id) {
        super(String.format("Mã %s [%s] đã tồn tại trong hệ thống", what, id));
    }
}