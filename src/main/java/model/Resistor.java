package model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("resistor")
public class Resistor extends Component{
    private ComponentInfo resistance;
    private ResistorNetlist netlist;

    public Resistor() {
    }

    public Resistor(String type, String id, ComponentInfo resistance, ResistorNetlist netlist) {
        super(type, id);
        this.resistance = resistance;
        this.netlist = netlist;
    }

    public ComponentInfo getResistance() {
        return resistance;
    }

    public void setResistance(ComponentInfo resistance) {
        this.resistance = resistance;
    }

    public ResistorNetlist getNetlist() {
        return netlist;
    }

    public void setNetlist(ResistorNetlist netlist) {
        this.netlist = netlist;
    }
}
