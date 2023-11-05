CREATE SCHEMA IF NOT EXISTS solar_system;
USE solar_system;


DROP TABLE IF EXISTS solar_system_client;

DROP TABLE IF EXISTS solar_panel;
DROP TABLE IF EXISTS solar_battery;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS solar_system;
DROP TABLE IF EXISTS ip_address;
DROP TABLE IF EXISTS city;

CREATE TABLE IF NOT EXISTS country (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    CONSTRAINT country_pk PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS city
(
    id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(25) NOT NULL,
    country_id int NOT NULL
);
CREATE TABLE  IF NOT EXISTS ip_address (
    ip_address varchar(15) NOT NULL
    CONSTRAINT ip_address_pk PRIMARY KEY (ip_address)
);

CREATE TABLE  IF NOT EXISTS solar_system (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    feed_in_tariff double(8,2) NOT NULL,
    energy_sold int NOT NULL,
    city_id   INT         NULL,
    street    VARCHAR(30) NULL,
    apartment VARCHAR(10) NULL,
    CONSTRAINT fk_solar_system_city1
        FOREIGN KEY (city_id)
            REFERENCES city (id)
);
CREATE TABLE IF NOT EXISTS solar_panel
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ip_address INT NOT NULL,
    model    VARCHAR(45) NOT NULL,
    type VARCHAR(35) NOT NULL,
	solar_system_id INT  NOT NULL,
    current_angle float NOT NULL,
      CONSTRAINT fk_solar_panel_solar_system1
        FOREIGN KEY (solar_system_id)
            REFERENCES solar_system (id)
);

CREATE TABLE IF NOT EXISTS solar_battery
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    model    VARCHAR(45) NOT NULL,
    ip_address INT NOT NULL,
    capacity double(5,2) NOT NULL,
    operating_temperature int NOT NULL DEFAULT 20 COMMENT '°C',
	solar_system_id INT  NOT NULL,
      CONSTRAINT fk_solar_battery_solar_system1
        FOREIGN KEY (solar_system_id)
            REFERENCES solar_system (id)
);


CREATE TABLE IF NOT EXISTS client
(
    id        INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    surname   VARCHAR(25) NOT NULL,
    name      VARCHAR(25) NOT NULL,
    email     VARCHAR(45) NULL,
    city_id   INT         NULL,
    street    VARCHAR(30) NULL,
    apartment VARCHAR(10) NULL,
    CONSTRAINT fk_client_city1
        FOREIGN KEY (city_id)
            REFERENCES city (id)
);

CREATE TABLE IF NOT EXISTS solar_system_client
(
	id int not null auto_increment,
    solar_system_id int not null,
    client_id int not null,
    constraint solar_system_client_pk primary key (id)
);
ALTER TABLE city ADD CONSTRAINT city_country FOREIGN KEY city_country (country_id)
    REFERENCES country (id);
alter table solar_battery add constraint solar_battery_ip_address foreign key solar_battery_ip_address (ip_address)
    references ip_address (`ip_address`) on update cascade on delete cascade;

alter table solar_panel add constraint solar_panel_ip_address foreign key solar_panel_ip_address (ip_address)
    references ip_address (`id`) on update cascade on delete cascade;

alter table solar_system_client add constraint solar_system_client_client foreign key solar_system_client_client (client_id)
    references client (`id`) on update cascade on delete cascade;

alter table solar_system_client add constraint solar_system_client_solar_system foreign key solar_system_client_solar_system (solar_system_id)
    references solar_system (`id`) on update cascade on delete cascade;

INSERT INTO country (id, name)
VALUES (1, 'Spain'),
       (2, 'Norway'),
       (3, 'Finland'),
       (4, 'Italy'),
       (5, 'Ukraine');

INSERT INTO city (id, city,country_id)
VALUES (1, 'Barcelona',1),
       (2, 'Drohobych',5),
       (3, 'Stavanger',2),
       (4, 'Bari',4),
       (5, 'Tartu',5);
 insert into ip_address
 values ('192.169.0.101'),
        ('192.169.0.102'),
        ('192.169.0.103'),
        ('192.169.0.104'),
		('192.169.0.105'),
        ('192.169.0.106'),
        ('192.169.0.107'),
        ('192.169.0.108'),
        ('192.169.0.109'),
		('192.169.0.110');
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

insert into solar_battery(id,model,ip_address,capacity,operating_temperature,solar_system_id)
values (1,'2001','192.169.0.106', 12.00,25,2),
	   (2,'2002','192.169.0.107', 48.00,12,1),
	   (3,'2003','192.169.0.108', 13.00,25,3),
	   (4,'2004','192.169.0.109', 48.00,12,4),
	   (5,'2005','192.169.0.110', 76.00,25,5);


INSERT INTO solar_panel
VALUES (1,'192.169.0.101','1001','monocrystalline',2,20),
	 (2,'192.169.0.102','1002','polycrystalline',4,45),
	 (3,'192.169.0.103','1003','monocrystalline',5,35),
	 (4,'192.169.0.104','1004','polycrystalline',2,45),
	 (5,'192.169.0.105','1005','monocrystalline',1,46);

insert into solar_system_client(solar_system_id, client_id) values(1,2);
insert into solar_system_client(solar_system_id, client_id) values(2,2);
insert into solar_system_client(solar_system_id, client_id) values(1,5);
insert into solar_system_client(solar_system_id, client_id) values(5,1);
insert into solar_system_client(solar_system_id, client_id) values(4,5);
insert into solar_system_client(solar_system_id, client_id) values(5,2);
insert into solar_system_client(solar_system_id, client_id) values(1,2);