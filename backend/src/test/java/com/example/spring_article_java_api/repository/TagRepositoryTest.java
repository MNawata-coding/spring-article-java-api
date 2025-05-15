package com.example.spring_article_java_api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.spring_article_java_api.entity.Tag;
import com.example.spring_article_java_api.testUtils.ConstUtils;
import com.example.spring_article_java_api.testUtils.TestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@DataJpaTest
public class TagRepositoryTest {

    @Autowired
    TagRepository repository;
    
    @Container
    @ServiceConnection
    static final MySQLContainer<?> mysql = new MySQLContainer<>(ConstUtils.MYSQL_IMAGE);
    
    /***
     * flywayデータ挿入
     */
    @BeforeAll
    public void flywaySet(){
        TestUtils connect = new TestUtils();
        connect.flywayConnect(mysql, ConstUtils.REP_BASE_LOCATION);
    }

    /***
     * タグタイトルの取得テスト
     * @param test
     */
    @Test
    @DisplayName("FindAll_tagIDをすべて取得できること")
    public void findAllTagId() throws Exception {
        //登録されているデータを全て取得
        List<Tag> tag = repository.findAll();
        //リスト数が9であることを確認
        assertThat(tag).as("リスト数").hasSize(9);

        //取得した値のうち、タグ名称のみ使用する
        List<Integer> tagNameList = tag.stream().map(s -> s.getTagId()).toList();
        //期待値を設定する
        List<Integer> tagNameExpects = List.of(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9
        );

        //取得したIDが設定されている値と同じだったら正常
        assertThat(tagNameList).as("タグ名称")
            .containsAll(tagNameExpects);
    }

    /***
     * タグタイトルの取得テスト
     * @param test
     */
    @Test
    @DisplayName("FindAll_tagNameをすべて取得できること")
    public void findAllTagName() throws Exception {
        //登録されているデータを全て取得
        List<Tag> tag = repository.findAll();
        //リスト数が9であることを確認
        assertThat(tag).as("リスト数").hasSize(9);

        //取得した値のうち、タグ名称のみ使用する
        List<String> tagNameList = tag.stream().map(s -> s.getTagName()).toList();
        //期待値を設定する
        List<String> tagNameExpects = List.of(
            "tagTestName1",
            "tagTestName2",
            "tagTestName3",
            "tagTestName4",
            "tagTestName5",
            "tagTestName6",
            "tagTestName7",
            "tagTestName8",
            "tagTestName9"
        );

        //取得したIDが設定されている値と同じだったら正常
        assertThat(tagNameList).as("タグ名称")
            .containsAll(tagNameExpects);
    }

    /***
     * 作成者の取得テスト
     * @param test
     */
    @Test
    @DisplayName("FindAll_created_byをすべて取得できること")
    public void findAllCreatedBy() throws Exception{
        //登録されているデータを全て取得
        List<Tag> tag = repository.findAll();
        //リスト数が9であることを確認
        assertThat(tag).as("リスト数").hasSize(9);

        //取得した値のうち、作成者のみ使用する
        List<String> CreatedByList = tag.stream().map(s -> s.getCreatedBy()).toList();
        //期待値を設定する
        List<String> CreatedByExpects = List.of(
            "tagCreatedBy1",
            "tagCreatedBy2",
            "tagCreatedBy3",
            "tagCreatedBy4",
            "tagCreatedBy5",
            "tagCreatedBy6",
            "tagCreatedBy7",
            "tagCreatedBy8",
            "tagCreatedBy9"
        );

        //取得した作成者が設定されている値と同じだったら正常
        assertThat(CreatedByList).as("作成者")
            .containsAll(CreatedByExpects);
    }

    /***
     * 作成時間の取得テスト
     * @param test
     */
    @Test
    @DisplayName("FindAll_created_atをすべて取得できること")
    public void findAllCreatedAt() throws Exception{
        //登録されているデータを全て取得
        List<Tag> tag = repository.findAll();
        //リスト数が9であることを確認
        assertThat(tag).as("リスト数").hasSize(9);

        //取得した値のうち、作成者のみ使用する
        List<LocalDateTime> CreatedAtList = tag.stream().map(s -> s.getCreatedAt()).toList();
        //期待値を設定する
        List<LocalDateTime> CreatedAtExpects = List.of(
            LocalDateTime.of(2025, 01, 01, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 05, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 02, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 04, 12, 10, 10, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 03, 00, 00, 00, 00000)
        );

        //取得した作成者が設定されている値と同じだったら正常
        assertThat(CreatedAtList).as("作成者")
            .containsAll(CreatedAtExpects);
    }

    /***
     * 削除フラグの取得テスト
     * @param test
     */
    @Test
    @DisplayName("FindAll_delete_flgをすべて取得できること")
    public void findAllDeleteFlg() throws Exception{
        //登録されているデータを全て取得
        List<Tag> tag = repository.findAll();
        //リスト数が9であることを確認
        assertThat(tag).as("リスト数").hasSize(9);

        //取得した値のうち、タグタイトルのみ使用する
        List<Boolean> CreatedByList = tag.stream().map(s -> s.isDeleteFlg()).toList();

        //falseのみの場合正常
        assertThat(CreatedByList).as("作成者")
            .containsOnly(false);
    }

