package helpers.api.qonto.service;

import com.google.gson.Gson;
import helpers.api.qonto.QontoClient;
import helpers.api.qonto.model.StatementResponse;
import okhttp3.Request;

/**
 * @author jtremeaux
 */
public class StatementService {
    public QontoClient qontoClient;

    public StatementService(QontoClient qontoClient) {
        this.qontoClient = qontoClient;
    }

    /**
     * Get the statements.
     * TODO handle pagination (by default get 100)
     *
     * @param iban The IBAN
     * @return The list of statements
     */
    public StatementResponse getStatement(String iban) {
        Request request = qontoClient.authRequest(new Request.Builder()
                .url(qontoClient.getUrl("/v2/statements?slug=" + qontoClient.getQontoSlug() + "&ibans[]=" + iban + "&page=1&per_page=100&sort_by=period%3Adesc"))
                .get()
                .build());
        return qontoClient.execute(request,
                (response) -> new Gson().fromJson(response.body().string(), StatementResponse.class),
                (response) -> {
                    throw new RuntimeException("Error getting statements, response was: " + response.body().string());
                });
    }
}
