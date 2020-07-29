INSERT INTO merchant (merchant_code,merchant_name)
VALUES
('M-0001','Metabank'),
('M-0002','Adidas America Inc.'),
('M-0003','Staples Inc.'),
('M-0004','Starbucks Inc.'),
('M-0005','Subway'),
('M-0006','Panera Bread LLC.');

INSERT INTO  card(gc_uuid,gc_type_code,gc_original_value,gc_issue_data,gc_expiry_date,customer_id)
VALUES
('327aaa82-abfb-4747-a1d1-8b42438119b3','G-0001',25,'2020-01-01','2020-07-01','76809bcc-0e1f-4b44-8119-8a795b103678'),
('180f2f90-a1d7-4a2c-9b59-f5bc8adfca17','G-0002',50,'2020-01-01','2020-07-01','d31bd3d9-8655-4081-81b5-30e3fc937f0f'),
('5a31e817-f0c5-423c-ab12-7a74a678d7a6','G-0003',25,'2020-04-01','2020-10-01','6a684bf5-ef9c-4792-be1f-c1748bdc2b52'),
('54826d0b-cd56-4e11-83a8-5c384c16c2b8','G-0004',100,'2020-01-01','2020-07-01','adc7218c-9ef3-4c35-bedf-43f269bc3438'),
('287e115f-9a16-4f70-9eec-edcac7e19116','G-0005',25,'2020-01-01','2020-07-01','6be054b9-4199-48f2-9fee-fcb9ce5b070c'),
('2e1be9b5-369c-4d53-a583-585c308c6148','G-0006',20,'2020-03-01','2020-09-01','87dd97b1-ba79-41c2-a053-ca94c8e4446f'),
('2e1be9b5-369c-4d53-a583-585c308c6149','G-0007',50,'2020-01-01','2020-07-01','76809bcc-0e1f-4b44-8119-8a795b103678'),
('2e1be9b5-369c-4d53-a583-585c308c6199','G-0007',50,'2020-05-01','2020-11-01','76809bcc-0e1f-4b44-8119-8a795b103678'),
('2e1be9b5-369c-4d53-a583-585c308c7120','G-0007',50,'2019-05-01','2019-11-01','76809bcc-0e1f-4b44-8119-8a795b103678'),
('327aaa82-abfb-4747-a1d1-8b4243811234','G-0001',25,'2019-02-01','2019-08-01','76809bcc-0e1f-4b44-8119-8a795b101234'),
('327aaa82-abfb-4747-a1d1-8b4243811235','G-0001',50,'2019-03-01','2019-09-01','76809bcc-0e1f-4b44-8119-8a795b101234'),
('327aaa82-abfb-4747-a1d1-8b4243811236','G-0001',75,'2019-04-01','2019-10-01','76809bcc-0e1f-4b44-8119-8a795b105624');

INSERT INTO  card_type(gc_type_code,gc_name,gc_business_model,gc_merchant_code,gc_validity_days,gc_category,gc_reloadable,gc_funds_expire)
VALUES
('G-0001','Happy Student Gift Card','open-close-loop','M-0001',180,'store-gift-card',FALSE,FALSE),
('G-0002','Adidas Gift Card','close-loop','M-0002',180,'store-gift-card',FALSE,FALSE),
('G-0003','Staples Gift Card','close-loop','M-0003',180,'store-gift-card',FALSE,FALSE),
('G-0004','Starbucks Gift Card','close-loop','M-0004',180,'store-gift-card',FALSE,FALSE),
('G-0005','Subway Gift Card','close-loop','M-0005',180,'store-gift-card',FALSE,FALSE),
('G-0006','Panera Bread Gift Card','close-loop','M-0006',180,'store-gift-card',FALSE,FALSE),
('G-0007','Happy You eGift Card','open-close-loop','M-0001',180,'specialty-retailer-gift-cards',FALSE,FALSE);

INSERT INTO  customer(customer_id,customer_name,customer_city,customer_zip_code,customer_dob,customer_registration_date,customer_email,customer_phone)
VALUES
('76809bcc-0e1f-4b44-8119-8a795b103678','John Doe','San Antonio','78240','1978-06-21','2020-01-01','john.doe@email.com','210-917-2260'),
('d31bd3d9-8655-4081-81b5-30e3fc937f0f','Jane Doe','Lexington','78240','1980-01-01','2020-01-01','john.doe@email.com','210-917-2260'),
('6a684bf5-ef9c-4792-be1f-c1748bdc2b52','Max Smith','New York','78240','1981-01-01','2020-01-01','john.doe@email.com','210-917-2260'),
('adc7218c-9ef3-4c35-bedf-43f269bc3438','James Bond','Bethesda','78240','1960-01-01','2020-01-01','john.doe@email.com','210-917-2260'),
('6be054b9-4199-48f2-9fee-fcb9ce5b070c','Max Planck','Gaithersburg','78240','2001-01-01','2020-01-01','john.doe@email.com','210-917-2260'),
('87dd97b1-ba79-41c2-a053-ca94c8e4446f','Nicola tesla','Phoenix','78240','1975-06-22','2020-01-01','john.doe@email.com','210-917-2260');

