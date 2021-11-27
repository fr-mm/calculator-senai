package infrastructure;


import javax.swing.JLabel;


public class JFrameEquationHistoryContainer {
    private final JLabel equationCell;
    private final JLabel equalsCell;
    private final JLabel resultCell;
    
    public JFrameEquationHistoryContainer(JLabel equationCell, JLabel equalsCell, JLabel resultCell) {
        this.equationCell = equationCell;
        this.equalsCell = equalsCell;
        this.resultCell = resultCell;
    }
    
    public void setText(String equation, String result) {
        equationCell.setText(equation);
        equalsCell.setText("=");
        resultCell.setText(result);
    }
    
    public String getEquation() {
        return equationCell.getText();
    }
    
    public String getResult() {
        return resultCell.getText();
    }
}
