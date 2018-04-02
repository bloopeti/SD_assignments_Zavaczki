package sd.commands;

import sd.daos.UserDAO;
import sd.model.User;

public class CreateUserCommand {
	
	private User user;
    
    public CreateUserCommand(String mail, String pass_nohash) {
    	this.user = new User(mail, pass_nohash);
    }

    public void execute(){
    	UserDAO.insert(user);
    }
}
