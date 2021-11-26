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
    
    public EquationElement getByIndex(int index) {
        return elements.get(index);
    }
    
    public EquationElement getLast() {
        return elements.get(size() - 1);
    }
    
    public Boolean isEmpty() {
        return elements.isEmpty();
    }
    
    public EquationElement getFirst() {
        return elements.get(0);
    }
    
    public void removeLast() {
        elements.remove(size() - 1);
    }
    
    public void removeFirst() {
        elements.remove(0);
    }
    
    public void removeIndex(int index) {
        elements.remove(index);
    }
    
    public void insert(int index, EquationElement element) {
        elements.add(index, element);
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
       
    public int getIndexOfInstance(Class class_) {
        for (EquationElement element : elements) {
            if (class_.isInstance(element)) {
                return elements.indexOf(element);
            }
        }
        return -1;
    }
    
    public boolean hasAtLeastThree() {
        return size() >= 3;
    }
    
    public boolean hasAtLeastTwo() {
        return size() >= 2;
    }
    
    public boolean hasAtLeastOne() {
        return size() >= 1;
    }
    
    public boolean thirdToLastIsNumber() {
        EquationElement thirdToLast = getByIndex(size() - 3);
        return thirdToLast.isNumber();
    }
}
