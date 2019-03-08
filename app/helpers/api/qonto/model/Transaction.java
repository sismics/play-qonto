package helpers.api.qonto.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jtremeaux
 */
public class Transaction {
      public String transaction_id; // company-1234-1-transaction-8

      public BigDecimal amount; // 100.8

      public Long amount_cents; // 10080

      public String reference; // "Pay"

      public BigDecimal local_amount; // 100.8

      public Long local_amount_cents; // 10080

      public String side; // debit

      public String operation_type; // card

      public String currency; // EUR

      public String local_currency; // EUR

      public String label; // OVH

      public Date settled_at; // 2018-04-04T05:23:27.000Z

      public Date emitted_at; // 2018-04-02T05:56:22.000Z

      public String status; // completed

      public String note; // null
}
