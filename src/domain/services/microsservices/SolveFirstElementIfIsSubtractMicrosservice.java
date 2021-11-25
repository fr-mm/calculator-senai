package domain.services.microsservices;


import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;


public class SolveFirstElementIfIsSubtractMicrosservice {
    private final EquationElementRepository elementRepository;

    
    public SolveFirstElementIfIsSubtractMicrosservice(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    public void execute() {
        EquationElement firstElement = elementRepository.getFirst();
        
        if (firstElement.isSubtract()) {
            elementRepository.removeFirst();
            
            domain.valueObjects.Number firstNumber = (domain.valueObjects.Number)elementRepository.getFirst();
            double newFirstValue = firstNumber.getValue() * -1;
            domain.valueObjects.Number newFirstNumber = new domain.valueObjects.Number(newFirstValue);
            
            elementRepository.removeFirst();
            elementRepository.insert(0, newFirstNumber);
        }  
    }
}
