package domain.services;

import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.services.microsservices.SolveFirstElementIfIsSubtractMicrosservice;
import domain.services.microsservices.SolvePercentMicrosservice;
import domain.services.microsservices.SolveSumMicrosservice;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;
import domain.valueObjects.Sum;


public class SolveEquationService {
    private final EquationElementRepository elementRepository;
    private final SolvePercentMicrosservice solvePercentMicrosservice; 
    private final SolveFirstElementIfIsSubtractMicrosservice solveFirstElementIfIsSubtractMicrosservice;
    private final SolveSumMicrosservice solveSumMicrosservice;

    
    public SolveEquationService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
        solvePercentMicrosservice = new SolvePercentMicrosservice(elementRepository);
        solveFirstElementIfIsSubtractMicrosservice = new SolveFirstElementIfIsSubtractMicrosservice(elementRepository);
        solveSumMicrosservice = new SolveSumMicrosservice(elementRepository);
    }
    
    public void execute() {
        if (!elementRepository.isEmpty()) {
            solvePopulatedRepository();
        }
    }
    
    private void solvePopulatedRepository() {
        solveFirstElementIfIsSubtractMicrosservice.execute();
        solveAllPercents();
        solveAllSums();
        
    }
    
    private void solveAllPercents() {
        solveRecursively(Percent.class, solvePercentMicrosservice);  
    }
    
    private void solveAllSums() {
        solveRecursively(Sum.class, solveSumMicrosservice);
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
