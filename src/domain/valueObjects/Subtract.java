package domain.valueObjects;


public class Subtract extends Operation {

    @Override
    public Number solve(Number firstNumber, Number secondNumber) {
        return firstNumber.subtract(secondNumber);
    }

    @Override
    public String toString() {
        return "-";
    }
    
    @Override
    public boolean isSubtract() {
        return true;
    }
    
}
