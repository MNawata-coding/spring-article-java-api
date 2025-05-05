#使用するイメージを指定
FROM gradle:8.14-jdk21-corretto-al2023 AS build
#作業場所指定
WORKDIR /app
#現在の場所にあるファイルを/appにコピー
COPY . .
#daemonを無効化するための設定
RUN mkdir .gradle && echo "org.gradle.daemon=false" >> .gradle/gradle.properties
#docker内でjarファイルにビルドする
RUN gradle clean build --no-daemon

#使用するイメージを指定(java実行用)
FROM eclipse-temurin:21-jdk
#ビルドしたjarをコピーする
COPY --from=build /app/build/libs/spring-article-java-api-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT [実行したいコマンド](app.jarを実行する)(CMDとの違いを後で調べておく)
ENTRYPOINT [ "java", "-jar", "app.jar" ]
