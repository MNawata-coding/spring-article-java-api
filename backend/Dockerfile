#使用するイメージを指定
FROM gradle:8.14-jdk21-corretto-al2023 AS build
#作業場所指定
WORKDIR /app
#現在の場所にあるファイルを/appにコピー
COPY . .
#daemonを無効化するための設定
RUN mkdir .gradle && echo "org.gradle.daemon=false" >> .gradle/gradle.properties
#docker内でjarファイルにビルドする(testを無視する)
RUN gradle clean build -x test --no-daemon

#使用するイメージを指定(java実行用)
FROM eclipse-temurin:21-jdk
#dockerizeを使用するための環境設定
ENV DOCKERIZE_VERSION=v0.9.3
#dockerizeをインストール
RUN apt-get update \
    && apt-get install -y wget \
    && wget -O - https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz | tar xzf - -C /usr/local/bin \
    && apt-get autoremove -yqq --purge wget && apt-get clean && rm -rf /var/lib/apt/lists/*
#ビルドしたjarをコピーする
COPY --from=build /app/build/libs/spring-article-java-api-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT [実行したいコマンド](app.jarを実行する)(CMDとの違いを後で調べておく)
ENTRYPOINT ["dockerize", "-wait", "tcp://mysql:3306", "-timeout", "5s", "java", "-jar", "app.jar" ]
