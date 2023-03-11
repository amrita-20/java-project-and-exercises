package com.exercise9;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CalculatorController {
    @FXML
    private Label textLabel;
    double operand1 = Double.MIN_VALUE;
    double operand2 = Double.MIN_VALUE;
    String previousOperator = "";
    static double inputNo = Double.MIN_VALUE;

    //Created method to handle clear functionality
    @FXML
    protected void onClear() {
        inputNo = Double.MIN_VALUE;
        operand1 = Double.MIN_VALUE;
        operand2 = Double.MIN_VALUE;
        previousOperator = "";
        textLabel.setText("0.0");
    }

    //Created method to handle click event of digits
    @FXML
    protected void onDigitsClicked(MouseEvent event){
        Button button = (Button) event.getSource();
        double buttonVal = Double.parseDouble(button.getText());
        inputNo = inputNo == Double.MIN_VALUE ? buttonVal : inputNo * 10 + buttonVal;
        textLabel.setText(String.valueOf(inputNo));
    }

    //Created method to handle click events of symbols
    @FXML
    protected void onSymbolClicked(MouseEvent event){
        String symbolVal = ((Button) event.getSource()).getText();
        //String symbolVal = button.getText();
        if(symbolVal.equalsIgnoreCase("=")){
            if(previousOperator.isEmpty()){
                return;
            }
            if(inputNo != Double.MIN_VALUE) {
                operand2 = inputNo;
                operand1 = getOperatorResult(previousOperator, operand1, operand2);
            }
            textLabel.setText(String.valueOf(operand1));

            inputNo = Double.MIN_VALUE;
        }else{
            if(operand1 == Double.MIN_VALUE)
                operand1 = Double.parseDouble(textLabel.getText());
            else
                operand2 = inputNo;

            if(!previousOperator.isEmpty() && operand2 != Double.MIN_VALUE) {
                operand1 = getOperatorResult(previousOperator, operand1, operand2);
            }
            inputNo = Double.MIN_VALUE;
            previousOperator = symbolVal;
            textLabel.setText(String.valueOf(operand1));
        }
    }

    //Created method to perform the respective operations
    protected double getOperatorResult(String symbolVal, double operand1, double operand2){
        switch (symbolVal){
            case "+":
                return (operand1 + operand2);
            case  "-":
                return (operand1 - operand2);
            case "/":
                return (operand1/operand2);
            case "X":
                return (operand1 * operand2);
            case "%":
                return (operand1 / 100);
            default: return 0;
        }
    }
}
