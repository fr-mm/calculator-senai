package domain.entities;


import domain.interfaces.CalculatorInterface;
import domain.valueObjects.Divide;
import domain.valueObjects.Dot;
import domain.valueObjects.Multiply;
import domain.valueObjects.Number;
import domain.valueObjects.Operation;
import domain.valueObjects.Percent;
import domain.valueObjects.Sum;
import domain.valueObjects.Subtract;
    

public class Calculator implements CalculatorInterface {
    private final Equation equation;
    
    public Calculator() {
        equation = new Equation();
    }
    @Override
    public String pressNumber(double number) {
        Number parsedNumber = new Number(number);
        equation.addElement(parsedNumber);
        return(equation.toString());
    }

    @Override
    public String pressPlus() {
        Operation operation = new Sum();
        equation.addElement(operation);
        return(equation.toString());
    }

    @Override
    public String pressMinus() {
        Operation operation = new Subtract();
        equation.addElement(operation);
        return(equation.toString());
    }

    @Override
    public String pressMultiply() {
        Operation operation = new Multiply();
        equation.addElement(operation);
        return(equation.toString());
    }

    @Override
    public String pressDivide() {
        Operation operation = new Divide();
        equation.addElement(operation);
        return(equation.toString());
    }

    @Override
    public String pressDot() {
        Dot dot = new Dot();
        equation.addElement(dot);
        return(equation.toString());
    }

    @Override
    public String pressPercent() {
        Percent percent = new Percent();
        equation.addElement(percent);
        return(equation.toString());
    }

    @Override
    public String pressClear() {
        return("");
    }

    @Override
    public String pressEquals() {
        return("");
    }

    
}
