import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Topology;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonHandler {

    private final String dir = "jsonfiles\\";

    public Topology readJsonFile(String fileName){
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(fileName);
            TypeReference<Topology> typeReference = new TypeReference<>() {};
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void save(Topology topology, String path){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get(path).toFile(), topology);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readJson(String fileName){
        Topology topology = readJsonFile(fileName);
        save(readJsonFile(fileName), dir + topology.getId()+".json");
    }

    public void writeJson(String TopologyID) {
        Topology topology = readJsonFile(dir + TopologyID + ".json");
        save(topology, topology.getId()+".json");
    }

    public void deleteTopology(String TopologyID){
        Path fileToDeletePath = Paths.get(dir + TopologyID + ".json");
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
