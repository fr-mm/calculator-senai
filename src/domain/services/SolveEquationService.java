package domain.services;

import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.services.microsservices.SolvePercentMicroservice;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;
import java.util.function.Function;


public class SolveEquationService {
    private final EquationElementRepository elementRepository;
    private final SolvePercentMicroservice solvePercentMicroservice; 

    
    public SolveEquationService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
        solvePercentMicroservice = new SolvePercentMicroservice(elementRepository);
    }
    
    public void execute() {
        if (!elementRepository.isEmpty()) {
            solvePopulatedRepository();
        }
    }
    
    private void solvePopulatedRepository() {
        solveFirstElementIfIsSubtract();
        solveAllPercents();
        
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
    
    private void solveAllPercents() {
        solveRecursively(Percent.class, solvePercentMicroservice);  
    } 
    
    private void solveRecursively(Class operationType, SolverInterface solverMicrosservice) {
        int operationIndex = elementRepository.getIndexOfInstance(operationType);
        
        if (operationIndex < 0) {
            return;
        }
        
        solverMicrosservice.execute(operationIndex);
        
        solveRecursively(operationType, solverMicrosservice);
    }
}
