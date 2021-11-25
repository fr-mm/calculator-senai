package domain.services;


import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Number;


public class AddNumberToRepositoryService {
    private final EquationElementRepository elementRepository;
    
    public AddNumberToRepositoryService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    public void execucte(Number number) {
        if (elementRepository.isEmpty()) {
            elementRepository.add(number);
        }
        else {
            interactWithLastElement(number);
        }
    }
    
    private void interactWithLastElement(Number number) {
        EquationElement lastElement = elementRepository.getLast();
        if (number.canBePlacedAfter(lastElement)) {
            number = getResultOfInteractionWithLastElement(number, lastElement);
            elementRepository.add(number);
        }
    }
    
    private Number getResultOfInteractionWithLastElement(Number newNumber, EquationElement lastElement) {
        if (lastElement.isNumber()) {
            elementRepository.removeLast();
            return getConcatenatedNumber((Number)lastElement, newNumber);
        }
        else if (lastElement.isDot()) {
            elementRepository.removeLast();
            Number secondToLastElement = (Number)elementRepository.getLast();
            elementRepository.removeLast();
            Number newDecimalPart = newNumber.divideByTen();
            return secondToLastElement.sum(newDecimalPart);
        }
        return newNumber;
    }
    
    private Number getConcatenatedNumber(Number numberBefore, Number numberAfter){
        if (numberBefore.maxDecimalPlacesReached()) {
            return numberBefore;
        }
        
        if (!numberBefore.isDotted()) {
            return numberBefore.multiplyByTen().sum(numberAfter);
        }
        
        String newValue = numberBefore.toString() + numberAfter.toString();       
        return new Number(newValue);
    }
}
