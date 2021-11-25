
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
        if (elementRepository.hasAtLeastThree()) {
            manageLastThreeElements(percent);
        }
    }
    
    private void manageLastThreeElements(Percent percent) {
        EquationElement[] lastThreeElements = elementRepository.fetchLastThree();
        if (percent.canBePlacedAfterThreeElements(lastThreeElements)){
            elementRepository.add(percent);
        }
    }
}