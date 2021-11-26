package domain.services.microsservices;


import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;


public class SolveOperationMicrosservice implements SolverInterface {
    private final EquationElementRepository elementRepository;

    public SolveOperationMicrosservice(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    @Override
    public void execute(int operationIndex) {
        int firstNumberIndex = operationIndex - 1;
        int lastNumberIndex = operationIndex + 1;
       
        Number firstNumber = (Number)elementRepository.getByIndex(firstNumberIndex);
        Number lastNumber = (Number)elementRepository.getByIndex(lastNumberIndex);
        Operation operation = (Operation)elementRepository.getByIndex(operationIndex);
        
        Number newNumber = operation.solve(firstNumber, lastNumber);   
        
        elementRepository.removeIndex(lastNumberIndex);
        elementRepository.removeIndex(operationIndex);
        elementRepository.removeIndex(firstNumberIndex);
        elementRepository.insert(firstNumberIndex, newNumber);
    }
}
