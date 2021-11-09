use bookstore;

-- INSERT INTO permission (code, name) VALUES ('ADMIN', 'Access Admin Page');
-- INSERT INTO permission (code, name) VALUES ('READ', 'Read');
-- INSERT INTO permission (code, name) VALUES ('WRITE', 'Create and modify');
-- INSERT INTO permission (code, name) VALUES ('DELETE', 'Delete');

INSERT INTO role (code, name) VALUES ('ADMIN', 'Quản trị viên');
INSERT INTO role (code, name) VALUES ('CUSTOMER', 'Khách hàng');

-- ADMIN -- PERMISSION --
-- ADMIN - ADMIN -- can access admin page
-- ADMIN - READ -- can view data, statisitc
-- ADMIN - WRITE -- can create and modify data
-- ADMIN - DELETE -- can detelate

-- INSERT INTO role_permission (roleid, permissionid) VALUES (1,1);
-- INSERT INTO role_permission (roleid, permissionid) VALUES (1,2);
-- INSERT INTO role_permission (roleid, permissionid) VALUES (1,3);
-- INSERT INTO role_permission (roleid, permissionid) VALUES (1,4);

INSERT INTO user (username, fullname, password, email, tell, address, status)
VALUES (
	'admin', 
    'Nguyễn Trung Tính',
    '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', -- 123456
    'tinhdeptrai@gmail.com',
    '1234567890',
    'Can Tho',
    1
);
INSERT INTO user (username, fullname, password, email, tell, address, status)
VALUES (
	'nguyenvana', 
    'Nguyễn Văn A',
    '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', -- 123456
    'nguyenvana@gmail.com',
    '1234567890',
    '172 đường 3/2, phường Hưng Lợi, Quận Ninh Kiều, Thành phố Cần Thơ',
    1
);
INSERT INTO user (username, fullname, password, email, tell, address, status)
VALUES (
	'linhnhi', 
    'Linh Khiếp Nhan',
    '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', -- 123456
    'linhnhi@gmail.com',
    '1234567890',
    '554 Hà Huy Giáp, Thạnh Lộc, Quận 12, Thành phố Hồ Chí Minh',
    1
);

-- USER - ROLE --
INSERT INTO user_role (userid, roleid) VALUES (1, 1);
INSERT INTO user_role (userid, roleid) VALUES (2, 2);
INSERT INTO user_role (userid, roleid) VALUES (3, 2);

-- Category --
INSERT INTO category (code, name) VALUE ("IT", "Công Nghệ Thông Tin");
INSERT INTO category (code, name) VALUE ("TOAN-HOC", "Toán Học");
INSERT INTO category (code, name) VALUE ("VAT-LY", "Vật Lý");
INSERT INTO category (code, name) VALUE ("TRUYEN-NGAN", "Truyện Ngắn");

INSERT INTO publisher (code, name) VALUE ("DHCT", "Nhà xuất bản Đại Học Cần Thơ");
INSERT INTO publisher (code, name) VALUE ("BK-HN", "Nhà xuất bản Bách Khoa Hà Nội");
INSERT INTO publisher (code, name) VALUE ("GDVN", "Nhà xuất bản Giáo Dục Việt Nam");


