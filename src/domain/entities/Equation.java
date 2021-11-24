package domain.entities;


import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;


public class Equation {
    private EquationElementRepository elementRepository;
    
    public Equation(){
        elementRepository = new EquationElementRepository();
    }
    
    public void addNumber(Number number) {
        elementRepository.add(number);
    }
    
    public void addOperation(Operation operation) {
        if (!elementRepository.isEmpty()) {
            EquationElement lastElement = elementRepository.getLast();
            Boolean lastIsNumber = lastElement instanceof Number;
            Boolean lastIsPercent = lastElement instanceof Percent;
            
            if (!lastIsNumber && !lastIsPercent) {
                elementRepository.removeLast();
            }
        }
        elementRepository.add(operation);
    }
    
    public void addPercent() {
        Percent percent = new Percent();
        elementRepository.add(percent);
    }
    
    public void reset() {
        elementRepository.clear();
    }
    
    @Override
    public String toString() {
        String result = "";
        for (EquationElement element : elementRepository.fetchAll()) {
            result += element.toString() + " ";
        }
        return result;
    }    
}
