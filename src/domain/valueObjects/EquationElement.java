package domain.valueObjects;


abstract public class EquationElement { 
    
    @Override
    abstract public String toString();
    
    abstract public boolean canBePlacedAfter(EquationElement lastElement);
    
    public boolean isNumber() {
        return false;
    };
    
    public boolean isOperation() {
        return false;
    };
    
    public boolean isPercent() {
        return false;
    };
    
    public boolean isDot() {
        return false;
    };
    
    public boolean isSubtract() {
        return false;
    };
    
    public boolean isSum() {
        return false;
    };
    
    public boolean isMultiply() {
        return false;
    }
    
    public boolean isDivide() {
        return false;
    }
}
