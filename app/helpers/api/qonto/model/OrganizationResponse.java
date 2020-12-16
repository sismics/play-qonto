package helpers.api.qonto.model;

import java.util.List;

/**
 * @author jtremeaux
 */
public class OrganizationResponse {
    public String slug;

    public String legal_name;

    public List<BankAccount> bank_accounts;
}
