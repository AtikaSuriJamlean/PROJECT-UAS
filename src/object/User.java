package object;

public class User {
	private String username, pass;
	private boolean admin;

	public User(String username, String password, boolean isAdmin) {
		this.username = username;
		this.pass = password;
		this.admin = isAdmin;
	}

	public User(User usr) {
		this.username = usr.getUsername();
		this.pass = usr.getPassword();
		this.admin = usr.admin();
	}

	public String getUsername() {
		return username;
	}

	public boolean admin() {
		return admin;
	}

	public String getPassword() {
		return pass;
	}
}
