package domain.entities;


import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;


public class Equation {
    private final EquationElementRepository elementRepository;
    
    public Equation(){
        elementRepository = new EquationElementRepository();
    }
    
    public void addNumber(Number number) {
        elementRepository.add(number);
    }
    
    public void addOperation(Operation operation) {
        if (!elementRepository.isEmpty()) {
            EquationElement lastElement = elementRepository.getLast();
            
            if (!operation.canBePlacedAfter(lastElement)) {
                elementRepository.removeLast();
            }
        }
        elementRepository.add(operation);
    }
    
    public void addPercent() {
        Percent percent = new Percent();
        if (!elementRepository.isEmpty()) {
            EquationElement lastElement = elementRepository.getLast();
            if (percent.canBePlacedAfter(lastElement)){
                elementRepository.add(percent);
            }
        }
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
