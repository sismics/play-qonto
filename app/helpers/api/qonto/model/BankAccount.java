package helpers.api.qonto.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jtremeaux
 */
public class BankAccount {
      public String slug;

      public String iban;

      public String bic;

      public String currency;

      public BigDecimal balance;

      public Long balance_cents;

      public BigDecimal authorized_balance;

      public Long authorized_balance_cents;

      public Date updated_at; // 2018-04-02T05:56:22.000Z
}
