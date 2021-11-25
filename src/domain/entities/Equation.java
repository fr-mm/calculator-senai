package domain.entities;


import domain.repositories.EquationElementRepository;
import domain.services.AddNumberToRepositoryService;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;


public class Equation {
    private final EquationElementRepository elementRepository;
    private final AddNumberToRepositoryService addNumberToRepositoryService;
    
    public Equation(){
        elementRepository = new EquationElementRepository();
        addNumberToRepositoryService = new AddNumberToRepositoryService(elementRepository);
    }
    
    public void addNumber(Number number) {
        addNumberToRepositoryService.execucte(number);
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
        if (elementRepository.hasAtLeastThree()) {
            EquationElement[] lastThreeElements = elementRepository.fetchLastThree();
            if (percent.canBePlacedAfterThreeElements(lastThreeElements)){
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
