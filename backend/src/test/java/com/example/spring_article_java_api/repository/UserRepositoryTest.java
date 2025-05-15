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

import com.example.spring_article_java_api.constant.EnumList.ROLE;
import com.example.spring_article_java_api.entity.User;
import com.example.spring_article_java_api.testUtils.ConstUtils;
import com.example.spring_article_java_api.testUtils.TestUtils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@DataJpaTest
public class UserRepositoryTest {

    @Container
    @ServiceConnection
    static final MySQLContainer<?> mysql = new MySQLContainer<>(ConstUtils.MYSQL_IMAGE);

    @Autowired
    UserRepository repository;


    /***
     * flywayで初期データを投入
     */
    @BeforeAll
    public void flywaySet(){
        TestUtils connect = new TestUtils();
        connect.flywayConnect(mysql, ConstUtils.REP_BASE_LOCATION);
    }

    /***
     * 名前取得
     */
    @Test
    @DisplayName("ユーザー名取得テスト")
    public void findAllName(){
        //データ取得
        List<User> users = repository.findAll();
        //空ではないこと
        assertThat(users).as("空チェック")
            .isNotEmpty();
        //データを取得する
        List<String> userNames = users.stream().map(s -> s.getName()).toList();
        //期待値
        List<String> expectsName = List.of(
            "userName1",
            "userName2",
            "userName3",
            "userName4",
            "userName5",
            "userName6",
            "userName7",
            "userName8",
            "userName9"
        );
        //期待値と同じだったら正常
        assertThat(userNames).as("ユーザー名")
            .containsAll(expectsName)
            .hasSize(9);
    }

    /***
     * メールアドレス取得
     */
    @Test
    @DisplayName("メールアドレス取得テスト")
    public void findAllEmail(){
        //データ取得
        List<User> users = repository.findAll();
        //空ではないこと
        assertThat(users).as("空チェック")
            .isNotEmpty();
        //データを取得する
        List<String> emails = users.stream().map(s -> s.getEmail()).toList();
        //期待値
        List<String> expectsEmails = List.of(
            "email@example.com1",
            "email@example.com2",
            "email@example.com3",
            "email@example.com4",
            "email@example.com5",
            "email@example.com6",
            "email@example.com7",
            "email@example.com8",
            "email@example.com9"
        );
        //期待値と同じだったら正常
        assertThat(emails).as("メールアドレス")
            .containsAll(expectsEmails)
            .hasSize(9);
    }

    /***
     * 役職取得
     */
    @Test
    @DisplayName("役職取得テスト")
    public void findAllRole(){
        //データ取得
        List<User> users = repository.findAll();
        //空ではないこと
        assertThat(users).as("空チェック")
            .isNotEmpty();
        //データを取得する
        List<ROLE> role = users.stream().map(s -> s.getRole()).toList();
        //期待値
        List<ROLE> expectsRole = List.of(
            ROLE.USER,
            ROLE.USER,
            ROLE.USER,
            ROLE.USER,
            ROLE.USER,
            ROLE.USER,
            ROLE.USER,
            ROLE.USER,
            ROLE.ADMIN
        );
        //期待値と同じだったら正常
        assertThat(role).as("役職")
            .containsAll(expectsRole)
            .hasSize(9);
    }

    /***
     * 作成者取得
     */
    @Test
    @DisplayName("作成者取得テスト")
    public void findAllCreatedBy(){
        //データ取得
        List<User> users = repository.findAll();
        //空ではないこと
        assertThat(users).as("空チェック")
            .isNotEmpty();
        //データを取得する
        List<String> createdBy = users.stream().map(s -> s.getCreatedBy()).toList();
        //期待値
        List<String> expectsCreatedBy = List.of(
            "UserCreatedBy1",
            "UserCreatedBy2",
            "UserCreatedBy3",
            "UserCreatedBy4",
            "UserCreatedBy5",
            "UserCreatedBy6",
            "UserCreatedBy7",
            "UserCreatedBy8",
            "UserCreatedBy9"
        );
        //期待値と同じだったら正常
        assertThat(createdBy).as("作成者")
            .containsAll(expectsCreatedBy)
            .hasSize(9);
    }

