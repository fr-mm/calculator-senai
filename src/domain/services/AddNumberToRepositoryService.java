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
            double newDecimalPart = newNumber.getValue() / 10;
            double newValue = secondToLastElement.getValue() + newDecimalPart;
            return new Number(newValue);
            
        }
        return newNumber;
    }
    
    private Number getConcatenatedNumber(Number numberBefore, Number numberAfter){
        int integerPart = numberBefore.getIntegerPart();
        int decimalPart = numberBefore.getDecimalPart();
        
        if (numberBefore.getDecimalPartSize() > 8) {
            return numberBefore;
        }
        
        double finalValue;
        if (decimalPart == 0) {
            finalValue = integerPart * 10 + numberAfter.getValue();
        }
        else {  
            String valueBefore = String.valueOf(numberBefore.getValue());
            String valueAfter = String.valueOf(numberAfter.getIntegerPart());
            String concatenatedValues = valueBefore.concat(valueAfter);
            finalValue = Double.parseDouble(concatenatedValues);
        }
        
        return new Number(finalValue);
    }
}
