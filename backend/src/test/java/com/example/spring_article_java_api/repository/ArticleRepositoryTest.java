package com.example.spring_article_java_api.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.spring_article_java_api.entity.Article;
import com.example.spring_article_java_api.testUtils.ConstUtils;
import com.example.spring_article_java_api.testUtils.TestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@DataJpaTest
public class ArticleRepositoryTest {
    
    ArticleRepository repository;
    
    //テスト実行前にmysql9のイメージを取得、コンテナを起動
    //()内でバージョンを指定
    @Container
    @ServiceConnection
    static final MySQLContainer<?> mysql = new MySQLContainer<>(ConstUtils.MYSQL_IMAGE);

    @Autowired
    public ArticleRepositoryTest(ArticleRepository repository){
        this.repository = repository;
    }
    
    /***
     * 共通データ挿入
     * マスタデータなどがある場合はFlywayで初期データを挿入
     */
    @BeforeAll
    public void flywaySet(){
        TestUtils connect = new TestUtils();
        connect.flywayConnect(mysql, ConstUtils.REP_BASE_LOCATION);
    }

    /***
     * DB取得テスト
     * 取得した内容が一致しているかどうかを検証する
     * @throws Exception
     */
    @Test
    public void findAll() throws Exception {
        //全権取得
        List<Article> article = repository.findAll();
        //件数チェック
        assertThat(article).as("リストの個数")
            .hasSize(9)
            .withFailMessage("リストの個数が一致しません");

        //Title比較
        List<String> titleList = article.stream().map(e -> e.getTitle()).toList();
        //タイトル期待値追加
        List<String> titleExpect = List.of(
            "testTitle1",
            "testTitle2",
            "testTitle3",
            "testTitle4",
            "testTitle5",
            "testTitle6",
            "testTitle7",
            "testTitle8",
            "testTitle9"
        );

        //内容が一致しているかを確認
        assertThat(titleList).as("タイトル一覧")
            .isEqualTo(titleExpect)
            .withFailMessage("タイトルの値が一致しません");

        //Content比較
        List<String> contentList = article.stream().map(e -> e.getContent()).toList();
        //内容期待値追加
        List<String> contentExpect = List.of(
            "content1",
            "content2",
            "content3",
            "content4",
            "content5",
            "content6",
            "content7",
            "content8",
            "content9"
        );

        //リスト内容を比較
        assertThat(contentList).as("内容リスト")
            .isEqualTo(contentExpect)
            .withFailMessage("内容の値が一致しません");

        //likecountTest
        //デフォルト0が入っているか確認
        List<Long> likeList = article.stream().map(s -> s.getLikeCnt()).toList();
        //全て0だったらOK
        ArticleAssertUtil.assertThat(likeList).as("ライク数").isAllZero(likeList);

        //ViewCountTest
        //デフォルト0が入っているか確認
        List<Long> viewList = article.stream().map(s -> s.getViewCnt()).toList();
        //全て0だったらOK
        ArticleAssertUtil.assertThat(viewList).as("閲覧数").isAllZero(viewList);

        //予約日NULLテスト
        //予約日に値がなければOK
        List<LocalDateTime> reservationList = article.stream().map(s -> s.getReservationDay()).toList();
        
        //全てNULLの場合成功
        assertThat(reservationList).as("予約日リスト")
            .containsOnlyNulls()
            .withFailMessage("予約日の値が一致しません");

        //作成者テスト
        List<Long> userList = article.stream().map(s -> s.getCreatedBy()).toList();
        //期待値を追加
        List<Long> userExpect = List.of(
            1L,
            2L,
            3L,
            4L,
            5L,
            6L,
            7L,
            8L,
            9L
        );
        //値の整合性とリスト内の個数を検証
        assertThat(userList).as("作成者名")
            .containsAll(userExpect)
            .withFailMessage("作成者の値が一致しません");

        //作成日を抽出
        List<LocalDateTime> createdAtList = article.stream().map(e -> e.getCreatedAt()).toList();
        //作成日期待値追加
        List<LocalDateTime> createdAtExpect = List.of(
            LocalDateTime.of(2025, 01, 01, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 05, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 02, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 12, 10, 10, 00000),
            LocalDateTime.of(2025, 01, 04, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000)
        );
        //作成日がすべて含まれているか確認
        assertThat(createdAtList).as("作成日リスト")
            .containsAll(createdAtExpect)
            .withFailMessage("作成日の値が一致しません");

        //削除フラグ検証
        //全てfalseで入っていればOK
        List<Boolean> deleteFlgList = article.stream().map(s -> s.isDeleteFlg()).toList();
        assertThat(deleteFlgList).as("デリートフラグ")
            .allMatch(Boolean.FALSE::equals)
            .withFailMessage("削除フラグの値が一致しません");
    }

