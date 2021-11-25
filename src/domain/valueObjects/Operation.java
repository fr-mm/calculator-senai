package domain.valueObjects;


public abstract class Operation extends EquationElement {
    
    abstract public Number solve(Number firstNumber, Number secondNumber);
     
    @Override
    public Boolean canBePlacedAfter(EquationElement lastElement){
        Boolean lastIsNumber = lastElement instanceof Number;
        Boolean lastIsPercent = lastElement instanceof Percent;
        return lastIsNumber || lastIsPercent;
    }
      
}
