package domain.valueObjects;


public class Dot extends EquationElement {

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public boolean canBePlacedAfter(EquationElement lastElement) {
        return lastElement.isNumber();
    }

    @Override
    public boolean isDot() {
        return true;
    }
}
