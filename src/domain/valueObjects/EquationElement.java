package domain.valueObjects;


abstract public class EquationElement {
    @Override
    abstract public String toString();
    
    abstract public Boolean canBePlacedAfter(EquationElement lastElement);
}
