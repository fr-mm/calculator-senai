package domain.entities;


import java.util.List;
import java.util.ArrayList;


import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;


public class Equation {
    private List<EquationElement> elements;
    
    public Equation(){
        elements = new ArrayList<>();
    }
    
    public void addNumber(Number number) {
        elements.add(number);
    }
    
    public void addOperation(Operation operation) {
        elements.add(operation);
    }
    
    public void reset() {
        elements.clear();
    }
    
    @Override
    public String toString() {
        String result = "";
        for (EquationElement element : elements) {
            result += element.toString() + " ";
        }
        return result;
    }
    
}
