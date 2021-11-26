package domain.services.microsservices;

import domain.interfaces.SolverInterface;
import domain.repositories.EquationElementRepository;
import domain.valueObjects.Number;


public class SolvePercentMicrosservice implements SolverInterface{
    private final EquationElementRepository elementRepository;

    
    public SolvePercentMicrosservice(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    @Override
    public void execute(int percentIndex) {
        if (percentIndex == 1) {
            manangePercentAsSecondElement();
        }
        else {
            manageTriad(percentIndex);
        }

//        if (elementRepository.hasAtLeastOne()&& elementRepository.thirdToLastIsNumber()) {
//            manageTriad(percentIndex);
//        }
//        
//        else if (elementRepository.hasAtLeastOne() && elementRepository.getLast().isNumber()) {
//            manageTriad(percentIndex);
//        }
//        
//        if (percentIndex == 1) {
//            manangePercentAsSecondElement();
//        }
//        
//        else if (elementRepository.hasAtLeastThree()) {
//            manageTriad(percentIndex);
//        }
    }
    
 
    private void manangePercentAsSecondElement() {
        Number numberBefore = (Number)elementRepository.getFirst();
        Number newNumber = numberBefore.divideByOneHundred();
        
        elementRepository.removeFirst();
        elementRepository.removeFirst();
        
        elementRepository.insert(0, newNumber);
    }
    
    private void manageTriad(int percentIndex) {
        int firstNumberIndex = percentIndex - 3;
        int lastNumberIndex = percentIndex - 1;
        
        Number firstNumber = (Number)elementRepository.getByIndex(firstNumberIndex);
        Number lastNumber = (Number)elementRepository.getByIndex(lastNumberIndex);
        
        Number newLastNumber = firstNumber.multiply(lastNumber).divideByOneHundred();
        
        elementRepository.removeIndex(lastNumberIndex);
        elementRepository.insert(lastNumberIndex, newLastNumber);
        elementRepository.removeIndex(percentIndex);
    }
}
