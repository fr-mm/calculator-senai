package domain.valueObjects;

import java.text.DecimalFormat;


public class Number extends EquationElement {
    private double value;
    private final DecimalFormat decimalFormat;
    
    public Number(double value) {
        this.value = value;
        decimalFormat = new DecimalFormat("###");
    }
    
    public double getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return decimalFormat.format(value);
    }
}
