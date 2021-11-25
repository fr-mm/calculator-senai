package domain.services;

import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.services.microsservices.SolveFirstElementIfIsSubtractMicrosservice;
import domain.services.microsservices.SolvePercentMicroservice;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;
import java.util.function.Function;


public class SolveEquationService {
    private final EquationElementRepository elementRepository;
    private final SolvePercentMicroservice solvePercentMicroservice; 
    private final SolveFirstElementIfIsSubtractMicrosservice solveFirstElementIfIsSubtractMicrosservice;

    
    public SolveEquationService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
        solvePercentMicroservice = new SolvePercentMicroservice(elementRepository);
        solveFirstElementIfIsSubtractMicrosservice = new SolveFirstElementIfIsSubtractMicrosservice(elementRepository);
    }
    
    public void execute() {
        if (!elementRepository.isEmpty()) {
            solvePopulatedRepository();
        }
    }
    
    private void solvePopulatedRepository() {
        solveFirstElementIfIsSubtractMicrosservice.execute();
        solveAllPercents();
        
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
