package domain.valueObjects;

import java.math.BigDecimal;
import java.math.BigInteger;


public class Number extends EquationElement {
    private final BigDecimal value;
    
    public Number(BigDecimal value) {
        this.value = value;
    }
    
    public Number(BigInteger value) {
        BigDecimal parsedValue = new BigDecimal(value);
        this.value = parsedValue;
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
    
    public Number divideByTen() {
        BigDecimal newValue = value.divide(BigDecimal.TEN);
        return new Number(newValue);
    }
       
    public Number getIntegerPart() {
        BigInteger integerPart = value.toBigInteger();
        return new Number(integerPart);
    }
    
    public Number getDecimalPart() {        
        BigDecimal newValue = value.remainder(BigDecimal.ONE);
        return new Number(newValue);
    }
    
    public Number getDecimalPartSize() {     
        
        if (!isDotted()) {
            return new Number(BigInteger.ZERO);
        }
        
        Number decimalPart = getDecimalPart();
        String decimalPartAsString = decimalPart.toString();
        Integer decimalPartLength = decimalPartAsString.length() - 2;

        return new Number(decimalPartLength.toString());
    }
    
    public boolean isDotted() {
        return !getDecimalPart().value.equals(BigDecimal.ZERO);
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
