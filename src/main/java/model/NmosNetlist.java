package model;

public class NmosNetlist{
    private String drain;
    private String gate;
    private String source;

    public NmosNetlist() {}

    public NmosNetlist(String drain, String gate, String source) {
        this.drain = drain;
        this.gate = gate;
        this.source = source;
    }

    public String getDrain() {
        return drain;
    }

    public void setDrain(String drain) {
        this.drain = drain;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String[] queryNetlist(){
        return new String[]{"drain: " + drain, "gate: " + gate, "source: " + source};
    }

}
