package datenhaltung;

public class PersistenzException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PersistenzException() {
		super();
	}
	
	public PersistenzException(String message) {
		super(message);
	}
	
}
