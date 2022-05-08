import model.Component;
import model.Topology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManipulateTopologyTest {

    ManipulateTopology manipulateTopology =
            new ManipulateTopology();

    @Test
    @DisplayName("Read a topology from a given JSON file and store it in the memory")
    void readJson() {
        manipulateTopology.readJson("topology.json");
        File data = new File("jsonfiles");
        String[] files = data.list();

        assert files != null;
        assertEquals("top1.json", files[0]);
    }

    @Test
    @DisplayName("Write a given topology from the memory to a JSON file")
    void writeJson() {
        manipulateTopology.writeJson("top1");
        File data = new File("output_json");
        String[] files = data.list();

        assert files != null;
        assertEquals("top1.json", files[0]);
    }

    @Test
    @DisplayName("Delete a given topology from memory")
    void deleteTopology() {
        boolean result = manipulateTopology.deleteTopology("top1");
        assertTrue(result);
    }

    @Test
    @DisplayName("Query about which topologies are currently in the memory")
    void queryTopologies() {
        List<Topology> topologyList = manipulateTopology.queryTopologies();
        File data = new File("jsonfiles");
        String[] files = data.list();
        assert files != null;
        List<String> valid = Arrays.asList(files);
        for (Topology top: topologyList) {
            assertTrue(valid.contains(top.getId()+".json"));
        }
    }

    @Test
    @DisplayName("Query about which devices are in a given topology")
    void queryDevices() {
        Component[] components =
                manipulateTopology.queryDevices("top1");
        List<String> valid = Arrays.asList("res1", "m1");
        for (Component comp: components) {
            assertTrue(valid.contains(comp.getId()));
        }
    }

    @Test
    @DisplayName("which devices are connected to a given netlist node in a given topology")
    void queryDevicesWithNetlistNode(){
        String[] list =
                manipulateTopology.queryDevicesWithNetlistNode("top1", "res1");
        List<String> valid = Arrays.asList("t1: vdd", "t2: n1");
        for (String s: list) {
            assertTrue(valid.contains(s));
        }
    }
}