package domain.valueObjects;


public class Number extends EquationElement {
    private double value;
    
    public Number(double value) {
        this.value = value;
    }
    
    public double getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
