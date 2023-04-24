package com.example.calchttpserver.data;

public class CalcResultData {
    private final int m_operandA;
    private final int m_operandB;
    private final String m_operation;
    private final float m_result;

    public CalcResultData(
            final int operandA,
            final int operandB,
            final String operation,
            final float result)
    {
        m_operandA = operandA;
        m_operandB = operandB;
        m_operation = operation;
        m_result = result;
    }

    public int getOperandA() {
        return m_operandA;
    }

    public int getOperandB() {
        return m_operandB;
    }

    public String getOperation() {
        return m_operation;
    }

    public float getResult() {
        return m_result;
    }
}