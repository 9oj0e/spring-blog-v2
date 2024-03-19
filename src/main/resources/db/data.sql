-- user_tb
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('ssar', '1234', 'ssar@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('cos', '1234', 'ssar@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('love', '1234', 'ssar@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('bori', '1234', 'ssar@nate.com', now());


-- board_tb
INSERT INTO board_tb (user_id, title, content, created_at)
VALUES (1, '제목1', '내용1', now());
INSERT INTO board_tb (user_id, title, content, created_at)
VALUES (2, '제목2', '내용2', now());
INSERT INTO board_tb (user_id, title, content, created_at)
VALUES (3, '제목3', '내용3', now());
INSERT INTO board_tb (user_id, title, content, created_at)
VALUES (4, '제목4', '내용4', now());
INSERT INTO board_tb (user_id, title, content, created_at)
VALUES (1, '제목5', '내용5', now());
INSERT INTO board_tb (user_id, title, content, created_at)
VALUES (2, '제목6', '내용6', now());


-- reply_tb
INSERT INTO reply_tb(comment, board_id, user_id, created_at)
VALUES ('댓글1', 4, 1, now());
INSERT INTO reply_tb(comment, board_id, user_id, created_at)
VALUES ('댓글2', 4, 1, now());
INSERT INTO reply_tb(comment, board_id, user_id, created_at)
VALUES ('댓글3', 4, 2, now());
INSERT INTO reply_tb(comment, board_id, user_id, created_at)
VALUES ('댓글4', 3, 2, now());