    /***
     * 作成日取得
     */
    @Test
    @DisplayName("作成日取得テスト")
    public void findAllCreatedAt(){
        //データ取得
        List<User> users = repository.findAll();
        //空ではないこと
        assertThat(users).as("空チェック")
            .isNotEmpty();
        //データを取得する
        List<LocalDateTime> createdAt = users.stream().map(s -> s.getCreatedAt()).toList();
        //期待値
        List<LocalDateTime> expectsCreatedAt = List.of(
            LocalDateTime.of(2025, 01, 1, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 2, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 3, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 4, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 5, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 6, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 7, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 8, 00, 00, 00, 00000),
            LocalDateTime.of(2025, 01, 9, 00, 00, 00, 00000)
        );
        //期待値と同じだったら正常
        assertThat(createdAt).as("作成日")
            .containsAll(expectsCreatedAt)
            .hasSize(9);
    }

    /***
     * 削除フラグ取得
     */
    @Test
    @DisplayName("削除フラグ取得テスト")
    public void findAllDeleteFlg(){
        //データ取得
        List<User> users = repository.findAll();
        //空ではないこと
        assertThat(users).as("空チェック")
            .isNotEmpty();
        //データを取得する
        List<Boolean> createdAt = users.stream().map(s -> s.isDeleteFlg()).toList();
        //期待値と同じだったら正常
        assertThat(createdAt).as("作成日")
            .allMatch(Boolean.FALSE::equals)
            .hasSize(9);
    }

