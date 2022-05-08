import model.Component;
import model.Topology;

import java.util.List;

public interface TopologyAPI {
    void readJson(String fileName);
    void writeJson(String TopologyID);
    List<Topology> queryTopologies();
    boolean deleteTopology(String TopologyID);
    Component[] queryDevices(String TopologyID);
    String[] queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID);
}
