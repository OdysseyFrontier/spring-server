INSERT INTO `content_type` (content_type_id, type_name) VALUES
                                                            (0, '모든 관광지 유형'),
                                                            (12, '관광지'),
                                                            (14, '문화시설'),
                                                            (15, '축제 / 공연 / 행사'),
                                                            (25, '여행코스'),
                                                            (28, '레포츠'),
                                                            (32, '숙박'),
                                                            (38, '쇼핑'),
                                                            (39, '음식점');


insert into members (name, email_id, password, phone, status)
values ("admin" , "admin", "1234" , "010-1234-5678", 'ADMIN');

insert into members (name, email_id, password, phone, status)
values ("시현" , "1234", "1234" , "010-1234-5678", 'ACTIVE');

insert into members (name, email_id, password, phone, status)
values ("소연" , "1234", "1234" , "010-1234-5678", 'ACTIVE');

insert into members (name, email_id, password, phone, status)
values ("ssafy" , "ssafy", "1234" , "010-1234-5678", 'ACTIVE');


insert into board (member_id, subject, content, type)
values (1, "abc", "abc", 'community');

insert into board (member_id, subject, content, type)
values (2, "안녕하세요!", "안녕하세요!", 'community');

insert into board (member_id, subject, content, type)
values (3, "안녕하세요  ", "안녕하세요 안녕하세요", 'community');

insert into board (member_id, subject, content, type)
values (1, "abc", "abc", 'community');