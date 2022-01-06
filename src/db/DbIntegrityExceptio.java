package db;

public class DbIntegrityExceptio extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DbIntegrityExceptio(String msg) {
		super(msg);
	}
	
}
