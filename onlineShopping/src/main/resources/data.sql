INSERT INTO SELLER (Date_Of_Birth,Email,First_Name,Last_Name,Password,Phone_Number,Role,User_Name,Approved)
VALUES('2010-01-01 10:00:00+01','Mega@hotmail.com','China','Style','12345','122123',1,'Seller1',2),
      ('2010-01-01 10:00:00+01','Mo@hotmail.com','Mobako','','12345','122123',1,'Seller2',2),
      ('2010-01-01 10:00:00+01','Mohamed@hotmail.com','Nike','Inc','12345','122123',1,'Seller3',2);

INSERT INTO PLACE_ORDER (ORDER_NUMBER,STATUS,TOTAL_PRICE,SELLER_USER_ID)
VALUES (123, 4, 123, 2), (456, 4, 456, 2), (789, 4, 789, 1);

INSERT INTO SELLER_ORDERS (SELLER_USER_ID, ORDERS_ID)VALUES(2, 1), (2, 2), (1, 3);