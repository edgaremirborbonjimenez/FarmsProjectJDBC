package org.example.utils.enums;

public enum UnitMeasurement {
    kg("kg"),
    lb("lb"),
    oz("oz"),
    ml("ml"),
    L("L"),
    pc("pc");

    public final String unit;

    UnitMeasurement(String unit){
        this.unit = unit;
    }
}
