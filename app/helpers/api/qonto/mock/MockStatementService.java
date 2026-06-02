package helpers.api.qonto.mock;

import helpers.api.qonto.model.File;
import helpers.api.qonto.model.Statement;
import helpers.api.qonto.model.StatementResponse;
import helpers.api.qonto.service.StatementService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author jtremeaux
 */
public class MockStatementService {
    public static List<Statement> statementList = new ArrayList<>();

    /**
     * Create a mock of StatementService.
     *
     * @return The mock
     */
    public static StatementService create() {
        StatementService StatementService = mock(StatementService.class);

        when(StatementService.getStatement(any(String.class))).thenAnswer(i -> {
            StatementResponse StatementResponse = new StatementResponse();
            StatementResponse.statements = new ArrayList<>();
            StatementResponse.statements.addAll(statementList);
            return StatementResponse;
        });

        return StatementService;
    }

    public static Statement getStatement(String date) {
        Statement statement = new Statement();
        statement.id = "08544790-6305-4a81-8487-e035290bcee8";
        statement.bank_account_id = "9f4ff770-344b-42c1-a592-6b2adf82a6d3";
        statement.period = date;
        statement.file = new File();

        return statement;
    }
}
