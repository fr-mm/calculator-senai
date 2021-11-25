package domain.valueObjects;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;


public class Number extends EquationElement {
    private final int MAX_DECIMAL_PLACES = 8; 
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
        BigDecimal newValue = value.divide(number.getValue(), MAX_DECIMAL_PLACES, RoundingMode.HALF_UP);
        return new Number(newValue);
    }
    
    public Number divideByTen() {
        Number ten = new Number(BigDecimal.TEN);
        return divide(ten);
    }
    
    public Number divideByOneHundred() {
        Number oneHundred = new Number("100");
        return divide(oneHundred);
    }
    
    public Number multiplyByTen() {
        Number ten = new Number(BigDecimal.TEN);
        return multiply(ten);
    }
    
    public Number invertPolarity() {
        Number minusOne = new Number("-1");
        return multiply(minusOne);
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
    
    public boolean isLessThen(Number number) {
        return value.compareTo(number.getValue()) == -1;
    }
    
    public boolean maxDecimalPlacesReached() {
        Number maxDecimalPlaces = new Number(String.valueOf(MAX_DECIMAL_PLACES));
        return !isLessThen(maxDecimalPlaces);
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
        String valueAsString = value.toPlainString();
        return removeDecimalZeros(valueAsString);
    }
    
    @Override
    public boolean isNumber() {
        return true;
    }
    
    private String removeDecimalZeros(String numberAsString) {
        
        int lastIndex = numberAsString.length() - 1;
        char lastChar = numberAsString.charAt(lastIndex);
        
        boolean isDotted = numberAsString.contains(".");
        boolean endsWithZero = lastChar == '0';
        boolean endsWithDot = lastChar == '.';
        
        if (isDotted && (endsWithZero || endsWithDot)) {
            numberAsString = numberAsString.substring(0, lastIndex);
            return removeDecimalZeros(numberAsString);

        }
        else {
            return numberAsString;
        } 
    }
}
