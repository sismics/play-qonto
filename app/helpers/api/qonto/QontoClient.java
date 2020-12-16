package helpers.api.qonto;

import com.sismics.sapparot.function.CheckedConsumer;
import com.sismics.sapparot.function.CheckedFunction;
import com.sismics.sapparot.okhttp.OkHttpHelper;
import helpers.api.qonto.mock.MockOrganizationService;
import helpers.api.qonto.mock.MockTransactionService;
import helpers.api.qonto.service.OrganizationService;
import helpers.api.qonto.service.TransactionService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import play.Play;

/**
 * @author jtremeaux
 */
public class QontoClient {
    private OkHttpClient client;

    private static QontoClient qontoClient;

    private OrganizationService organizationService;

    private TransactionService transactionService;

    public static QontoClient get() {
        if (qontoClient == null) {
            qontoClient = new QontoClient();
        }
        return qontoClient;
    }

    public QontoClient() {
        client = createClient();
        if (isMock()) {
            organizationService = MockOrganizationService.create();
            transactionService = MockTransactionService.create();
        } else {
            organizationService = new OrganizationService(this);
            transactionService = new TransactionService(this);
        }
    }

    private boolean isMock() {
        return Boolean.parseBoolean(Play.configuration.getProperty( "qonto.mock", "false"));
    }

    private static OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    public String getQontoUrl() {
        return Play.configuration.getProperty("qonto.url");
    }

    public String getQontoUser() {
        return Play.configuration.getProperty("qonto.user");
    }

    public String getQontoSlug() {
        return Play.configuration.getProperty("qonto.slug");
    }

    public String getQontoIban() {
        return Play.configuration.getProperty("qonto.iban");
    }

    public String getUrl(String url) {
        return getQontoUrl() + url;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public OrganizationService getOrganizationService() {
        return organizationService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public Request authRequest(Request request) {
        return request.newBuilder()
                .addHeader("Authorization", getQontoUser() + ":" + getQontoSlug())
                .build();
    }

    public <T> T execute(Request request, CheckedFunction<Response, T> onSuccess, CheckedConsumer<Response> onFailure) {
        return OkHttpHelper.execute(getClient(), request, onSuccess, onFailure);
    }
}
