package org.athena.api.util;

import org.hibernate.dialect.PostgreSQL81Dialect;

public class CustomRedshiftDialect extends PostgreSQL81Dialect {
    @Override
    public String getQuerySequencesString() {
        return null;
    }
}
