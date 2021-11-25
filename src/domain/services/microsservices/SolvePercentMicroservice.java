package domain.services.microsservices;

import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.valueObjects.Number;


public class SolvePercentMicroservice implements SolverInterface{
    private final EquationElementRepository elementRepository;

    
    public SolvePercentMicroservice(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    @Override
    public void execute(int percentIndex) {
        int firstNumberIndex = percentIndex - 3;
        int lastNumberIndex = percentIndex - 1;
        
        Number firstNumber = (Number)elementRepository.getByIndex(firstNumberIndex);
        Number lastNumber = (Number)elementRepository.getByIndex(lastNumberIndex);
        
        double newLastNumberValue = (firstNumber.getValue() * lastNumber.getValue()) / 100;
        Number newLastNumber = new Number(newLastNumberValue);
        
        elementRepository.removeIndex(lastNumberIndex);
        elementRepository.insert(lastNumberIndex, newLastNumber);
        elementRepository.removeIndex(percentIndex);
    }
}
