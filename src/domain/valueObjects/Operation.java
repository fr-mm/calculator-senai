package domain.valueObjects;


public abstract class Operation extends EquationElement {    
    abstract public Number solve(Number firstNumber, Number secondNumber);
     
    @Override
    public boolean canBePlacedAfter(EquationElement lastElement){
        Boolean lastIsNumber = lastElement instanceof Number;
        Boolean lastIsPercent = lastElement instanceof Percent;
        return lastIsNumber || lastIsPercent;
    }
    
    @Override
    public boolean isOperation() {
        return true;
    }
    
    @Override
    public boolean isNumber() {
        return false;
    }
    
    @Override
    public boolean isPercent() {
        return false;
    }
    
    @Override
    public boolean isDot() {
        return false;
    }
    
    @Override
    public boolean isSubtract() {
        return false;
    }
}
