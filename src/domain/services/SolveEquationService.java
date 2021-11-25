package domain.services;


import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.services.microsservices.SolveFirstElementIfIsSubtractMicrosservice;
import domain.services.microsservices.SolveOperationMicrosservice;
import domain.services.microsservices.SolvePercentMicrosservice;
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

    
    public SolveEquationService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
        solvePercentMicrosservice = new SolvePercentMicrosservice(elementRepository);
        solveFirstElementIfIsSubtractMicrosservice = new SolveFirstElementIfIsSubtractMicrosservice(elementRepository);
        solveOperationMicrosservice = new SolveOperationMicrosservice(elementRepository);
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
        solveRecursively(Sum.class, solveOperationMicrosservice);
    }
    
    private void solveAllSubtractions() {
        solveRecursively(Subtract.class, solveOperationMicrosservice);
    }
    
    private void solveAllMultiplies() {
        solveRecursively(Multiply.class, solveOperationMicrosservice);
    }
    
    private void solveAllDivisions() {
        solveRecursively(Divide.class, solveOperationMicrosservice);
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
