package sd.model;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
	private String mail;
	private String pass;
	private String pass_nohash;
	private int is_admin;

	public User(String mail, String pass, String pass_nohash, int is_admin) {
		super();
		this.mail = mail;
		this.pass = pass;
		this.pass_nohash = pass_nohash;
		if (is_admin == 0 || is_admin == 1)
			this.is_admin = is_admin;
		else
			throw new IllegalArgumentException("is_admin value out of range! (0 / 1)");
	}

	public User(String mail, String pass, String pass_nohash) {
		super();
		this.mail = mail;
		this.pass = pass;
		this.pass_nohash = pass_nohash;
		this.is_admin = 0;
	}
	
	public User(String mail, String pass_nohash) {
		super();
		this.mail = mail;
		this.pass = DigestUtils.sha1Hex(pass_nohash);
		this.pass_nohash = pass_nohash;
		this.is_admin = 0;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass_nohash() {
		return pass_nohash;
	}

	public void setPass_nohash(String pass_nohash) {
		this.pass_nohash = pass_nohash;
	}

	public int getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(int is_admin) {
		this.is_admin = is_admin;
	}
	
	public boolean isAdmin()
	{
		if(this.is_admin == 1)
			return true;
		else
			return false;
	}
}
