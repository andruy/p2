--delete from users ;
--delete from carts ;
--drop table users ;
--drop table items ;
--drop table carts ;
--drop table orders ;


create table users (
	id serial primary key,
	username varchar(40) not null,
	password varchar(40) not null,
	role smallint not null default 0
);


create table items (
	id serial primary key,
	description varchar(50) not null,
	price numeric
);


create table carts (
	id serial primary key,
	user_id int not null,
	item_id int not null,
	foreign key (user_id) references users (id),
	foreign key (item_id) references items (id)
);


create table orders (
	id serial primary key,
	user_id int not null,
	item_id int not null,
	quantity int not null default 1,
	order_number varchar(255) not null,
	foreign key (user_id) references users (id),
	foreign key (item_id) references items (id)
);


insert into items(description, price) values ('laptop', 600);
insert into items(description, price) values ('tv', 800);
insert into items(description, price) values ('phone', 400);
insert into items(description, price) values ('headphones', 200);
