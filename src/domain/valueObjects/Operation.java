package domain.valueObjects;


public abstract class Operation extends EquationElement {    
    abstract public Number solve(Number firstNumber, Number secondNumber);
     
    @Override
    public boolean canBePlacedAfter(EquationElement lastElement){
        return lastElement.isNumber() || lastElement.isPercent();
    }
    
    @Override
    public boolean isOperation() {
        return true;
    }
}
