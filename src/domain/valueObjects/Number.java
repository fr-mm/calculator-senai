package domain.valueObjects;

import java.math.BigDecimal;
import java.math.BigInteger;


public class Number extends EquationElement {
    private final BigDecimal value;
    
    public Number(BigDecimal value) {
        this.value = value;
    }
    
    public Number(String value) {
        BigDecimal parsedValue = new BigDecimal(value);
        this.value = parsedValue;
    }
    
    public BigDecimal getValue() {
        return value;
    }
    
    public Number sum(Number number) {
        BigDecimal newValue = value.add(number.getValue());
        return new Number(newValue);
    }
    
    public Number subtract(Number number) {
        BigDecimal newValue = value.subtract(number.getValue());
        return new Number(newValue);
    }
    
    public Number multiply(Number number) {
        BigDecimal newValue = value.multiply(number.getValue());
        return new Number(newValue);
    }
    
    public Number divide(Number number) {
        BigDecimal newValue = value.divide(number.getValue());
        return new Number(newValue);
    }
       
    public BigInteger getIntegerPart() {
        return value.toBigInteger();
    }
    
    public BigDecimal getDecimalPart() {        
        return value.remainder(BigDecimal.ONE);
    }
    
    public BigInteger getDecimalPartSize() {     
        
        if (!isDotted()) {
            return BigInteger.ZERO;
        }
        
        BigDecimal decimalPart = getDecimalPart();
        String decimalPartAsString = decimalPart.toString();
        Integer decimalPartLength = decimalPartAsString.length() - 2;

        return new BigInteger(decimalPartLength.toString());
    }
    
    public boolean isDotted() {
        return !getDecimalPart().equals(BigDecimal.ZERO);
    }
    
    @Override
    public boolean canBePlacedAfter(EquationElement lastElement) {
        return !lastElement.isPercent();
    }
    
    @Override
    public String toString() {
        return value.toString();
    }
    
    @Override
    public boolean isNumber() {
        return true;
    }
}