INSERT INTO  transaction(gc_uuid,tx_id,gc_type_code,merchant_code,tx_type,tx_value,tx_date)
VALUES
('327aaa82-abfb-4747-a1d1-8b42438119b3','bbcb881d-b76b-468d-a989-03d418def3a6','G-0001','M-0001','PURCHASE',25,'2020-01-01'),
('180f2f90-a1d7-4a2c-9b59-f5bc8adfca17','c67fd9f5-f175-41e1-b0a4-c4e4866b5fe5','G-0002','M-0002','PURCHASE',50,'2020-01-01'),
('5a31e817-f0c5-423c-ab12-7a74a678d7a6','5c8cd7d2-bfe2-48e0-97a2-01cf2611330c','G-0003','M-0003','PURCHASE',25,'2020-04-01'),
('54826d0b-cd56-4e11-83a8-5c384c16c2b8','e3769585-75ef-4e16-8c1b-0b9c15556564','G-0004','M-0004','PURCHASE',100,'2020-01-01'),
('287e115f-9a16-4f70-9eec-edcac7e19116','2fd396ae-2867-4dd8-9ce0-50fa13ad0b25','G-0005','M-0005','PURCHASE',25,'2020-01-01'),
('2e1be9b5-369c-4d53-a583-585c308c6148','ef1e471e-672c-4038-b755-bc7a8a435a85','G-0006','M-0006','PURCHASE',20,'2020-03-01'),
('327aaa82-abfb-4747-a1d1-8b42438119b3','0ef5233c-8acd-4d34-b33e-7eca9580d75c','G-0001','M-0001','REDEMPTION',12.5,'2020-01-20'),
('180f2f90-a1d7-4a2c-9b59-f5bc8adfca17','4a1b92ba-8ed1-4a0f-93dc-dce743c4b29c','G-0002','M-0002','REDEMPTION',29.99,'2020-01-20'),
('5a31e817-f0c5-423c-ab12-7a74a678d7a6','841b4f2f-1bf2-4aab-bf0d-b7c20c97ef81','G-0003','M-0003','REDEMPTION',13.99,'2020-05-20'),
('54826d0b-cd56-4e11-83a8-5c384c16c2b8','4f991993-6b72-47a5-8c2f-d0f88203e4da','G-0004','M-0004','REDEMPTION',75,'2020-01-20'),
('287e115f-9a16-4f70-9eec-edcac7e19116','6a00d29b-65d8-4293-a6ac-7f10def0959a','G-0005','M-0005','REDEMPTION',10,'2020-01-20'),
('2e1be9b5-369c-4d53-a583-585c308c6148','aa1079c9-cc61-4611-892a-f740e6418191','G-0006','M-0006','REDEMPTION',9.99,'2020-06-20'),
('2e1be9b5-369c-4d53-a583-585c308c6149','aa1079c9-cc61-4611-892a-f740e6418192','G-0007','M-0001','PURCHASE',75,'2020-01-01'),
('2e1be9b5-369c-4d53-a583-585c308c6149','aa1079c9-cc61-4611-892a-f740e6418193','G-0007','M-0001','REDEMPTION',42.99,'2020-03-25'),
('2e1be9b5-369c-4d53-a583-585c308c6149','aa1079c9-cc61-4611-892a-f740e6415432','G-0007','M-0001','REDEMPTION',2.01,'2020-03-26'),
('2e1be9b5-369c-4d53-a583-585c308c6199','ba1079c9-cc61-4611-892a-f740e6418126','G-0007','M-0001','PURCHASE',50,'2020-05-01'),
('2e1be9b5-369c-4d53-a583-585c308c6199','ba1079c9-cc61-4611-892a-f740e6418127','G-0007','M-0001','REDEMPTION',22,'2020-05-01'),
('2e1be9b5-369c-4d53-a583-585c308c7120','ba1079c9-cc61-4611-892a-f740e6418496','G-0007','M-0001','PURCHASE',50,'2019-05-01'),
('2e1be9b5-369c-4d53-a583-585c308c7120','ba1079c9-cc61-4611-892a-f740e6418864','G-0007','M-0001','REDEMPTION',27,'2019-06-01'),
('327aaa82-abfb-4747-a1d1-8b4243811234','acd079c9-cc61-4611-892a-f740e6418123','G-0001','M-0001','PURCHASE',25,'2019-02-01'),
('327aaa82-abfb-4747-a1d1-8b4243811235','acd079c9-cc61-4611-892a-f740e6418124','G-0001','M-0001','PURCHASE',50,'2019-03-01'),
('327aaa82-abfb-4747-a1d1-8b4243811236','acd079c9-cc61-4611-892a-f740e6418125','G-0001','M-0001','PURCHASE',75,'2019-04-01'),
('327aaa82-abfb-4747-a1d1-8b4243811234','acd079c9-cc61-4611-892a-f740e6418223','G-0001','M-0001','REDEMPTION',10,'2019-03-01'),
('327aaa82-abfb-4747-a1d1-8b4243811235','acd079c9-cc61-4611-892a-f740e6418224','G-0001','M-0001','REDEMPTION',12,'2019-04-01'),
('327aaa82-abfb-4747-a1d1-8b4243811236','acd079c9-cc61-4611-892a-f740e6418225','G-0001','M-0001','REDEMPTION',22,'2019-05-01');


INSERT INTO  historical_breakage_rate(gc_name,gc_type_code,ytd_purchase_value,ytd_breakage,breakage_rate,as_of_year)
VALUES
('Happy Student Gift Card','G-0001',150.0,106.00,70.66,2019),
('Happy You eGift Card','G-0007',50.0,23.00,46.00,2019);