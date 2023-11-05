USE solar_system;

DELIMITER //
drop function if exists maxEnergySoldFromSystems//
create function maxEnergySoldFromSystems()
returns int
deterministic
begin
    return (select max(energy_sold) from solar_system);
end //
DELIMITER ;