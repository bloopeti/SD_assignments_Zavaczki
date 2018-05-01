package commands.serialization;

import commands.Command;

import java.io.FileWriter;
import java.io.IOException;

public class SerializeToFileCommand implements Command {
    private Object toSerialize;
    private String filePath;

    public SerializeToFileCommand(Object toSerialize, String filePath) {
        this.toSerialize = toSerialize;
        this.filePath = filePath;
    }

    public String execute()
    {
        SerializeCommand serializeCommand = new SerializeCommand(toSerialize);
        String serialized = serializeCommand.execute();

        //String filePath = new File("").getAbsolutePath();   //build abs path
        //filePath = filePath.concat("\\serialized.json");    //build abs path
        //filePath = "serialized.json";                       //rel path

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(serialized);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serialized;
    }
}
