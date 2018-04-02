package sd.commands;

import sd.daos.UserDAO;
import sd.model.User;

public class CreateAccCheckMail {

    private String mail;

    public CreateAccCheckMail(String mail) {
        this.mail = mail;
    }

    public boolean execute(){
        User user = UserDAO.findByMail(mail);
        return freshMail(user);
    }

    private boolean freshMail(User user) {
        if (user == null) return true; //inexistent mail, good to go
        else
        	return false;
    }

    public String getMail() {
        return mail;
    }
}
