INSERT INTO ACCOMMODATION (name, description, latitude, longitude, type, is_free_parking, parking_type,
                           location_guide_text, tel)
VALUES ('서울 호텔', '서울 중심에 위치한 5성급 호텔로, 럭셔리한 객실과 다양한 편의 시설을 제공합니다.', 37.5665, 126.9780, 'HOTEL', 1, 'FIELD',
        '서울역에서 도보 5분 거리', '010-2323-1234');
INSERT INTO ACCOMMODATION (name, description, latitude, longitude, type, is_free_parking, parking_type,
                           location_guide_text, tel)
VALUES ('부산 모텔', '부산 해운대 근처에 위치한 모텔로, 합리적인 가격에 숙박을 제공합니다.', 35.1587, 129.1603, 'MOTEL', 1, 'MACHINE',
        '해운대역에서 도보 10분 거리', '010-1212-3232');


INSERT INTO IMAGE (image_type, path, acmd_no)
VALUES ('MAIN', 'images/accommodation1/main.jpg', 1);
INSERT INTO IMAGE (image_type, path, acmd_no)
VALUES ('THUMBNAIL', 'images/accommodation1/thumbnail.jpg', 1);
INSERT INTO IMAGE (image_type, path, acmd_no)
VALUES ('DETAIL', 'images/accommodation1/detail.jpg', 1);
INSERT INTO IMAGE (image_type, path, acmd_no)
VALUES ('LOCATION', 'images/accommodation1/location.jpg', 1);
INSERT INTO IMAGE (image_type, path, acmd_no)
VALUES ('MAIN', 'images/accommodation1/main.jpg', 2);
INSERT INTO IMAGE (image_type, path, acmd_no)
VALUES ('THUMBNAIL', 'images/accommodation1/thumbnail.jpg', 2);
INSERT INTO IMAGE (image_type, path, acmd_no)
VALUES ('DETAIL', 'images/accommodation1/detail.jpg', 2);
INSERT INTO IMAGE (image_type, path, acmd_no)
VALUES ('LOCATION', 'images/accommodation1/location.jpg', 2);
