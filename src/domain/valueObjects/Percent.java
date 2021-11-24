package domain.valueObjects;


public class Percent extends EquationElement {
    
    @Override
    public Boolean canBePlacedAfter(EquationElement lastElement) {
        return lastElement instanceof Number;
    }

    @Override
    public String toString() {
        return "%";
    }    
}
