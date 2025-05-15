INSERT INTO articles (user_id, title, content, release_flg, created_at, created_by, delete_flg)
VALUES
('1', 'testTitle1', 'content1', '0', '2025-01-01 00:00:00.00000', 'testUser1', '0'),
('2', 'testTitle2', 'content2', '0', '2025-01-05 00:00:00.00000', 'testUser2', '0'),
('3', 'testTitle3', 'content3', '0', '2025-01-02 00:00:00.00000', 'testUser3', '0'),
('4', 'testTitle4', 'content4', '0', '2025-01-04 00:00:00.00000', 'testUser4', '0'),
('5', 'testTitle5', 'content5', '0', '2025-01-04 00:00:00.00000', 'testUser5', '0'),
('6', 'testTitle6', 'content6', '0', '2025-01-04 12:10:10.00000', 'testUser6', '0'),
('7', 'testTitle7', 'content7', '0', '2025-01-03 00:00:00.00000', 'testUser7', '0'),
('8', 'testTitle8', 'content8', '0', '2025-01-03 00:00:00.00000', 'testUser8', '0'),
('9', 'testTitle9', 'content9', '0', '2025-01-03 00:00:00.00000', 'testUser9', '0')
;

INSERT INTO tags (tag_id, tagname, created_by, created_at, delete_flg)
VALUES
('1', 'tagTestName1', 'tagCreatedBy1', '2025-01-01 00:00:00.00000', '0'),
('2', 'tagTestName2', 'tagCreatedBy2', '2025-01-05 00:00:00.00000', '0'),
('3', 'tagTestName3', 'tagCreatedBy3', '2025-01-02 00:00:00.00000', '0'),
('4', 'tagTestName4', 'tagCreatedBy4', '2025-01-04 00:00:00.00000', '0'),
('5', 'tagTestName5', 'tagCreatedBy5', '2025-01-04 00:00:00.00000', '0'),
('6', 'tagTestName6', 'tagCreatedBy6', '2025-01-04 12:10:10.00000', '0'),
('7', 'tagTestName7', 'tagCreatedBy7', '2025-01-03 00:00:00.00000', '0'),
('8', 'tagTestName8', 'tagCreatedBy8', '2025-01-03 00:00:00.00000', '0'),
('9', 'tagTestName9', 'tagCreatedBy9', '2025-01-03 00:00:00.00000', '0')
;

INSERT INTO users (id, name, email, role, created_by, created_at, delete_flg)
VALUES
('1', 'userName1', 'email@example.com1', 'USER', 'UserCreatedBy1', '2025-01-01 00:00:00.00000', '0'),
('2', 'userName2', 'email@example.com2', 'USER', 'UserCreatedBy2', '2025-01-02 00:00:00.00000', '0'),
('3', 'userName3', 'email@example.com3', 'USER', 'UserCreatedBy3', '2025-01-03 00:00:00.00000', '0'),
('4', 'userName4', 'email@example.com4', 'USER', 'UserCreatedBy4', '2025-01-04 00:00:00.00000', '0'),
('5', 'userName5', 'email@example.com5', 'USER', 'UserCreatedBy5', '2025-01-05 00:00:00.00000', '0'),
('6', 'userName6', 'email@example.com6', 'USER', 'UserCreatedBy6', '2025-01-06 00:00:00.00000', '0'),
('7', 'userName7', 'email@example.com7', 'USER', 'UserCreatedBy7', '2025-01-07 00:00:00.00000', '0'),
('8', 'userName8', 'email@example.com8', 'USER', 'UserCreatedBy8', '2025-01-08 00:00:00.00000', '0'),
('9', 'userName9', 'email@example.com9', 'ADMIN', 'UserCreatedBy9', '2025-01-09 00:00:00.00000', '0')
;