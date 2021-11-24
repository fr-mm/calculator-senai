package domain.entities;


import domain.interfaces.CalculatorInterface;
    

public class Calculator implements CalculatorInterface {
    private final Equation equation;
    
    public Calculator() {
        equation = new Equation();
    }
    @Override
    public String pressNumber(double number) {
        equation.addNumber(number);
        return(equation.toString());
    }

    @Override
    public String pressPlus() {
        return("");
    }

    @Override
    public String pressMinus() {
        return("");
    }

    @Override
    public String pressMultiply() {
        return("");
    }

    @Override
    public String pressDivide() {
        return("");
    }

    @Override
    public String pressDot() {
        return("");
    }

    @Override
    public String pressPercent() {
        return("");
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
