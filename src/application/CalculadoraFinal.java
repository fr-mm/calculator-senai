package application;


import infrastructure.JFrameGUI;
import domain.interfaces.GUIInterface;
import domain.interfaces.CalculatorInterface;
import domain.entities.Calculator;


public class CalculadoraFinal {
    private static final GUIInterface GUI = new JFrameGUI();
    private static final CalculatorInterface CALCULATOR = new Calculator();
        
    public static void main(String[] args) {
        GUI.setCalculator(CALCULATOR);
        GUI.start();   
    }
    
}
