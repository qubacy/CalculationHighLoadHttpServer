package com.example.calchttpserver.database;

import com.example.calchttpserver.data.CalcResultData;
import org.springframework.lang.NonNull;

import java.sql.*;

public class DatabaseController {
    public static final String C_DATABASE_NAME = "database.db";

    public static final String C_CALC_HISTORY_TABLE_NAME = "calc_history";

    public static final String C_CALC_TABLE_ID_NAME_PROP = "id";
    public static final String C_CALC_TABLE_OPERAND_A_NAME_PROP = "operation_a";
    public static final String C_CALC_TABLE_OPERAND_B_NAME_PROP = "operation_b";
    public static final String C_CALC_TABLE_OPERATION_NAME_PROP = "operation";
    public static final String C_CALC_TABLE_RESULT_NAME_PROP = "result";

    private static DatabaseController s_instance = null;

    private final String m_connectionName;

    private DatabaseController(
            final String connectionName)
    {
        m_connectionName = connectionName;
    }

    private static void init()
            throws SQLException
    {
        s_instance = new DatabaseController("jdbc:sqlite:" + C_DATABASE_NAME);

        Connection connection = DriverManager.getConnection(s_instance.m_connectionName);
        Statement statement = connection.createStatement();
        String statementString = String
                .format(
                        "CREATE TABLE if not exists '%s' (" +
                                "'%s' INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "'%s' INT, '%s' INT, '%s' TEXT, '%s' REAL);",
                        C_CALC_HISTORY_TABLE_NAME,
                        C_CALC_TABLE_ID_NAME_PROP,
                        C_CALC_TABLE_OPERAND_A_NAME_PROP,
                        C_CALC_TABLE_OPERAND_B_NAME_PROP,
                        C_CALC_TABLE_OPERATION_NAME_PROP,
                        C_CALC_TABLE_RESULT_NAME_PROP);

        statement.execute(statementString);
    }

    public synchronized static DatabaseController getInstance()
            throws SQLException
    {
        if (s_instance == null) init();

        return s_instance;
    }

    public synchronized void addLog(
            @NonNull final CalcResultData resultData)
            throws SQLException
    {
        Connection connection = DriverManager.getConnection(s_instance.m_connectionName);

        Statement statement = connection.createStatement();
        String statementString = String
                .format(
                        "INSERT INTO '%s'('%s', '%s', '%s', '%s')" +
                        "VALUES (%d, %d, '%s', '%f');",
                        C_CALC_HISTORY_TABLE_NAME,
                        C_CALC_TABLE_OPERAND_A_NAME_PROP,
                        C_CALC_TABLE_OPERAND_B_NAME_PROP,
                        C_CALC_TABLE_OPERATION_NAME_PROP,
                        C_CALC_TABLE_RESULT_NAME_PROP,
                        resultData.getOperandA(),
                        resultData.getOperandB(),
                        resultData.getOperation(),
                        resultData.getResult());

        statement.execute(statementString);
    }
}
