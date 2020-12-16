package helpers.api.qonto.mock;

import helpers.api.qonto.service.OrganizationService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author jtremeaux
 */
public class MockOrganizationService {

    /**
     * Create a mock of OrganizationService.
     *
     * @return The mock
     */
    public static OrganizationService create() {
        OrganizationService organizationService = mock(OrganizationService.class);

        when(organizationService.getOrganization(any(String.class))).thenAnswer(i -> {
            return null;
        });

        return organizationService;
    }
}
