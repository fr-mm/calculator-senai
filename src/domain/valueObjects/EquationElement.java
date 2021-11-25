package domain.valueObjects;


abstract public class EquationElement {   
    @Override
    abstract public String toString();
    
    abstract public boolean canBePlacedAfter(EquationElement lastElement);
    
    abstract public boolean isNumber();
    
    abstract public boolean isOperation();
    
    abstract public boolean isPercent();
    
    abstract public boolean isDot();
    
    abstract public boolean isSubtract();
}
