package domain.services;


import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.services.microsservices.SolveFirstElementIfIsSubtractMicrosservice;
import domain.services.microsservices.SolveOperationMicrosservice;
import domain.services.microsservices.SolvePercentMicrosservice;
import domain.services.microsservices.TrimLooseOperationAtEnd;
import domain.valueObjects.Divide;
import domain.valueObjects.Multiply;
import domain.valueObjects.Percent;
import domain.valueObjects.Subtract;
import domain.valueObjects.Sum;


public class SolveEquationService {
    private final EquationElementRepository elementRepository;
    private final SolvePercentMicrosservice solvePercentMicrosservice; 
    private final SolveFirstElementIfIsSubtractMicrosservice solveFirstElementIfIsSubtractMicrosservice;
    private final SolveOperationMicrosservice solveOperationMicrosservice;
    private final TrimLooseOperationAtEnd trimLooseOperationAtEnd;

    public SolveEquationService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
        solvePercentMicrosservice = new SolvePercentMicrosservice(elementRepository);
        solveFirstElementIfIsSubtractMicrosservice = new SolveFirstElementIfIsSubtractMicrosservice(elementRepository);
        solveOperationMicrosservice = new SolveOperationMicrosservice(elementRepository);
        trimLooseOperationAtEnd = new TrimLooseOperationAtEnd(elementRepository);
    }
    
    public void execute() {
        if (!elementRepository.isEmpty()) {
            solvePopulatedRepository();
        }
    }
    
    private void solvePopulatedRepository() {
        trimLooseOperationAtEnd.execute();
        solveFirstElementIfIsSubtractMicrosservice.execute();
        solveAllPercents();
        solveMultipliesAndDivides();
        solveSumsAndSubtractions();
    }
    
    private void solveAllPercents() {
        solveAllOfType(Percent.class, solvePercentMicrosservice);  
    }
    
    private void solveSumsAndSubtractions() {
        solveAllOfType(Sum.class, Subtract.class, solveOperationMicrosservice);
    }
    
    private void solveMultipliesAndDivides() {
        solveAllOfType(Multiply.class, Divide.class, solveOperationMicrosservice);
    }
    
    private void solveAllOfType(Class operationType, SolverInterface solverMicrosservice) {
        int operationIndex = elementRepository.getIndexOfInstance(operationType);
        
        if (operationIndex < 0) {
            return;
        }
        
        solverMicrosservice.execute(operationIndex);
        
        solveAllOfType(operationType, solverMicrosservice);
    }
    
    private void solveAllOfType(Class oneOperationType, Class otherOperationType, SolverInterface solverMicrosservice) {
        int oneOperationIndex = elementRepository.getIndexOfInstance(oneOperationType);
        int otherOperationIndex = elementRepository.getIndexOfInstance(otherOperationType);
        int firstOperationIndex;
        
        if (oneOperationIndex < 0 && otherOperationIndex < 0) {
            return;
        }
        else if (oneOperationIndex < 0) {
            firstOperationIndex = otherOperationIndex;
        }
        else if (otherOperationIndex < 0) {
            firstOperationIndex = oneOperationIndex;
        }
        else {
            firstOperationIndex = Math.min(oneOperationIndex, otherOperationIndex);
        }
        
        solverMicrosservice.execute(firstOperationIndex);
        
        solveAllOfType(oneOperationType, otherOperationType, solverMicrosservice);
    }
}