    /***
     * DB削除テスト
     * 取得した内容が一致しているかどうかを検証する
     * @throws Exception
     */
    // @Sql(scripts = createdAtDesc + "/R__insert_data.sql")
    @Test
    public void deleteData() throws Exception {
        //id=3を削除する
        int id = 3;
        repository.deleteById(id);
        //全権取得
        List<Article> article = repository.findAll();
        //件数チェック
        assertThat(article).as("検索したリスト")
            .hasSize(8);

        //Title比較
        List<String> titleList = article.stream().map(e -> e.getTitle()).toList();
        //タイトル期待値追加
        List<String> titleExpect = List.of(
            "testTitle1",
            "testTitle2",
            "testTitle4",
            "testTitle5",
            "testTitle6",
            "testTitle7",
            "testTitle8",
            "testTitle9"
        );

        assertThat(titleList).as("タイトルのリスト")
            .containsAll(titleExpect)
            .withFailMessage("タイトルの値が一致しません");

        //Content比較
        List<String> contentList = article.stream().map(e -> e.getContent()).toList();
        //内容期待値追加
        List<String> contentExpect = List.of(
            "content1",
            "content2",
            "content4",
            "content5",
            "content6",
            "content7",
            "content8",
            "content9"
        );
        assertThat(contentList).as("内容リスト")
            .containsAll(contentExpect)
            .withFailMessage("内容の値が一致しません");

        //likecountTest
        List<Long> likeList = article.stream().map(s -> s.getLikeCnt()).toList();
        //すべて0の場合正常
        ArticleAssertUtil.assertThat(likeList).as("ライク数").isAllZero(likeList);

        //ViewCountTest
        //デフォルト0が入っているか確認
        List<Long> viewList = article.stream().map(s -> s.getViewCnt()).toList();
        //全て0の場合正常
        ArticleAssertUtil.assertThat(viewList).as("閲覧数").isAllZero(viewList);

        //予約日NULLテスト
        //予約日に値がなければOK
        List<LocalDateTime> reservationList = article.stream().map(s -> s.getReservationDay()).toList();
        assertThat(reservationList).as("予約日")
            .containsOnlyNulls();
        //作成者テスト
        List<Long> userList = article.stream().map(s -> s.getCreatedBy()).toList();
        //期待値を追加
        List<Long> userExpect = List.of(
            1L,
            2L,
            4L,
            5L,
            6L,
            7L,
            8L,
            9L
        );
        //値の整合性とリスト内の個数を検証
        assertThat(userList).as("ユーザーリスト")
            .containsAll(userExpect)
            .withFailMessage("ユーザーリストの値が違います");

        //作成日を抽出
        List<LocalDateTime> createdAtList = article.stream().map(e -> e.getCreatedAt()).toList();
        //作成日期待値追加
        List<LocalDateTime> createdAtExpect = List.of(
            LocalDateTime.of(2025, 01, 01, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 05, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 12, 10, 10, 00000),
            LocalDateTime.of(2025, 01, 04, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000)
        );
        //作成日がすべて含まれているか確認
        assertThat(createdAtList).as("作成日")
            .containsAll(createdAtExpect);

            //リストの個数が合致するか確認
        assertThat(createdAtList).as("作成日")
            .hasSize(createdAtExpect.size());

        //削除フラグ検証
        //全てfalseで入っていればOK
        List<Boolean> deleteFlgList = article.stream().map(s -> s.isDeleteFlg()).toList();
        assertThat(deleteFlgList).as("削除フラグ")
            .allMatch(Boolean.FALSE::equals);
    }

