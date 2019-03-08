package helpers.api.qonto.mock;

import helpers.api.qonto.model.Transaction;
import helpers.api.qonto.model.TransactionResponse;
import helpers.api.qonto.service.TransactionService;
import helpers.extension.PlayfulJavaExtensions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author jtremeaux
 */
public class MockTransactionService {
    public static List<Transaction> transactionList = new ArrayList<>();

    /**
     * Create a mock of TransactionService.
     *
     * @return The mock
     */
    public static TransactionService create() {
        TransactionService transactionService = mock(TransactionService.class);

        when(transactionService.getTransaction(any(String.class))).thenAnswer(i -> {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.transactions = new ArrayList<>();
            transactionResponse.transactions.addAll(transactionList);
            return transactionResponse;
        });

        return transactionService;
    }

    public static Transaction getTransaction(double amount, String side, String label, String settledDate, String emmitedDate) {
        Transaction transaction = new Transaction();
        transaction.transaction_id = "company-1234-1-transaction-" + (transactionList.size() + 1);
        transaction.amount = BigDecimal.valueOf(amount);
        transaction.amount_cents = Double.valueOf(amount * 100).longValue();
        transaction.local_amount = transaction.amount;
        transaction.local_amount_cents = transaction.amount_cents;
        transaction.side = side;
        transaction.operation_type = "card";
        transaction.currency = "EUR";
        transaction.local_currency = "EUR";
        transaction.label = label;
        transaction.settled_at = PlayfulJavaExtensions.parseDateTimeIso(settledDate);
        transaction.emitted_at = PlayfulJavaExtensions.parseDateTimeIso(emmitedDate);
        transaction.status = "completed";

        return transaction;
    }
}
