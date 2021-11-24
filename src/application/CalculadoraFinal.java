package application;


import domain.interfaces.GUIInterface;
import infrastructure.JFrameGUI;


public class CalculadoraFinal {
    private static final GUIInterface GUI = new JFrameGUI();
    

    
    public static void main(String[] args) {
        GUI.start();   
    }
    
}
