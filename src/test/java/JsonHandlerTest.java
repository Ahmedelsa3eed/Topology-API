import org.junit.jupiter.api.Test;

class JsonHandlerTest {

    JsonHandler jsonHandler = new JsonHandler();

    @Test
    void readJson() {
        //Read a topology from a given JSON file and store it in the memory
        jsonHandler.readJson("topology.json");
    }

    @Test
    void deleteTopology() {
        //Delete a given topology from memory
        jsonHandler.deleteTopology("top1");
    }

    @Test
    void writeJson() {
        //Write a given topology from the memory to a JSON file
        jsonHandler.writeJson("top2");
    }
}