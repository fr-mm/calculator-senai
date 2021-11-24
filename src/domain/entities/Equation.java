package domain.entities;


import java.util.List;
import java.util.ArrayList;


import domain.valueObjects.EquationElement;


public class Equation {
    private List<EquationElement> elements;
    
    public Equation(){
        reset();
    }
    
    private void reset() {
        elements = new ArrayList<>();
    }
}
