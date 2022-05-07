package model;

public class  ComponentInfo {
    private Double Default;
    private Double min;
    private Double max;

    public ComponentInfo() {}

    public ComponentInfo(Double aDefault, Double min, Double max) {
        Default = aDefault;
        this.min = min;
        this.max = max;
    }

    public Double getDefault() {
        return Default;
    }

    public void setDefault(Double aDefault) {
        Default = aDefault;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