    /***
     * タグ名称更新テスト
     * @param idNo
     */
    @DisplayName("タグ名称を正常に更新できること")
    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    public void tagNameUpdateTest(int idNo){
        //idからデータを取得する
        Optional<Tag> tag = repository.findById(idNo);
        //nullチェック
        assertThat(tag).as("nullチェック")
            .isPresent();
        //タグ名称を変更する
        String TagName = "tagUpdateTest" + idNo;
        //変更をセットする
        tag.get().setTagName(TagName);
        //データを保存する
        repository.save(tag.get());

        //変更した値を習得する
        Optional<Tag> tagGet = repository.findById(idNo);
        //取得した値が設定した値と同じ場合、正常
        assertThat(tagGet.get().getTagName()).as("タグ名称")
            .isEqualTo(TagName);
    }

    /***
     * 更新者更新テスト
     * @param idNo
     */
    @DisplayName("更新者を正常に更新できること")
    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    public void titleUpdateTest(int idNo){
        //idからデータを取得する
        Optional<Tag> tag = repository.findById(idNo);
        //nullチェック
        assertThat(tag).as("nullチェック")
            .isPresent();
        //タグ名称を変更する
        String updatedBy = "updatedByUpdateTest" + idNo;
        //変更をセットする
        tag.get().changeUpdatedBy(updatedBy);
        //データを保存する
        repository.save(tag.get());

        //変更した値を習得する
        Optional<Tag> tagGet = repository.findById(idNo);
        //取得した値が設定した値と同じ場合、正常
        assertThat(tagGet.get().getUpdatedBy()).as("更新者")
            .isEqualTo(updatedBy);
    }

    /***
     * 更新時間更新テスト
     * @param idNo
     */
    @DisplayName("更新時間を正常に更新できること")
    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    public void updatedAtUpdateTest(int idNo){
        //idからデータを取得する
        Optional<Tag> tag = repository.findById(idNo);
        //nullチェック
        assertThat(tag).as("nullチェック")
            .isPresent();
        //タグ名称を変更する
        LocalDateTime updatedAt = LocalDateTime.now();
        //変更をセットする
        tag.get().changeUpdatedAt(updatedAt);
        //データを保存する
        repository.save(tag.get());

        //変更した値を習得する
        Optional<Tag> tagGet = repository.findById(idNo);
        //取得した値が設定した値と同じ場合、正常
        assertThat(tagGet.get().getUpdatedAt()).as("更新時間")
            .isEqualTo(updatedAt);
    }

    /***
     * 削除フラグ更新テスト
     * @param idNo
     */
    @DisplayName("削除フラグを正常に更新できること")
    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    public void deleteFlgUpdateTest(int idNo){
        //idからデータを取得する
        Optional<Tag> tag = repository.findById(idNo);
        //nullチェック
        assertThat(tag).as("nullチェック")
            .isPresent();
        //タグ名称を変更する
        boolean deleteFlg = true;
        //変更をセットする
        tag.get().deleteFlgDelete();
        //データを保存する
        repository.save(tag.get());

        //変更した値を習得する
        Optional<Tag> flgFalse = repository.findById(idNo);
        //nullチェック
        assertThat(flgFalse).as("nullチェック")
            .isPresent();
        //取得した値が設定した値と同じ場合、正常
        assertThat(flgFalse.get().isDeleteFlg()).as("削除フラグ")
            .isEqualTo(deleteFlg);

        boolean restore = false;
        //削除フラグ更新
        tag.get().deleteFlgRestore();
        //保存処理
        repository.save(tag.get());

        //取得
        Optional<Tag> flgTrue = repository.findById(idNo);
        //nullチェック
        assertThat(flgTrue).as("nullチェック")
            .isPresent();
        //削除フラグ
        assertThat(flgTrue.get().isDeleteFlg()).as("削除フラグ")
            .isEqualTo(restore);
    }

    /***
     * 削除テスト
     * @param idNo
     */
    @DisplayName("削除が正常にできること")
    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    public void deleteTest(int idNo){
        //idからデータを取得する
        Optional<Tag> tag = repository.findById(idNo);
        //nullチェック
        assertThat(tag).as("nullチェック")
            .isPresent();
        //削除
        repository.deleteById(idNo);
        //削除したデータを取得
        Optional<Tag> deleteTag = repository.findById(idNo);
        //emptyであればOK
        assertThat(deleteTag).as("nullチェック")
            .isNotPresent();
    }
    /***
     * 挿入テスト
     * @param idNo
     */
    @DisplayName("挿入が正常にできること")
    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    public void insertTest(int count){
        long idCount = repository.count();
        //タグEntityを設定
        int tagId = (int)idCount + count;
        String tagName = "insertName" + count;
        String createdBy = "insertCreatedBy" + count;
        boolean deleteFlg = false;
        Tag tag = new Tag(tagName, createdBy, deleteFlg);
        //Entityを保存する
        repository.save(tag);
        //取得
        Optional<Tag> getTag = repository.findById(tagId);
        assertThat(getTag).as("nullチェック")
            .isPresent();
        //特定の値があるか確認
        assertThat(getTag.get().getTagId()).as("タグID")
            .isEqualTo(tagId);
        
        assertThat(getTag.get().getTagName()).as("タグ名称")
            .isEqualTo(tagName);

        assertThat(getTag.get().getCreatedBy()).as("作成者")
            .isEqualTo(createdBy);

        assertThat(getTag.get().isDeleteFlg()).as("削除フラグ")
            .isEqualTo(deleteFlg);

        assertThat(getTag.get().getCreatedAt()).as("作成時間")
            .isEqualTo(tag.getCreatedAt());

    }
}
