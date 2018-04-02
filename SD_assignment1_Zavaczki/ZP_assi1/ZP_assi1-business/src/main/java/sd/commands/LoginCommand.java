package sd.commands;

import sd.daos.UserDAO;
import sd.model.User;

import org.apache.commons.codec.digest.DigestUtils;

public class LoginCommand {

    private String mail;
    private String pass;
    private String passNoHash;

    public LoginCommand(String mail, String passNoHash, boolean isAdmin) {
        this.mail = mail;
		this.pass = DigestUtils.sha1Hex(passNoHash);
        this.passNoHash = passNoHash;
    }

    public LoginCommand(String mail, String passNoHash) {
        this.mail = mail;
		this.pass = DigestUtils.sha1Hex(passNoHash);
        this.passNoHash = passNoHash;
    }

    public int execute(){
        User user = UserDAO.findByMail(mail);
        return authenticate(user);
    }

    private int authenticate(User user) {
        if (user == null) return -1; //incorrect mail
        if (!user.getMail().equals(mail)) return -1; //wrong mail
        if (!user.getPass().equals(pass)) return -2; // wrong pass
        if (!user.isAdmin()) 
        	return 0; // user not admin
        else
        	return 1; // user admin
    }

    public String getMail() {
        return mail;
    }
}
