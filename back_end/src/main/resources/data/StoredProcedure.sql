USE solar_system;
DELIMITER //
# Task 2.a
# Забезпечити параметризовану вставку нових значень у довільну таблицю

drop procedure if exists insert_into_ip_address //

create procedure insert_into_ip_address(
	in ip_address_in varchar (50),
	out ip_address_out varchar (50)
)
begin
	insert into ip_address(`ip_address`) values (ip_address_in);
    select last_insert_id() into ip_address_out;
end //
DELIMITER ;

# Task 2.b
# Забезпечити реалізацію зв’язку М:М між 2ма таблицями,
# тобто вставити в стикувальну таблицю відповідну стрічку за реально-існуючими значеннями
# (напр. surname, name) в цих основних таблицях
DROP PROCEDURE IF EXISTS insert_into_solar_system_client;
DELIMITER //
create procedure insert_into_solar_system_client(
	in solar_system_id_in int,
	in client_surname varchar(50),
    out solar_system_client_id int
)
begin
	declare client_id_in int;
    set client_id_in = (select id from client
								where client.surname = client_surname
                                limit 1);
	if (client_id_in is null) then
     signal sqlstate '45000'
        set message_text = 'No clients weth this surname found';
    end if;
	insert into solar_system_client(solar_system_id, client_id)
		values(solar_system_id_in, client_id_in);
select last_insert_id() into solar_system_client_id;
end //
DELIMITER ;

# Task 2.c
# Створити пакет, який вставляє 10 стрічок у довільну таблицю БД у форматі <Noname+№>
# наприклад: Noname5, Noname6, Noname7 і т.д.

drop procedure if exists insert_ten_rows_into_ip_address;
DELIMITER //

create procedure insert_ten_rows_into_ip_address()
begin
	declare counter int;
    set counter = 20;
    loop_label: loop
		if counter = 30 then leave loop_label;
        end if;
        insert into ip_address values(concat("192.168.0.1", counter));
        set counter = counter + 1;
    end loop;
end //

# Task 2.d
# Написати користувацьку функцію, яка буде шукати Max, Min, Sum чи Avg для стовпця довільної таблиці у БД
# Написати процедуру, яка буде у SELECT викликати цю функцію

drop procedure if exists get_max_energy_sold;
create procedure get_max_energy_sold(
	out max_energy integer
)
 begin
	set max_energy = get_max_energy_sold_f();
end //



# Task 2.e
# Використовуючи курсор, забезпечити динамічне створення таблиць з назвами+штамп часу, взятими зі стовпця з довільної таблиці БД,
# з випадковою кількістю стовпців (від 1 до 9). Імена та тип стовпців довільні
DROP PROCEDURE IF EXISTS create_tables_by_city_names //
CREATE PROCEDURE create_tables_by_city_names()
begin
	DECLARE done bool default false;
	DECLARE city_name varchar(50);
    DECLARE table_name_with_time varchar(50);

    DECLARE counter, random_num int;

	DECLARE city_cursor cursor
	FOR select city from city;

	DECLARE continue handler
	FOR not found SET done = true;

	open city_cursor;

	city_names_LOOP: LOOP

		fetch city_cursor into city_name;
        SET table_name_with_time = concat(city_name, '_', replace(time(now()), ':', '_'));

		IF done then leave city_names_LOOP;
		END IF;

		SET @drop_table_query = concat('drop table IF exists ', table_name_with_time, ';');
		SET @create_table_query = concat('create table ', table_name_with_time, ' (id int NOT NULL AUTO_INCREMENT, CONSTRAINT ', table_name_with_time, '_pk PRIMARY KEY (id));');

		SET counter = 1;
        SET random_num = (select floor(rand()*10)+1);

        prepare drop_table_query_ from @drop_table_query;
		execute drop_table_query_;
		deallocate prepare drop_table_query_;

		prepare create_table_query_ from @create_table_query;
		execute create_table_query_;
		deallocate prepare create_table_query_;

        columns_LOOP: LOOP

			SET @add_column_query = concat('alter table ', table_name_with_time, ' add column column_', counter, ' varchar(50);');

			prepare add_column_query_ from @add_column_query;
			execute add_column_query_;
			deallocate prepare add_column_query_;

			IF counter = random_num then leave columns_LOOP;
			END IF;

			SET counter = counter + 1;
		END LOOP;

	END LOOP;

	close city_cursor;
END //

delimiter ;