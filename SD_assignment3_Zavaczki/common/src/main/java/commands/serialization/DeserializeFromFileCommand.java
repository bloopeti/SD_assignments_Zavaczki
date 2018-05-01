package commands.serialization;

import commands.Command;
import model.Article;
import model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeserializeFromFileCommand implements Command {
    private Object target;
    private String filePath;

    public DeserializeFromFileCommand(Object target, String filePath) {
        this.target = target;
        this.filePath = filePath;
    }

    public List<?> execute()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String input = br.readLine();
            //DeserializeCommand deserializeCommand = new DeserializeCommand(input, new User());
            //User newUser = (User) deserializeCommand.execute();
            if(target.getClass() == User.class)
            {
                DeserializeCommand deserializeCommand = new DeserializeCommand(input, new ArrayList<User>());
                List<User> users = (List<User>) deserializeCommand.execute();
                return users;
            }
            else if(target.getClass() == Article.class)
            {
                DeserializeCommand deserializeCommand = new DeserializeCommand(input, new ArrayList<Article>());
                List<Article> articles = (List<Article>) deserializeCommand.execute();
                return articles;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
