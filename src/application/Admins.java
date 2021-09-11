package application;

public class Admins {
	private String username;
	private String password;
	public Admins() {
		this.username = "";
		this.password = "";
	}
	public Admins(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
