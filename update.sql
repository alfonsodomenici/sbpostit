create table categoria (id integer not null auto_increment, aggiornato_il datetime(6), creato_il datetime(6), nome varchar(50) not null, primary key (id)) engine=InnoDB;
create table post_it (id integer not null auto_increment, aggiornato_il datetime(6), creato_il datetime(6), msg varchar(255) not null, quando date not null, categoria_id integer not null, primary key (id)) engine=InnoDB;
alter table post_it add constraint FKa1k2oemwpyf4wvkp1uh4xi7dx foreign key (categoria_id) references categoria (id);
