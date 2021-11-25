package domain.services;

import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Operation;


public class AddOperationToRepositoryService {
    private final EquationElementRepository elementRepository;

    
    public AddOperationToRepositoryService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    public void execute(Operation operation) {
        if (elementRepository.isEmpty()) {
            manageEmptyRepository(operation);
        }
        else if (elementRepository.size() == 1) {
            manageRepositoryWithOneElement(operation);
        }
        else if (elementRepository.size() > 1) {
            manageRepositoryWithManyElements(operation);
        }
    } 
    
    private void manageEmptyRepository(Operation operation) {
        if (operation.isSubtract()) {
            elementRepository.add(operation);
        }
    }
    
    private void manageRepositoryWithOneElement(Operation operation) {
        EquationElement lastElement = elementRepository.getLast();
        
        if (lastElement.isNumber()) {
            elementRepository.add(operation);
        }
    }
    
    private void manageRepositoryWithManyElements(Operation operation) {
        EquationElement lastElement = elementRepository.getLast();
        
        if (lastElement.isNumber() || lastElement.isPercent()) {
            elementRepository.add(operation);
        }
        else if (lastElement.isOperation() || lastElement.isDot()) {
            elementRepository.removeLast();
            elementRepository.add(operation);
        }
    }         
}
