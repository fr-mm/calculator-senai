package domain.valueObjects;


public class Sum extends Operation {

    @Override
    public Number solve(Number firstNumber, Number secondNumber) {
        return firstNumber.sum(secondNumber);
    }

    @Override
    public String toString() {
        return "+";
    }
    
}
