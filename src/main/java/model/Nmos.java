package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("nmos")
public class Nmos extends Component{
    @JsonProperty("m(l)")
    private ComponentInfo ml;
    private NmosNetlist netlist;

    public Nmos() {
    }

    public Nmos(String type, String id, ComponentInfo ml, NmosNetlist netlist) {
        super(type, id);
        this.ml = ml;
        this.netlist = netlist;
    }

    public ComponentInfo getMl() {
        return ml;
    }

    public void setMl(ComponentInfo ml) {
        this.ml = ml;
    }

    public NmosNetlist getNetlist() {
        return netlist;
    }

    public void setNetlist(NmosNetlist netlist) {
        this.netlist = netlist;
    }
}
