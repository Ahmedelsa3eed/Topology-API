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

    /**
     * Read a topology from a given JSON file and store it in the memory.
     * @param fileName the absolute path of the topology
     * */
    public void readJson(String fileName){
        Topology topology = readJsonFile(fileName);
        save(readJsonFile(fileName), dir + topology.getId()+ ".json");
    }

    /**
     * Write a given topology from the memory to a JSON file.
     * @param TopologyID the topology id specified at that json file
     * */
    public void writeJson(String TopologyID) {
        Topology topology = readJsonFile(dir + TopologyID + ".json");
        save(topology, "output_json\\"+topology.getId()+".json");
    }

    /**
     * Returns query about which topologies are currently in the memory.
     * @return List of topologies resident in memory
     * */
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
     * Delete a given topology from memory.
     * @param TopologyID the topology id specified at that json file
     * @return true if the file deleted successfully, false otherwise
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

    /**
     * Returns query about which devices are in a given topology.
     * @param TopologyID the topology id specified at that json file
     * @return Array of components found at that topology
     * */
    public Component[] queryDevices(String TopologyID){
        Topology topology =readJsonFile(dir + TopologyID + ".json");
        return topology.getComponents();
    }

    /**
     * Returns query about which devices are connected to a given netlist node in
     * a given topology.
     * @param TopologyID the topology id specified at that json file
     * @param NetlistNodeID the id of certain component at that topology
     * @return Array of Strings which represents the connected devices of that netlist
     * */
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

    /**
     * Returns a Topology Object that can be saved to memory.
     * @param fileName the absolute path of the topology
     * @return topology found at that location
     * */
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

    /**
     * Saves the given topology at given location.
     * @param topology Topology Object to be saved in memory
     * @param path location to save the given topology
     * */
    private void save(Topology topology, String path){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get(path).toFile(), topology);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
