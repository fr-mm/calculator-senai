package domain.valueObjects;


public abstract class Operation extends EquationElement {
    
      abstract public Number solve(Number firstNumber, Number secondNumber);
      
}
