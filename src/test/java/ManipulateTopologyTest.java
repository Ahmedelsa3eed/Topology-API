import model.Topology;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManipulateTopologyTest {

    ManipulateTopology manipulateTopology =
            new ManipulateTopology();

    @Test
    void queryTopologies() {
        List<Topology> topologyList = manipulateTopology.queryTopologies();
        for (Topology top: topologyList) {
            System.out.println(top.getId());
        }
        assertEquals(2, topologyList.size());
    }

    @Test
    void queryDevicesWithNetlistNode(){
        String[] list =
                manipulateTopology.queryDevicesWithNetlistNode("top1", "res1");
        for (String s: list) {
            System.out.println(s);
        }
    }
}