package domain.valueObjects;


public class Dot extends EquationElement {
    
    @Override
    public String toString() {
        return ",";
    }

    @Override
    public boolean canBePlacedAfter(EquationElement lastElement) {
        boolean result = false;
        if (lastElement.isNumber()) {
            Number lastNumber = (Number)lastElement;
            if (!lastNumber.isDotted()) {
                result = true;
            }
        }
        else if (lastElement.isOperation()) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean isDot() {
        return true;
    }
}
