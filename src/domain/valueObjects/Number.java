package domain.valueObjects;

import java.text.DecimalFormat;


public class Number extends EquationElement {
    private final double value;
    private final DecimalFormat decimalFormat;
    
    public Number(double value) {
        this.value = value;
        decimalFormat = new DecimalFormat("###");
    }
    
    public double getValue() {
        return value;
    }
    
    public Number concatenate(Number number) {
        double finalValue = this.value * 10 + number.value;
        return new Number(finalValue);
    }
    
    @Override
    public boolean canBePlacedAfter(EquationElement lastElement) {
        return !(lastElement instanceof Percent);
    }
    
    @Override
    public String toString() {
        return decimalFormat.format(value);
    }
    
    @Override
    public boolean isNumber() {
        return true;
    }
}
