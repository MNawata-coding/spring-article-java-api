// package com.example.spring_article_java_api.repository;

// import org.apache.commons.lang3.StringUtils;
// import org.junit.jupiter.api.extension.ExtensionContext;

// import com.radcortez.flyway.test.junit.DataSourceInfo;
// import com.radcortez.flyway.test.junit.DataSourceProvider;

// public class SpringDataSourceProvider implements DataSourceProvider {

//     //ローカル判定
//     private final boolean IS_LOCAL = StringUtils.isBlank(System.getenv("DB_DIR"));
    
//     @Override
//     public DataSourceInfo getDatasourceInfo(final ExtensionContext extensionContext){
//         String url = "";
//         String username = "";
//         String password = "";
//         //ローカルの場合
//         if(!IS_LOCAL){
//             url = System.getenv("DB_URL");
//             username = System.getenv("DB_USER");
//             password = System.getenv("DB_PASS");
//         } else {
//             //testcontainerの場合
//             url = ArticleRepositoryTest.mysql.getJdbcUrl();
//             username = ArticleRepositoryTest.mysql.getUsername();
//             password = ArticleRepositoryTest.mysql.getPassword();
//         }

//         return DataSourceInfo.config(url, username, password);
//     }
// }