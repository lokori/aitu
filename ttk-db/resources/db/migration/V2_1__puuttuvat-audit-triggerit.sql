alter table sopimus_ja_tutkinto_ja_osaamisala add column muutettu_kayttaja varchar(80) NOT NULL default 'JARJESTELMA' references kayttaja(oid);
alter table sopimus_ja_tutkinto_ja_osaamisala add column luotu_kayttaja varchar(80) NOT NULL default 'JARJESTELMA' references kayttaja(oid);
alter table sopimus_ja_tutkinto_ja_osaamisala add column muutettuaika timestamptz NOT NULL default current_timestamp;
alter table sopimus_ja_tutkinto_ja_osaamisala add column luotuaika timestamptz NOT NULL default current_timestamp;
create trigger sopimus_ja_tutkinto_ja_osaamisala_update before update on sopimus_ja_tutkinto_ja_osaamisala for each row execute procedure update_stamp() ;
create trigger sopimus_ja_tutkinto_ja_osaamisalal_insert before insert on sopimus_ja_tutkinto_ja_osaamisala for each row execute procedure update_created() ;
create trigger sopimus_ja_tutkinto_ja_osaamisalam_insert before insert on sopimus_ja_tutkinto_ja_osaamisala for each row execute procedure update_stamp() ;
create trigger sopimus_ja_tutkinto_ja_osaamisala_mu_update before update on sopimus_ja_tutkinto_ja_osaamisala for each row execute procedure update_modifier() ;
create trigger sopimus_ja_tutkinto_ja_osaamisala_mu_insert before insert on sopimus_ja_tutkinto_ja_osaamisala for each row execute procedure update_modifier() ;
create trigger sopimus_ja_tutkinto_ja_osaamisala_cu_insert before insert on sopimus_ja_tutkinto_ja_osaamisala for each row execute procedure update_creator() ;
alter table sopimus_ja_tutkinto_ja_tutkinnonosa add column muutettu_kayttaja varchar(80) NOT NULL default 'JARJESTELMA' references kayttaja(oid);
alter table sopimus_ja_tutkinto_ja_tutkinnonosa add column luotu_kayttaja varchar(80) NOT NULL default 'JARJESTELMA' references kayttaja(oid);
alter table sopimus_ja_tutkinto_ja_tutkinnonosa add column muutettuaika timestamptz NOT NULL default current_timestamp;
alter table sopimus_ja_tutkinto_ja_tutkinnonosa add column luotuaika timestamptz NOT NULL default current_timestamp;
create trigger sopimus_ja_tutkinto_ja_tutkinnonosa_update before update on sopimus_ja_tutkinto_ja_tutkinnonosa for each row execute procedure update_stamp() ;
create trigger sopimus_ja_tutkinto_ja_tutkinnonosal_insert before insert on sopimus_ja_tutkinto_ja_tutkinnonosa for each row execute procedure update_created() ;
create trigger sopimus_ja_tutkinto_ja_tutkinnonosam_insert before insert on sopimus_ja_tutkinto_ja_tutkinnonosa for each row execute procedure update_stamp() ;
create trigger sopimus_ja_tutkinto_ja_tutkinnonosa_mu_update before update on sopimus_ja_tutkinto_ja_tutkinnonosa for each row execute procedure update_modifier() ;
create trigger sopimus_ja_tutkinto_ja_tutkinnonosa_mu_insert before insert on sopimus_ja_tutkinto_ja_tutkinnonosa for each row execute procedure update_modifier() ;
create trigger sopimus_ja_tutkinto_ja_tutkinnonosa_cu_insert before insert on sopimus_ja_tutkinto_ja_tutkinnonosa for each row execute procedure update_creator() ;
alter table toimipaikka add column muutettu_kayttaja varchar(80) NOT NULL default 'JARJESTELMA' references kayttaja(oid);
alter table toimipaikka add column luotu_kayttaja varchar(80) NOT NULL default 'JARJESTELMA' references kayttaja(oid);
alter table toimipaikka add column muutettuaika timestamptz NOT NULL default current_timestamp;
alter table toimipaikka add column luotuaika timestamptz NOT NULL default current_timestamp;
create trigger toimipaikka_update before update on toimipaikka for each row execute procedure update_stamp() ;
create trigger toimipaikkal_insert before insert on toimipaikka for each row execute procedure update_created() ;
create trigger toimipaikkam_insert before insert on toimipaikka for each row execute procedure update_stamp() ;
create trigger toimipaikka_mu_update before update on toimipaikka for each row execute procedure update_modifier() ;
create trigger toimipaikka_mu_insert before insert on toimipaikka for each row execute procedure update_modifier() ;
create trigger toimipaikka_cu_insert before insert on toimipaikka for each row execute procedure update_creator() ;
alter table tutkinto_ja_tutkinnonosa add column muutettu_kayttaja varchar(80) NOT NULL default 'JARJESTELMA' references kayttaja(oid);
alter table tutkinto_ja_tutkinnonosa add column luotu_kayttaja varchar(80) NOT NULL default 'JARJESTELMA' references kayttaja(oid);
alter table tutkinto_ja_tutkinnonosa add column muutettuaika timestamptz NOT NULL default current_timestamp;
alter table tutkinto_ja_tutkinnonosa add column luotuaika timestamptz NOT NULL default current_timestamp;
create trigger tutkinto_ja_tutkinnonosa_update before update on tutkinto_ja_tutkinnonosa for each row execute procedure update_stamp() ;
create trigger tutkinto_ja_tutkinnonosal_insert before insert on tutkinto_ja_tutkinnonosa for each row execute procedure update_created() ;
create trigger tutkinto_ja_tutkinnonosam_insert before insert on tutkinto_ja_tutkinnonosa for each row execute procedure update_stamp() ;
create trigger tutkinto_ja_tutkinnonosa_mu_update before update on tutkinto_ja_tutkinnonosa for each row execute procedure update_modifier() ;
create trigger tutkinto_ja_tutkinnonosa_mu_insert before insert on tutkinto_ja_tutkinnonosa for each row execute procedure update_modifier() ;
create trigger tutkinto_ja_tutkinnonosa_cu_insert before insert on tutkinto_ja_tutkinnonosa for each row execute procedure update_creator() ;
