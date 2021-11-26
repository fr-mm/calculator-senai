
package domain.services;

import domain.repositories.EquationElementRepository;
import domain.valueObjects.EquationElement;
import domain.valueObjects.Percent;


public class AddPercentToRepositoryService {
    private final EquationElementRepository elementRepository;

    
    public AddPercentToRepositoryService(EquationElementRepository elementRepository) {
        this.elementRepository = elementRepository;
    }
    
    public void execute(Percent percent) {
        if (elementRepository.hasAtLeastOne()) {
            manageLastElement(percent);
        }
    }
        
    private void manageLastElement(Percent percent) {
        EquationElement lastElement = elementRepository.getLast();
        System.out.println("oi");
        if (percent.canBePlacedAfter(lastElement)) {
            elementRepository.add(percent);
        }
    }
}