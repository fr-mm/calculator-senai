package application;


import infrastructure.JFrameGUI;
import domain.interfaces.GUIInterface;
import domain.interfaces.CalculatorInterface;
import domain.entities.Calculator;


public class CalculadoraFinal {
    public static void main(String[] args) {
        CalculatorInterface calculator = new Calculator();  
        GUIInterface GUI = new JFrameGUI(calculator);
        
        GUI.start();   
    }
    
}
