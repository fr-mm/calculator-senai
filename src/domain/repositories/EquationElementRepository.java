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
        int size = elements.size();
        return elements.get(size - 1);
    }
    
    public Boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public void removeLast() {
        int size = elements.size();
        elements.remove(size - 1);
    }
    
    public void clear() {
        elements.clear();
    }
    
}
