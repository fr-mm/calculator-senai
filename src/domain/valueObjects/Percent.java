package domain.valueObjects;


public class Percent extends EquationElement {   
    
    @Override
    public boolean canBePlacedAfter(EquationElement lastElement) {
        return lastElement.isNumber();
    }
    
    public Boolean canBePlacedAfterThreeElements(EquationElement[] lastElements) {
        return lastElements[0].isNumber() && lastElements[1].isOperation() && lastElements[2].isNumber();
    }

    @Override
    public String toString() {
        return "%";
    }    
    
    @Override
    public boolean isPercent() {
        return true;
    }
}
