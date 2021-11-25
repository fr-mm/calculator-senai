package domain.services.microsservices;

import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.valueObjects.Number;


public class SolveMultiplyMicrosservice implements SolverInterface {
    private final EquationElementRepository elementRepository;

    
    public SolveMultiplyMicrosservice(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    @Override
    public void execute(int sumIndex) {
        int firstNumberIndex = sumIndex - 1;
        int lastNumberIndex = sumIndex + 1;
       
        Number firstNumber = (Number)elementRepository.getByIndex(firstNumberIndex);
        Number lastNumber = (Number)elementRepository.getByIndex(lastNumberIndex);
        
        double newNumberValue = firstNumber.getValue() * lastNumber.getValue();
        Number newNumber = new Number(newNumberValue);
        
        
        elementRepository.removeIndex(lastNumberIndex);
        elementRepository.removeIndex(sumIndex);
        elementRepository.removeIndex(firstNumberIndex);
        elementRepository.insert(firstNumberIndex, newNumber);
    }
}
