// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Component;
import model.Nmos;
import model.Resistor;
import model.Topology;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ManipulateTopology implements TopologyAPI {

    private final String dir = "jsonfiles\\";

    private Topology readJsonFile(String fileName){
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
        save(readJsonFile(fileName), dir + topology.getId()+ ".json");
    }

    public void writeJson(String TopologyID) {
        Topology topology = readJsonFile(dir + TopologyID + ".json");
        save(topology, "output_json\\"+topology.getId()+".json");
    }

    public List<Topology> queryTopologies(){
        List<Topology> topologies = new ArrayList<>();
        File data = new File("jsonfiles");
        String[] files = data.list();
        if ( files == null || files.length == 0) return null;
        for (String file: files){
            Topology topology = readJsonFile(dir + file);
            topologies.add(topology);
        }
        return topologies;
    }

    /**
     * return true if the file deleted false otherwise.
     * */
    public boolean deleteTopology(String TopologyID){
        Path fileToDeletePath = Paths.get(dir + TopologyID + ".json");
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            System.out.println(TopologyID + " is not found in memory!");
            return false;
        }
        return true;
    }

    public Component[] queryDevices(String TopologyID){
        Topology topology =readJsonFile(dir + TopologyID + ".json");
        return topology.getComponents();
    }

    public String[] queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID){
        Topology topology = readJsonFile(dir + TopologyID + ".json");
        for (Component component: topology.getComponents()) {
            if(component.getId().equals(NetlistNodeID)){
                if(component instanceof Resistor)
                    return ((Resistor) component).getNetlist().queryNetlist();
                if(component instanceof Nmos)
                    return ((Nmos) component).getNetlist().queryNetlist();
            }
        }
        return null;
    }
}
