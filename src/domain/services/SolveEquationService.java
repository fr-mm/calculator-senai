package domain.services;

import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;


public class SolveEquationService {
    private final EquationElementRepository elementRepository;

    
    public SolveEquationService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    public void execute() {
        if (!elementRepository.isEmpty()) {
            solvePopulatedRepository();
        }
    }
    
    private void solvePopulatedRepository() {
        solveFirstElementIfIsSubtract();
        solvePercents();
    }
    
    private void solveFirstElementIfIsSubtract() {
        EquationElement firstElement = elementRepository.getFirst();
        if (firstElement.isSubtract()) {
            elementRepository.removeFirst();
            
            Number firstNumber = (Number)elementRepository.getFirst();
            double newFirstValue = firstNumber.getValue() * -1;
            Number newFirstNumber = new Number(newFirstValue);
            
            elementRepository.removeFirst();
            elementRepository.insert(0, newFirstNumber);
        }
    }
    
    private void solvePercents() {
        int percentIndex = elementRepository.getFirstPercentIndex();
        
        if (percentIndex < 0) {
            return;
        }
        
        int firstNumberIndex = percentIndex - 3;
        int lastNumberIndex = percentIndex - 1;
        
        Number firstNumber = (Number)elementRepository.getByIndex(firstNumberIndex);
        Number lastNumber = (Number)elementRepository.getByIndex(lastNumberIndex);
        
        double newLastNumberValue = (firstNumber.getValue() * lastNumber.getValue()) / 100;
        Number newLastNumber = new Number(newLastNumberValue);
        
        elementRepository.removeIndex(lastNumberIndex);
        elementRepository.insert(lastNumberIndex, newLastNumber);
        elementRepository.removeIndex(percentIndex);
        
        solvePercents();    
    }
}
