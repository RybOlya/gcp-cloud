use solar_system;

delimiter //

# Task 1
# Додати до БД 1 додаткову довільну таблицю і зв’язати з іншою існуючою таблицею зв’язком  1:M
# Однак для забезпечення цілісності значень використати тригери замість фізичного зовнішнього ключа
drop trigger if exists before_insert_city //
create trigger before_insert_city
before insert
on city
for each row
begin
    if (new.country_id not in (select id from country)) then
        signal sqlstate '45000'
        set message_text = 'City\'s refering to non-existent country';
    end if;
end//

drop trigger if exists before_update_city //
create trigger before_update_city
before update
on city
for each row
begin
    if (new.country_id not in (select id from country)) then
        signal sqlstate '45000' set message_text = 'City\'s refering to non-existent country';
    end if;
end//
drop trigger if exists before_update_country //
create trigger before_update_country
before update
on country
for each row
begin
    if (old.id in (select country_id from city)) then
        signal sqlstate '45000' set message_text = 'There are cities refering to the country';
    end if;
end//

drop trigger if exists before_delete_country //
create trigger before_delete_country
before delete
on country
for each row
begin
    if (old.id in (select country_id from city)) then
        signal sqlstate '45000' set message_text = 'There are cities refering to the country';
    end if;
end //

# Task 3.a
# Значення певного стовпця не може закінчувати двома нулями
drop trigger if exists no_apartments_ending_with_00_before_insert //
create trigger no_apartments_ending_with_00_before_insert
before insert
on client
for each row
begin
    if (new.apartment like "%00") then
        signal sqlstate '45000' set message_text = 'Apartment cannot end with \'00\'';
    end if;
end //
drop trigger if exists no_apartments_ending_with_00_before_update //
create trigger no_apartments_ending_with_00_before_update
before update
on client
for each row
begin
    if (new.apartment like "%00") then
        signal sqlstate '45000' set message_text = 'Contact number cannot end with \'00\'';
    end if;
end //


# Task 3.d
# Забезпечити мінімальну кардинальність 6 стрічок для певної  таблиці БД
drop trigger if exists ip_address_minimal_cardinality_trigger //
create trigger ip_address_minimal_cardinality_trigger
after delete
on ip_address
for each row
begin
    if ((select count(*) from ip_address) < 6) then
        signal sqlstate '45000' set message_text = 'Minimal cardinality condition is not met';
    end if;
end //

# Task 3.b
# Заборонити будь-яку модифікацію даних в таблиці
drop trigger if exists forbid_ip_address_data_update //
create trigger forbid_ip_address_data_update
before update
on ip_address
for each row
begin
	signal sqlstate '45000' set message_text = 'Data modification is forbidden';
end //
delimiter ;

