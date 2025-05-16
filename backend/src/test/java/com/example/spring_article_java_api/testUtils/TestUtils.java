package com.example.spring_article_java_api.testUtils;

import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.testcontainers.containers.MySQLContainer;

public class TestUtils {

    //ローカル判定
    private final boolean IS_DOCKER = StringUtils.isBlank(System.getenv("DB_DIR"));

    /***
     * 共通データ挿入
     * マスタデータなどがある場合はFlywayで初期データを挿入
     * @param mysql
     * @param baseLocation
     */
    public void flywayConnect(MySQLContainer<?> mysql, String baseLocation){
        //flyway用datasource変数
        String url = IS_DOCKER ? mysql.getJdbcUrl() : System.getenv("DB_URL") ;
        String name = IS_DOCKER ? mysql.getUsername() : System.getenv("DB_USER") ;
        String pass = IS_DOCKER ? mysql.getPassword() : System.getenv("DB_PASS") ;

        //Flyway設定の変更
        Flyway.configure()
        .dataSource(url, name, pass)
        .locations(
            baseLocation + "maindata", //testのマイグレーションファイル
            "classpath:/db/migration" //mainのマイグレーションファイル
            )
        .load()
        .migrate();
    }
}