    /***
     * DB更新、挿入テスト
     * 取得した内容が一致しているかどうかを検証する
     * @throws Exception
     */
    // @Sql(scripts = createdAtDesc + "/R__insert_data.sql")
    @Test
    public void upsertTest() throws Exception {

        int idThree = 3;
        //idからEntity取得
        Optional<Article> artThree = repository.findById(idThree);
        //タイトルを設定
        artThree.get().setTitle("titleThree");


        //Articleに変換
        Article updateThree = artThree.get();
        //保存処理実行
        repository.save(updateThree);

        //変更するID
        int idFour = 4;
        //idからEntity取得
        Optional<Article> artFour = repository.findById(idFour);
        //内容を設定
        artFour.get().setContent("contentFour");

        //Articleに変換
        Article updateFour = artFour.get();
        //保存処理実行
        repository.save(updateFour);

        int idFive = 5;
        //idからEntity取得
        Optional<Article> artFive = repository.findById(idFive);
        //公開フラグを設定
        artFive.get().setReleaseFlg(true);

        //Articleに変換
        Article updateFive = artFive.get();
        //保存処理実行
        repository.save(updateFive);

        int idSix = 6;
        //idからEntity取得
        Optional<Article> artSix = repository.findById(idSix);
        //予約日を設定
        artSix.get().setReservationDay(LocalDateTime.of(2020, 12, 12, 11, 11, 11));

        //Articleに変換
        Article updateSix = artSix.get();
        //保存処理実行
        repository.save(updateSix);
        
        int idSeven = 7;
        //idからEntity取得
        Optional<Article> artSeven = repository.findById(idSeven);
        //更新する値を設定
        artSeven.get().setTitle("sevenTitle");
        artSeven.get().setContent("sevenContent");
        artSeven.get().setReleaseFlg(true);
        artSeven.get().setReservationDay(LocalDateTime.of(2022, 01, 12, 11, 11, 11));
        artSeven.get().addLikeCnt();
        artSeven.get().addViewCnt();
        artSeven.get().deleteFlgDelete();
        //Articleに変換
        Article updateSeven = artSeven.get();
        //保存処理実行
        repository.save(updateSeven);

        //likecount
        int idEight = 8;
        Optional<Article> artEight = repository.findById(idEight);
        //like数プラス
        artEight.get().addLikeCnt();
        Article updateEight = artEight.get();
        //保存処理
        repository.save(updateEight);

        //viewcount
        int idNine = 9;
        Optional<Article> artNine = repository.findById(idNine);
        //閲覧数プラス
        artNine.get().addViewCnt();
        Article updateNine = artNine.get();
        //保存処理
        repository.save(updateNine);


        //Insert処理
        Article insArt = new Article(
                    "upsertTest",
                    "this is UpSert Test",
                    false,
                    99L
                );
        repository.save(insArt);

        List<Article> article = repository.findAll();

        //Title比較
        List<String> titleList = article.stream().map(e -> e.getTitle()).toList();
        //タイトル期待値追加
        List<String> titleExpect = List.of(
            "testTitle1",
            "testTitle2",
            "titleThree",
            "testTitle4",
            "testTitle5",
            "testTitle6",
            "sevenTitle",
            "testTitle8",
            "testTitle9",
            "upsertTest"
        );
        assertThat(titleList).as("タイトルリスト")
            .containsAll(titleExpect);

        //Content比較
        List<String> contentList = article.stream().map(e -> e.getContent()).toList();
        //内容期待値追加
        List<String> contentExpect = List.of(
            "content1",
            "content2",
            "content3",
            "contentFour",
            "content5",
            "content6",
            "sevenContent",
            "content8",
            "content9",
            "this is UpSert Test"
        );
        assertThat(contentList).as("内容リスト")
            .containsAll(contentExpect);

        //likecountTest
        //デフォルト0が入っているか確認
        List<Long> likeList = article.stream().map(s -> s.getLikeCnt()).toList();
        //全て0の場合正常
        ArticleAssertUtil.assertThat(likeList).as("ライク数").isAllZero(likeList);

        //ViewCountTest
        //デフォルト0が入っているか確認
        List<Long> viewList = article.stream().map(s -> s.getViewCnt()).toList();
        //全て0の場合正常
        ArticleAssertUtil.assertThat(viewList).as("閲覧数").isAllZero(viewList);

        //予約日NULLテスト
        //予約日に値がなければOK
        List<LocalDateTime> reservationList = article.stream().map(s -> s.getReservationDay()).toList();
        //予約日がnullの値を取得する
        List<LocalDateTime> reservationFilterList = reservationList.stream().filter(Objects::isNull).toList();
        //nullの数が8個であれば正常
        assertThat(reservationFilterList).as("予約日nullのリスト").hasSize(8);

        //予約日がnullではない値を取得
        List<LocalDateTime> reservationTwoList = reservationList.stream().filter(Objects::nonNull).toList();
        List<LocalDateTime> reservationExtextedList = List.of(
            LocalDateTime.of(2022, 01, 12, 11, 11, 11),
            LocalDateTime.of(2020, 12, 12, 11, 11, 11)
        );
        //予約日がnullの値で
        assertThat(reservationTwoList).as("予約日リスト").containsAll(reservationExtextedList);


        //作成者テスト
        List<Long> userList = article.stream().map(s -> s.getCreatedBy()).toList();
        //期待値を追加
        List<Long> userExpect = List.of(
            1L,
            2L,
            3L,
            4L,
            5L,
            6L,
            7L,
            8L,
            9L,
            99L
        );
        //値の整合性とリスト内の個数を検証
        assertThat(userList).as("作成者名")
            .containsAll(userExpect);
 
        //作成日を抽出
        List<LocalDateTime> createdAtList = article.stream().map(e -> e.getCreatedAt()).toList();
        //作成日期待値追加
        List<LocalDateTime> createdAtExpect = List.of(
            LocalDateTime.of(2025, 01, 01, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 05, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 02, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 12, 10, 10, 00000),
            LocalDateTime.of(2025, 01, 04, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            artSeven.get().getCreatedAt() //作成日は内部で生成されるため、Entityの値を使用
        );
        //作成日がすべて含まれているか確認
        assertThat(createdAtList).as("作成日")
            .containsAll(createdAtExpect)
            .withFailMessage("作成日の値が一致しません");

        //削除フラグ検証
        List<Boolean> deleteFlgList = article.stream().map(s -> s.isDeleteFlg()).toList();
        List<Boolean> filterFlg = deleteFlgList.stream().filter(Boolean.FALSE::equals).toList();
        assertThat(filterFlg).as("削除フラグ").hasSize(9);
        assertThat(deleteFlgList).as("削除フラグ(全て)").hasSize(10);

        
        //like数
        //like数を取得
        List<Long> filterlikeList = article.stream().map(s -> s.getLikeCnt()).toList();
        //like数が0のデータを取得
        List<Long> zeroFilterLikeList = filterlikeList.stream().filter(s -> s == 0).toList();
        assertThat(zeroFilterLikeList).as("like数").hasSize(8);
        //1の数を取得
        List<Long> oneFilterLikeList = filterlikeList.stream().filter(s -> s == 1).toList();
        assertThat(oneFilterLikeList).as("like数が1の値").hasSize(2);

        //閲覧数
        //閲覧数を取得
        List<Long> filterViewList = article.stream().map(s -> s.getViewCnt()).toList();
        //0のデータを取得
        List<Long> zeroFilterViewList = filterViewList.stream().filter(s -> s == 0).toList();
        assertThat(zeroFilterViewList).as("閲覧数0のデータ").hasSize(8);
        //1のデータを取得
        List<Long> oneFilterViewList = filterViewList.stream().filter(s -> s == 1).toList();
        assertThat(oneFilterViewList).as("閲覧数1のデータ").hasSize(2);
    }

    /***
     * タイトル検索テスト
     * @throws Exception
     */
    @Test
    public void findByTitleContainingIgnoreCaseTest() throws Exception {
        //title1に合致する値を取得(大文字)
        List<Article> findUpperCase = repository.findByTitleContainingIgnoreCase("Title1", null);
        //ID=1の値が取得できているか確認
        assertThat(findUpperCase.get(0)).as("title1検索").isEqualTo(repository.findById(1).get());
        //title1に合致する値を取得(小文字)
        List<Article> findLowerCase = repository.findByTitleContainingIgnoreCase("title1", null);
        //ID=1の値が取得できているか確認
        assertThat(findLowerCase.get(0)).as("title1検索").isEqualTo(repository.findById(1).get());

        //値を登録
        Article insertArt001 = new Article(
            "titleTest001",
            "contentTest001",
            false,
            100001L
        );
        repository.save(insertArt001);

        //値を登録
        Article insertArt002 = new Article(
            "titletest002",
            "contentTest002",
            false,
            100002L
        );
        repository.save(insertArt002);

        //登録した値のみ検索する
        List<Article> searchLowerArticles = repository.findByTitleContainingIgnoreCase("TITLETEST", null);
        List<Article> searchUpperArticles = repository.findByTitleContainingIgnoreCase("titletest", null);
        //期待値を登録する
        List<Article> expectsList = List.of(
            insertArt001,
            insertArt002
        );
        //大文字、小文字、期待値を比較
        assertThat(searchLowerArticles).as("登録した値を検索")
            .isEqualTo(searchUpperArticles)
            .isEqualTo(expectsList);
    }

}
