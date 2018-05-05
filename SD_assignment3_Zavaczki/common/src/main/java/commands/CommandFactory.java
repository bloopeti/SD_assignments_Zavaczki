package commands;

import commands.*;
import commands.serialization.DeserializeCommand;
import commands.serialization.DeserializeFromFileCommand;
import commands.serialization.SerializeCommand;

import model.*;

public class CommandFactory {

    public static Command getCommand(String[] args) {
        try {
            Object target;
            switch (args[0]) {
                case "article":
                    target = new Article();
                    break;
                case "articleList":
                    target = new ResultArticles();
                    break;
                case "user":
                    target = new User();
                    break;
                case "userList":
                    target = new ResultUsers();
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
            switch (args[1]) {
                case "deserialize":
                    return new DeserializeCommand(args[2], target);
                case "deserializeFromFile":
                    return new DeserializeFromFileCommand(target, args[2]);
                case "serialize":

                default:
                    return null;

            }
        } catch (ArrayIndexOutOfBoundsException arrE) {
            return null;
        }

    }

}