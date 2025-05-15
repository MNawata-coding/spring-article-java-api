package com.example.spring_article_java_api.testUtils;

public final class ConstUtils {
    //sqlファイル共通の配置場所
    private ConstUtils(){} //インスタンス化禁止
    //repositoryテストの挿入データ
    public static final String REP_BASE_LOCATION = "classpath:/db/repository/";
    //MySQLコンテナのバージョン
    public static final String MYSQL_IMAGE = "mysql:8";
}
