package domain.services.microsservices;


import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;


public class SolveFirstElementIfIsSubtractMicrosservice {
    private final EquationElementRepository elementRepository;
    
    public SolveFirstElementIfIsSubtractMicrosservice(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    public void execute() {
        EquationElement firstElement = elementRepository.getFirst();
        
        if (firstElement.isSubtract()) {
            elementRepository.removeFirst();
            
            Number firstNumber = (Number)elementRepository.getFirst();
            Number newFirstNumber = firstNumber.invertPolarity();
            
            elementRepository.removeFirst();
            elementRepository.insert(0, newFirstNumber);
        }  
    }
}
