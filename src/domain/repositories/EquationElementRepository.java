package domain.repositories;

import domain.valueObjects.EquationElement;
import java.util.List;
import java.util.ArrayList;


public class EquationElementRepository {
    private final List<EquationElement> elements;
    
    public EquationElementRepository() {
        elements = new ArrayList<>();
    }
    
    public List<EquationElement> fetchAll() {
        return elements;
    }
    
    public void add(EquationElement element) {
        this.elements.add(element);
    }
    
    public EquationElement getLast() {
        return elements.get(size() - 1);
    }
    
    public Boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public void removeLast() {
        elements.remove(size() - 1);
    }
    
    public void clear() {
        elements.clear();
    }
    
    public int size() {
        return elements.size();
    }
    
    public EquationElement[] fetchLastThree() {
        int size = size();
        return new EquationElement[]{
            elements.get(size - 1),
            elements.get(size - 2),
            elements.get(size - 3)
        };
    }
    
    public boolean hasAtLeastThree() {
        return size() >= 3;
    }
    
}
