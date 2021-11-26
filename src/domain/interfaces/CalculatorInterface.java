package domain.interfaces;


public interface CalculatorInterface {
    abstract public String pressNumber(String number);
    abstract public String pressPlus();
    abstract public String pressMinus();
    abstract public String pressMultiply();
    abstract public String pressDivide();
    abstract public String pressDot();
    abstract public String pressPercent();
    abstract public String pressClear();
    abstract public String pressEquals();
    abstract public String equationToString();
}
