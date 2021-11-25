package domain.valueObjects;

import java.text.DecimalFormat;


public class Number extends EquationElement {
    private final double value;
    
    public Number(double value) {
        this.value = value;
    }
    
    public double getValue() {
        return value;
    }
       
    public int getIntegerPart() {
        return(int)this.getValue();
    }
    
    public int getDecimalPart() {        
        Double parsedValue = value;
        String valueAsString = parsedValue.toString();
        String decimalPartAsString = valueAsString.split("\\.")[1];
        int decimalPart = Integer.parseInt(decimalPartAsString);
        
        return decimalPart;
    }
    
    public int getDecimalPartSize() {
        int decimalPart = getDecimalPart();
        if (decimalPart > 0) {
            String decimalPartAsString = String.valueOf(getDecimalPart());

            return decimalPartAsString.length();
        }
        else {
            return 0;
        }
    }
    
    public boolean isDotted() {
        return getDecimalPartSize() > 0;
    }
    
    @Override
    public boolean canBePlacedAfter(EquationElement lastElement) {
        return !lastElement.isPercent();
    }
    
    @Override
    public String toString() {
        String format = "#";
        int decimalPartSize = getDecimalPartSize();
        if (decimalPartSize > 0) {
            String decimalPartFormat = new String(new char[decimalPartSize]).replace("\0", "#");
            format += "." + decimalPartFormat;
        }
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(value);
    }
    
    @Override
    public boolean isNumber() {
        return true;
    }
}
