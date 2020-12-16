package helpers.api.qonto.service;

import com.google.gson.Gson;
import helpers.api.qonto.QontoClient;
import helpers.api.qonto.model.OrganizationsResponse;
import okhttp3.Request;

/**
 * @author jtremeaux
 */
public class OrganizationService {
    public QontoClient qontoClient;

    public OrganizationService(QontoClient qontoClient) {
        this.qontoClient = qontoClient;
    }

    /**
     * Get the bank accounts of the organisation.
     *
     * @param slug The slug of the organization
     * @return The list of bank accounts
     */
    public OrganizationsResponse getOrganization(String slug) {
        Request request = qontoClient.authRequest(new Request.Builder()
                .url(qontoClient.getUrl("/v2/organizations/" + slug))
                .get()
                .build());
        return qontoClient.execute(request,
                (response) -> new Gson().fromJson(response.body().string(), OrganizationsResponse.class),
                (response) -> {
                    throw new RuntimeException("Error getting organizations, response was: " + response.body().string());
                });
    }
}
