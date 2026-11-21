package exception;

public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String what, Object id) {
        super(String.format("Không tìm thấy %s với mã [%s]", what, id));
    }
}