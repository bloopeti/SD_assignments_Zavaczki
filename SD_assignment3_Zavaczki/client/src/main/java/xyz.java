import model.User;
import commands.serialization.DeserializeFromFileCommand;
import commands.serialization.SerializeToFileCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class xyz {
    public static void main(String[] args) {
        User user1 = new User("admin", "admin1");
        User user2 = new User("dummy", "dummy1");
        List<User> user = new ArrayList<User>();
        user.add(user1);
        user.add(user2);

        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("\\serialized.json"); //build abs path

        SerializeToFileCommand serializeToFileCommand = new SerializeToFileCommand(user, filePath);
        String result = serializeToFileCommand.execute();
        System.out.println(result);

        DeserializeFromFileCommand deserializeFromFileCommand = new DeserializeFromFileCommand(new User(), filePath);
        List<User> newUsers = (List<User>) deserializeFromFileCommand.execute();

        boolean eq = user.equals(newUsers);
        int i = 1;
    }
}
