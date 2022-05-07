import model.Component;
import model.Nmos;
import model.Resistor;
import model.Topology;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ManipulateTopology {

    private final String dir = "jsonfiles";
    private JsonHandler jsonHandler;

    public ManipulateTopology(){
        jsonHandler = new JsonHandler();
    }

    public List<Topology> queryTopologies(){
        List<Topology> topologies = new ArrayList<>();
        File data = new File(dir);
        String[] files = data.list();
        if ( files == null || files.length == 0) return null;
        for (String file: files){
            Topology topology = jsonHandler.readJsonFile(dir + "\\" + file);
            topologies.add(topology);
        }
        return topologies;
    }

    public Component[] queryDevices(String TopologyID){
        Topology topology = jsonHandler.readJsonFile(dir + "\\" + TopologyID + ".json");
        return topology.getComponents();
    }

    public String[] queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID){
        Topology topology = jsonHandler.readJsonFile(dir + "\\" + TopologyID + ".json");
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
