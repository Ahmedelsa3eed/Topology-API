import model.Topology;

import java.util.List;

public interface TopologyAPI {
    void readJson(String fileName);
    void writeJson(String TopologyID);
    List<Topology> queryTopologies();
    void deleteTopology(String TopologyID);
    void queryDevices(String TopologyID);
    void queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID);
}
