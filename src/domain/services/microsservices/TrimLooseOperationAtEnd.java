package domain.services.microsservices;


import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;


public class TrimLooseOperationAtEnd {
    private final EquationElementRepository elementRepository;

    
    public TrimLooseOperationAtEnd(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    public void execute() {
        EquationElement lastElement = elementRepository.getLast();
        
        if (lastElement.isOperation()) {
            elementRepository.removeLast();
        }
    }
}
