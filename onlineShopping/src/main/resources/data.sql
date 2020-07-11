--Billing Address
INSERT INTO BILLING_ADDRESS(CITY,Country,State,Street,Zip_Code)
Values('Giza','Egypt','GA','Street 1','11111'),
      ('Fairfield','USA','IO','Street 2','11111');
--Shipping Address
INSERT INTO SHIPPING_ADDRESS (CITY,Country,State,Street,Zip_Code)
Values('IOWA','USA','MM','Street','11111');
--Card Payment
INSERT INTO CARD_PAYMENT (CARD_NUMBER,CVV,EXPIRY_DATE,NAME_ON_CARD)
Values ('1223','222','2010-01-01','MOHAMED SALEH');
--Categories
INSERT INTO PRODUCT_CATEGORY(NAME)
Values ('Clothing'),('Books'),('Movies,Music'),('Computers'),('Smart Home');
--Sellers
INSERT INTO SELLER (Date_Of_Birth,Email,First_Name,Last_Name,Password,Phone_Number,Role,User_Name,Approved)
Values('2010-01-01 10:00:00+01','Mega@hotmail.com','M','S','12345','122123',1,'Seller1',2),
      ('2010-01-01 10:00:00+01','Mega@hotmail.com','M','S','12345','122123',1,'Seller2',2),
      ('2010-01-01 10:00:00+01','Mega@hotmail.com','M','S','12345','122123',1,'Seller3',2);

--Products
INSERT INTO Product(DESCRIPTION,NAME,PHOTO,PRICE,STOCK_QUANTITY,SELLER_USER_ID)
Values('Desc1','Product1',null,100,12,1),
       ('Desc2','Product2',null,80,10,1),
       ('Desc3','Product3',null,100,12,1),
       ('Desc4','Product4',null,100,20,2),
       ('Desc5','Product5',null,85,20,2);

INSERT INTO PRODUCT_CATEGORY_PRODUCTS(PRODUCT_CATEGORY_ID,PRODUCTS_ID)
VALUES ( 1,1 ),( 1,2 ),(2,3),(2,4),(3,5);
--Buyer
INSERT INTO BUYER(Date_Of_Birth,Email,First_Name,Last_Name,Password,Phone_Number,Role,User_Name,Points,BILLING_ADDRESS_id,Shipping_ADDRESS_id,Card_Payment_Id)
Values('2010-01-01 10:00:00+01','Mega@hotmail.com','M','S','12345','122123',2,'buyer',0,1,1,1);
--Card Payment + Buyer
