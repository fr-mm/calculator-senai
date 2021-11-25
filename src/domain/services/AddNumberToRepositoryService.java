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
    
    private Number getResultOfInteractionWithLastElement(Number number, EquationElement lastElement) {
        if (lastElement instanceof Number) {
            number = ((Number) lastElement).concatenate(number);
            elementRepository.removeLast();
        }
        return number;
    }
}
