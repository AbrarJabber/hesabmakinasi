package com.example.abrarcalculator;

public class Calculator {

    public enum Operator {ADD, SUBTRACT, MULTIPLY, DIVIDE}

    private double firstOperand;
    private double secondOperand;
    private Operator operator;

    public Calculator() {
        reset();
    }

    public void setFirstOperand(double value) {
        this.firstOperand = value;
    }

    public void setSecondOperand(double value) {
        this.secondOperand = value;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public double calculate() {
        switch (operator) {
            case ADD:
                return firstOperand + secondOperand;
            case SUBTRACT:
                return firstOperand - secondOperand;
            case MULTIPLY:
                return firstOperand * secondOperand;
            case DIVIDE:
                if (secondOperand != 0) {
                    return firstOperand / secondOperand;
                } else {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
            default:
                throw new IllegalStateException("Invalid operator.");
        }
    }

    public void reset() {
        firstOperand = 0;
        secondOperand = 0;
        operator = null;
    }
}
