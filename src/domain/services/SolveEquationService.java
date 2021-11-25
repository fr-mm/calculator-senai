package domain.services;

import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.services.microsservices.SolveDivideMicrosservice;
import domain.services.microsservices.SolveFirstElementIfIsSubtractMicrosservice;
import domain.services.microsservices.SolveMultiplyMicrosservice;
import domain.services.microsservices.SolvePercentMicrosservice;
import domain.services.microsservices.SolveSubtractMicrosservice;
import domain.services.microsservices.SolveSumMicrosservice;
import domain.valueObjects.Divide;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Multiply;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;
import domain.valueObjects.Subtract;
import domain.valueObjects.Sum;


public class SolveEquationService {
    private final EquationElementRepository elementRepository;
    private final SolvePercentMicrosservice solvePercentMicrosservice; 
    private final SolveFirstElementIfIsSubtractMicrosservice solveFirstElementIfIsSubtractMicrosservice;
    private final SolveSumMicrosservice solveSumMicrosservice;
    private final SolveSubtractMicrosservice solveSubtractMicrosservice;
    private final SolveMultiplyMicrosservice solveMultiplyMicrosservice;
    private final SolveDivideMicrosservice solveDivideMicrosservice;

    
    public SolveEquationService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
        solvePercentMicrosservice = new SolvePercentMicrosservice(elementRepository);
        solveFirstElementIfIsSubtractMicrosservice = new SolveFirstElementIfIsSubtractMicrosservice(elementRepository);
        solveSumMicrosservice = new SolveSumMicrosservice(elementRepository);
        solveSubtractMicrosservice = new SolveSubtractMicrosservice(elementRepository);
        solveMultiplyMicrosservice = new SolveMultiplyMicrosservice(elementRepository);
        solveDivideMicrosservice = new SolveDivideMicrosservice(elementRepository);
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
        solveAllSubtractions();
        solveAllMultiplies();
        solveAllDivisions();
    }
    
    private void solveAllPercents() {
        solveRecursively(Percent.class, solvePercentMicrosservice);  
    }
    
    private void solveAllSums() {
        solveRecursively(Sum.class, solveSumMicrosservice);
    }
    
    private void solveAllSubtractions() {
        solveRecursively(Subtract.class, solveSubtractMicrosservice);
    }
    
    private void solveAllMultiplies() {
        solveRecursively(Multiply.class, solveMultiplyMicrosservice);
    }
    
    private void solveAllDivisions() {
        solveRecursively(Divide.class, solveDivideMicrosservice);
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
