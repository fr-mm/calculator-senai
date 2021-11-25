package domain.services.microsservices;

import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.valueObjects.Number;


public class SolveSumMicrosservice implements SolverInterface {
    private final EquationElementRepository elementRepository;

    
    public SolveSumMicrosservice(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    @Override
    public void execute(int sumIndex) {
        int firstNumberIndex = sumIndex - 1;
        int lastNumberIndex = sumIndex + 1;
       
        Number firstNumber = (Number)elementRepository.getByIndex(firstNumberIndex);
        Number lastNumber = (Number)elementRepository.getByIndex(lastNumberIndex);
        
        Number newNumber = firstNumber.sum(lastNumber);   
        
        elementRepository.removeIndex(lastNumberIndex);
        elementRepository.removeIndex(sumIndex);
        elementRepository.removeIndex(firstNumberIndex);
        elementRepository.insert(firstNumberIndex, newNumber);
    }
}
