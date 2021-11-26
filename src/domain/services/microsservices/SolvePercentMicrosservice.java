package domain.services.microsservices;


import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;


public class SolvePercentMicrosservice implements SolverInterface{
    private final EquationElementRepository elementRepository;
    
    public SolvePercentMicrosservice(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    @Override
    public void execute(int percentIndex) {
        if (percentIndex == 1) {
            manangePercentAsSecondElement();
        }
        else {
            manageTriad(percentIndex);
        }
    }
 
    private void manangePercentAsSecondElement() {
        Number numberBefore = (Number)elementRepository.getFirst();
        Number newNumber = numberBefore.divideByOneHundred();
        
        elementRepository.removeFirst();
        elementRepository.removeFirst();
        
        elementRepository.insert(0, newNumber);
    }
    
    private void manageTriad(int percentIndex) {
        int firstNumberIndex = percentIndex - 3;
        int operationIndex = percentIndex - 2;
        int lastNumberIndex = percentIndex - 1;
        
        Number firstNumber = (Number)elementRepository.getByIndex(firstNumberIndex);
        Number lastNumber = (Number)elementRepository.getByIndex(lastNumberIndex);
        Operation operation = (Operation)elementRepository.getByIndex(operationIndex);
               
        Number finalNumber = solve(firstNumber, lastNumber, operation);
        
        elementRepository.removeIndex(percentIndex);
        elementRepository.removeIndex(lastNumberIndex);
        elementRepository.removeIndex(operationIndex);
        elementRepository.removeIndex(firstNumberIndex);
        
        elementRepository.insert(firstNumberIndex, finalNumber);
    }

    private Number solve(Number firstNumber, Number lastNumber, Operation operation) {
        Number newLastNumber;
        if (operation.isSum() || operation.isSubtract()) {
            newLastNumber = firstNumber.multiply(lastNumber).divideByOneHundred();
        }
        else {
            newLastNumber = lastNumber.divideByOneHundred();
        }
        return operation.solve(firstNumber, newLastNumber);
    }
}
