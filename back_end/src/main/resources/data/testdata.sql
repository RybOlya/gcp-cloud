USE solar_system;
INSERT INTO city (id, city)
VALUES (1, 'Mykolaiv'),
       (2, 'Drohobych'),
       (3, 'Odesa'),
       (4, 'Poltava'),
       (5, 'Kyiv');
 insert into ip_address (id,ip_address)
 values (1,'192.169.0.101'),
        (2,'192.169.0.102'),
        (3,'192.169.0.103'),
        (4,'192.169.0.104'),
		(5,'192.169.0.105'),
        (6,'192.169.0.106'),
        (7,'192.169.0.107'),
        (8,'192.169.0.108'),
        (9,'192.169.0.109'),
		(10,'192.169.0.110');
INSERT INTO client (surname, name, email, city_id, street, apartment)
VALUES ('Namyniuk', 'Mykola', 'namyniukmykola@gmail.com', 3, 'Zernova', '61/2'),
       ('Omelko', 'Vicktoria', 'omelko@gmail.com', 4, 'Lypova', '11/14a'),
       ('Kivsh', 'Valeria', 'kivshvaleria@gmail.com', 1, 'Poliova', '76'),
       ('Larena', 'Marta', 'larenamarta@gmail.com', 5, 'Svobody', '65'),
       ('Nekryva', 'Maksym', 'nekryvam@gmail.com', 2, 'Dzherelna', '56'),
       ('Stepovyi', 'Victor', 'stepovyi.victor@gmail.com', 4, 'Zelena', '12'),
       ('Ostapiv', 'Maria', 'ostapiv.maria@gmail.com', 2, 'Kvitkova', '32'),
       ('Symonenko', 'Kateryna', 'symonenko.kateryna@gmail.com', 5, 'Nova', '76');

insert into solar_system(id,feed_in_tariff,energy_sold,city_id,street,apartment)
values(1,0.34,54,1,'Zelena','65'),
	  (2,0.56,120,2,'Kvitkova','12'),
	  (3,0.12,344,3,'Dzherelna','15'),
	  (4,0.43,54,3,'Svobody','35'),
	  (5,0.33,34,4,'Poliova','85');

insert into solar_battery(id,model,ip_address_id,capacity,operating_temperature,solar_system_id)
values (1,'2001',6, 12.00,25,2),
	   (2,'2002',7, 48.00,12,1),
	   (3,'2003',8, 13.00,25,3),
	   (4,'2004',9, 48.00,12,4),
	   (5,'2005',10, 76.00,25,5);


INSERT INTO solar_panel
VALUES (1,1,'1001','monocrystalline',2,20),
	 (2,2,'1002','polycrystalline',4,45),
	 (3,3,'1003','monocrystalline',5,35),
	 (4,4,'1004','polycrystalline',2,45),
	 (5,5,'1005','monocrystalline',1,46);

 insert into client_solar_system
 values (1,2),
		(2,2),
        (1,5),
        (5,1),
        (4,5),
        (6,1),
        (7,5);