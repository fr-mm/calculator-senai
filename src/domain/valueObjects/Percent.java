package domain.valueObjects;


public class Percent extends EquationElement {
    
    @Override
    public Boolean canBePlacedAfter(EquationElement lastElement) {
        return lastElement instanceof Number;
    }
    
    public Boolean canBePlacedAfterThreeElements(EquationElement[] lastElements) {
        Boolean firstIsNumber = lastElements[0] instanceof Number;
        Boolean secondIsOperation = lastElements[1] instanceof Operation;
        Boolean thirdIsNumber = lastElements[2] instanceof Number;
        return firstIsNumber && secondIsOperation && thirdIsNumber;
    }

    @Override
    public String toString() {
        return "%";
    }    
}
