package infrastructure;


public class JFrameEquationHistory {
    private final JFrameEquationHistoryContainer firstContainer;
    private final JFrameEquationHistoryContainer middleContainer;
    private final JFrameEquationHistoryContainer lastContainer;
    
    public JFrameEquationHistory(
            JFrameEquationHistoryContainer firstContainer,
            JFrameEquationHistoryContainer middleContainer,
            JFrameEquationHistoryContainer lastContainer
    ) {
        this.firstContainer = firstContainer;
        this.middleContainer = middleContainer;
        this.lastContainer = lastContainer;
    }
    
    public void updateDisplay(String equation, String result) {
        transferContent(middleContainer, lastContainer);
        transferContent(firstContainer, middleContainer);
        firstContainer.setText(equation, result);
    }
    
    private void transferContent(JFrameEquationHistoryContainer source, JFrameEquationHistoryContainer destiny) {
        destiny.setText(source.getEquation(), source.getResult());
    } 
}
