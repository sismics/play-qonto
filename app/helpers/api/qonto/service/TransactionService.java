package helpers.api.qonto.service;

import com.google.gson.Gson;
import helpers.api.qonto.QontoClient;
import helpers.api.qonto.model.TransactionResponse;
import okhttp3.Request;

/**
 * @author jtremeaux
 */
public class TransactionService {
    public QontoClient qontoClient;

    public TransactionService(QontoClient qontoClient) {
        this.qontoClient = qontoClient;
    }

    /**
     * Get the transactions.
     * TODO handle pagination (by default get 100)
     *
     * @param iban The IBAN
     * @return The list of transactions
     */
    public TransactionResponse getTransaction(String iban) {
        Request request = qontoClient.authRequest(new Request.Builder()
                .url(qontoClient.getUrl("/v2/transactions?slug=" + qontoClient.getQontoSlug() + "&iban=" + iban + "&status=pending&status=completed"))
                .get()
                .build());
        return qontoClient.execute(request,
                (response) -> new Gson().fromJson(response.body().string(), TransactionResponse.class),
                (response) -> {
                    throw new RuntimeException("Error getting transactions, response was: " + response.body().string());
                });
    }
}
