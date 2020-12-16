# play-qonto plugin

This plugin adds [Qonto](https://qonto.eu/) support to Play! Framework 1 applications.

# Features

# How to use

####  Add the dependency to your `dependencies.yml` file

```
require:
    - qonto -> qonto 1.2.0

repositories:
    - sismicsNexusRaw:
        type: http
        artifact: "https://nexus.sismics.com/repository/sismics/[module]-[revision].zip"
        contains:
            - qonto -> *

```
####  Set configuration parameters

Add the following parameters to **application.conf**:

```
# Qonto configuration
# ~~~~~~~~~~~~~~~~~~~~
qonto.mock=false
qonto.url=https://thirdparty.qonto.eu
qonto.user=company-1234
qonto.slug=12345678
qonto.iban=FR1234567890123456789012345
```
####  Use the API

```
TransactionResponse response = QontoClient.get().getTransactionService().getTransaction(qontoClient.getQontoIban());
```

####  Mock the Qonto server in dev

We recommand to mock Qonto in development mode and test profile.

Use the following configuration parameter:

```
qonto.mock=true
```

# License

This software is released under the terms of the Apache License, Version 2.0. See `LICENSE` for more
information or see <https://opensource.org/licenses/Apache-2.0>.
