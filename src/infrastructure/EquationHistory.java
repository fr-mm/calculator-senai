package infrastructure;


import java.util.ArrayList;
import java.util.List;


public class EquationHistory {
    private final int MAX_EQUATIONS = 3;
    private final int EQUATION_LENGTH = 30;
    private final int RESULT_LENGTH = 10;
    private final List<String> equations;
    
    public EquationHistory() {
        equations = new ArrayList<>();
        for (int i = 0; i < MAX_EQUATIONS; i++) {
            equations.add("");
        }
    }
    
    public void add(String equation, String result) {
        removeLastWhenFull();
        String formattedLine = formatLine(equation, result);
        equations.add(0, formattedLine);
    }

    @Override
    public String toString() {
        String result = "<html>";
        for (int index = equations.size() - 1; index >= 0; index--) {
            String equation = equations.get(index);
            result += "<p style='margin-top: 10'>               " + equation + "</p>";
        }
        result += "</html>";
        return result;
    }
    
    private String formatLine(String equation, String result) {
        String equalsPad = getPadding(EQUATION_LENGTH - equation.length());
        String resultPad = getPadding(RESULT_LENGTH - 1);
        
        String formattedLine = equation + equalsPad + "=" + resultPad + result;

        System.out.println(formattedLine);
        
        return formattedLine;
    }
    
    private String getPadding(int size) {
        return "       ";
    }
    
    private void removeLastWhenFull() {
        int equationsCount = equations.size();
        
        if (equationsCount >= MAX_EQUATIONS) {
            equations.remove(equationsCount - 1);
        }
    }
}
