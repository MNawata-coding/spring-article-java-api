package com.example.spring_article_java_api.repository;

import org.junit.jupiter.api.extension.ExtensionContext;

import com.radcortez.flyway.test.junit.DataSourceInfo;
import com.radcortez.flyway.test.junit.DataSourceProvider;

public class SpringDataSourceProvider implements DataSourceProvider {
    @Override
    public DataSourceInfo getDatasourceInfo(final ExtensionContext extensionContext){
        final String url = System.getenv("DB_URL");
        final String username = System.getenv("DB_USER");
        final String password = System.getenv("DB_PASS");

        return DataSourceInfo.config(url, username, password);
    }
}
