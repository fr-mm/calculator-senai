package domain.valueObjects;


public class Divide extends Operation {

    @Override
    public Number solve(Number firstNumber, Number secondNumber) {
        return firstNumber.divide(secondNumber);
    }

    @Override
    public String toString() {
        return "÷";
    }
    
}
