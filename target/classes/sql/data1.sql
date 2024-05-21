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


insert into members (name, email_id, email_domain, password, phone, status)
values ("admin" , "admin", "odysseyfrontiers.com", "1234" ,"010-1234-5678", 'ADMIN');

insert into members (name, email_id, email_domain,password, phone, status)
values ("시현" , "123", "odysseyfrontiers.com" , "1234" , "010-1234-5678", 'ACTIVE');

insert into members (name, email_id,email_domain, password, phone, status)
values ("소연" , "1234","odysseyfrontiers.com" ,  "1234" , "010-1234-5678", 'ACTIVE');

insert into members (name, email_id,email_domain, password, phone, status)
values ("ssafy" , "ssafy","odysseyfrontiers.com" , "1234" , "010-1234-5678", 'ACTIVE');

insert into members (name, email_id,email_domain, password, phone, status)
values ("alice" , "alice1234", "naver.com" , "1234" , "010-1234-5678", 'ACTIVE');


insert into board (member_id, subject, content, type)
values (1, "관리자 입니다.", "관리자 입니다.", 'community');

insert into board (member_id, subject, content, type)
values (1, "관리자 입니다.", "관리자 입니다.", 'notice');

insert into board (member_id, subject, content, type)
values (2, "안녕하세요!", "안녕하세요!", 'community');

insert into board (member_id, subject, content, type)
values (3, "안녕하세요  ", "안녕하세요 안녕하세요", 'community');

insert into board (member_id, subject, content, type)
values (1, "abc", "abc", 'community');