--Shopping Cart
INSERT INTO SHOPPING_CART (Total_Price) 
Values (800.00),(0.00),(0.00);
--Billing Address
INSERT INTO BILLING_ADDRESS(CITY,Country,State,Street,Zip_Code)
Values('Giza','Egypt','GA','Street 1','41526'),
      ('City 1','USA','IO','Street 2','96857'),
      ('City 2','USA','IO','Street 2','14256'),
      ('City 3','USA','IO','Street 2','12365');
--Shipping Address
INSERT INTO SHIPPING_ADDRESS (CITY,Country,State,Street,Zip_Code)
Values('IOWA','USA','MM','Street','11111'),
      ('City 2','USA','MM','Street','14852'),
      ('City 3','USA','MM','Street','14852');
--Card Payment
INSERT INTO CARD_PAYMENT (CARD_NUMBER,CVV,EXPIRY_DATE,NAME_ON_CARD)
Values ('12142569875','124','2010-01-01','MOHAMED SALEH'),
       ('12345698765','987','2023-01-01','Ahmed Mostafa'),
       ('12345674765','980','2025-01-01','Mostafa Abdel');
--Categories
INSERT INTO PRODUCT_CATEGORY(NAME)
Values ('Clothing'),
       ('Books'),
       ('Movies,Music'),
       ('Computers'),
       ('Smart Home');
--Sellers
INSERT INTO SELLER (Date_Of_Birth,Email,First_Name,Last_Name,Password,Phone_Number,Role,User_Name,Approved)
Values('2010-01-01 10:00:00+01','Mega@hotmail.com','China','Style','12345','6418192921',1,'Seller1',2),
      ('2010-01-01 10:00:00+01','Mo@hotmail.com','Mobako','','12345','6418192921',1,'Seller2',2),
      ('2010-01-01 10:00:00+01','Mohamed@hotmail.com','Nike','Inc','12345','6418192921',1,'Seller3',2);

--Products
INSERT INTO Product(DESCRIPTION,NAME,PHOTO,PRICE,STOCK_QUANTITY,SELLER_USER_ID,PRODUCT_CATEGORY_ID )
Values('Desc1','Product1',null,100,12,1,1),
       ('Desc2','Product2',null,80,10,1,1),
       ('Desc3','Product3',null,100,12,1,2),
       ('Desc4','Product4',null,100,20,2,2),
       ('Desc5','Product5',null,85,20,2,3);

INSERT INTO PRODUCT_CATEGORY_PRODUCTS(PRODUCT_CATEGORY_ID,PRODUCTS_ID)
VALUES ( 1,1 ),( 1,2 ),(2,3),(2,4),(3,5);
--Buyer
INSERT INTO BUYER(Date_Of_Birth,Email,First_Name,Last_Name,Password,Phone_Number,Role,User_Name,Points,BILLING_ADDRESS_id,Shipping_ADDRESS_id,Card_Payment_Id,shopping_cart_id)
Values('2011-01-01 10:00:00+01','Mohamedsaleh1984@hotmail.com','Mostafa','Salem','12345','6418192921',2,'buyer1',10,1,1,1,1),
      ('1982-01-01 10:00:00+01','Mega_unknown@hotmail.com','Mohamed','Saleh','12345','6418192921',2,'buyer2',30,2,2,2,2),
      ('1982-01-01 10:00:00+01','Dummy@hotmail.com','Mohamed','Saleh','12345','6418192921',2,'buyer3',30,2,3,3,3);
--Cart Items
INSERT INTO CART_ITEM(quantity, price, product_id)
Values (1,100.00,1),(3,100.00,4),(5,80.00,2);
--Shopping Cart Items
INSERT INTO SHOPPING_CART_CART_ITEMS(shopping_cart_id, cart_items_id, cart_items_key)
Values (1, 1, 1),(1, 2, 3),(1, 3, 2);