    /***
     * 名称更新
     */
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4})
    @DisplayName("名称更新テスト")
    public void updateName(int idNum){
        //データ取得
        Optional<User> user = repository.findById(idNum);
        //nullチェック
        assertThat(user).as("個別取得")
            .isPresent();
        //データを取得する
        String expects = "updateName" + idNum;
        user.get().setName(expects);
        //保存する
        repository.save(user.get());
        //再取得
        Optional<User> updateUser = repository.findById(idNum);
        assertThat(updateUser.get()).as("個別取得")
            .isNotNull();
        //期待値と同じだったら正常
        assertThat(updateUser.get().getName()).as("名前")
            .isEqualTo(expects);
    }

    /***
     * メールアドレス更新
     */
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4})
    @DisplayName("メールアドレス更新テスト")
    public void updateEmail(int idNum){
        //データ取得
        Optional<User> user = repository.findById(idNum);
        //nullチェック
        assertThat(user).as("個別取得")
            .isPresent();
        //データを取得する
        String expects = "updateEmail" + idNum;
        user.get().setEmail(expects);
        //保存する
        repository.save(user.get());
        //再取得
        Optional<User> updateUser = repository.findById(idNum);
        assertThat(updateUser.get()).as("個別取得")
            .isNotNull();
        //期待値と同じだったら正常
        assertThat(updateUser.get().getEmail()).as("メールアドレス")
            .isEqualTo(expects);
    }
    /***
     * 役職更新
     */
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4})
    @DisplayName("役職更新テスト")
    public void updateRole(int idNum){
        //データ取得
        Optional<User> user = repository.findById(idNum);
        //nullチェック
        assertThat(user).as("個別取得")
            .isPresent();
        //設定
        user.get().setRole(ROLE.ADMIN);
        //保存する
        repository.save(user.get());
        //再取得
        Optional<User> updateUser = repository.findById(idNum);
        assertThat(updateUser.get()).as("個別取得")
            .isNotNull();
        //期待値と同じだったら正常
        assertThat(updateUser.get().getRole()).as("役職")
            .isEqualTo(ROLE.ADMIN);
    }

    /***
     * 更新者更新
     */
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4})
    @DisplayName("更新者更新テスト")
    public void updateUpdatedBy(int idNum){
        //データ取得
        Optional<User> user = repository.findById(idNum);
        //nullチェック
        assertThat(user).as("個別取得")
            .isPresent();
        //データを取得する
        String expects = "updateUser" + idNum;
        user.get().changeUpdatedBy(expects);
        //保存する
        repository.save(user.get());
        //再取得
        Optional<User> updateUser = repository.findById(idNum);
        assertThat(updateUser.get()).as("個別取得")
            .isNotNull();
        //期待値と同じだったら正常
        assertThat(updateUser.get().getUpdatedBy()).as("更新者")
            .isEqualTo(expects);
    }

    /***
     * 更新日更新
     */
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4})
    @DisplayName("更新者更新テスト")
    public void updateUpdatedAt(int idNum){
        //データ取得
        Optional<User> user = repository.findById(idNum);
        //nullチェック
        assertThat(user).as("個別取得")
            .isPresent();
        //データを取得する
        LocalDateTime expects = LocalDateTime.of(2025, 1 + idNum, 10, 00, 00, 00, 00000);
        user.get().changeUpdatedAt(expects);
        //保存する
        repository.save(user.get());
        //再取得
        Optional<User> updateUser = repository.findById(idNum);
        assertThat(updateUser.get()).as("個別取得")
            .isNotNull();
        //期待値と同じだったら正常
        assertThat(updateUser.get().getUpdatedAt()).as("更新日")
            .isEqualTo(expects);
    }

    /***
     * 削除フラグ更新
     */
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4})
    @DisplayName("削除フラグ更新テスト")
    public void updateDeleteFlgTrue(int idNum){
        //データ取得
        Optional<User> user = repository.findById(idNum);
        //nullチェック
        assertThat(user).as("個別取得")
            .isPresent();
        //削除フラグをtrueに変更
        user.get().deleteFlgDelete();
        //保存する
        repository.save(user.get());
        //再取得
        Optional<User> updateUserTrue = repository.findById(idNum);
        assertThat(updateUserTrue.get()).as("個別取得")
            .isNotNull();
        //期待値と同じだったら正常
        assertThat(updateUserTrue.get().isDeleteFlg()).as("削除フラグ(True)")
            .isEqualTo(Boolean.TRUE);

        //削除フラグをfalseに変更
        user.get().deleteFlgRestore();
        repository.save(user.get());
        Optional<User> updateUserFalse = repository.findById(idNum);
        assertThat(updateUserFalse).as("個別取得")
            .isPresent();
        assertThat(updateUserFalse.get().isDeleteFlg()).as("削除フラグ(false)")
            .isEqualTo(Boolean.FALSE);

    }

    /***
     * 新規登録テスト
     */
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4})
    @DisplayName("新規登録テスト")
    public void insertUser(int num){
        //データ設定
        String userName = "insertUser" + num;
        String email = "insertMail@test.com" + num;
        String createdBy = "createdBy" + num;
        User user = new User(userName, email, createdBy, ROLE.USER, false);
        //保存する
        repository.save(user);
        //再取得
        Optional<User> insertUser = repository.findById(9 + num);
        assertThat(insertUser).as("個別取得")
            .isPresent();
        //期待値と同じだったら正常
        assertThat(user.getId()).as("id")
            .isEqualTo(9 + num);
        assertThat(user.getName()).as("name")
            .isEqualTo(userName);
        assertThat(user.getEmail()).as("email")
            .isEqualTo(email);
        assertThat(user.getRole()).as("role")
            .isEqualTo(ROLE.USER);
        assertThat(user.getCreatedBy()).as("createdBy")
            .isEqualTo(createdBy);
        assertThat(user.getCreatedAt()).as("createdAt")
            .isEqualTo(user.getCreatedAt());
        assertThat(user.isDeleteFlg()).as("deleteFlg")
            .isEqualTo(Boolean.FALSE);
    }
}
