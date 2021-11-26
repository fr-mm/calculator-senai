package domain.services;


import domain.repositories.EquationElementRepository;
import domain.valueObjects.Dot;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;


public class AddDotToRepositoryService {
    private final EquationElementRepository elementRepository;
    
    public AddDotToRepositoryService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    public void execute(Dot dot) {
        if (elementRepository.isEmpty()) {
            addWithZeroBefore(dot);
        }
        else {
            managePopulatedRepository(dot);
        }
    }
    
    private void managePopulatedRepository(Dot dot) {
        EquationElement lastElement = elementRepository.getLast();
        
        if (dot.canBePlacedAfter(lastElement)) {
            manageDotAfterLastElement(dot, lastElement);
        }
    }
    
    private void manageDotAfterLastElement(Dot dot, EquationElement lastElement) {
        if (lastElement.isNumber()) {
            elementRepository.add(dot);
        }
        else {
            addWithZeroBefore(dot);
        }
    } 
    
    private void addWithZeroBefore(Dot dot) {
        Number zero = new Number("0");
        elementRepository.add(zero);
        elementRepository.add(dot);
    }
}
