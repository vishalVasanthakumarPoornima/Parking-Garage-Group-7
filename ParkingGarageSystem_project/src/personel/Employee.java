package personel;

public interface Employee {
	String getUsername();
	String getPassword();
	boolean login(String username, String password);

}
