package domain.entities;


import domain.repositories.EquationElementRepository;
import domain.services.AddNumberToRepositoryService;
import domain.services.AddOperationToRepositoryService;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;


public class Equation {
    private final EquationElementRepository elementRepository;
    private final AddNumberToRepositoryService addNumberToRepositoryService;
    private final AddOperationToRepositoryService addOperationToRepositoryService;
    
    public Equation(){
        elementRepository = new EquationElementRepository();
        addNumberToRepositoryService = new AddNumberToRepositoryService(elementRepository);
        addOperationToRepositoryService = new AddOperationToRepositoryService(elementRepository);
    }
    
    public void addNumber(Number number) {
        addNumberToRepositoryService.execucte(number);
    }
    
    public void addOperation(Operation operation) {
        addOperationToRepositoryService.execute(operation);
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
