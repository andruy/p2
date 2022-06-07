drop table users ;
delete from users ;
delete from carts ;
drop table items ;
drop table carts ;
drop table orders ;


select * from items i ;
select * from carts c ;
select * from users u ;
select * from orders o ;

select * from carts c where user_id = 3;


create table items (
	id serial primary key,
	description varchar(50) not null,
	price numeric
);

create table carts (
	user_id int not null,
	item_id int not null,
	quantity int not null default 1,
	primary key (user_id, item_id),
	foreign key (user_id) references users (id),
	foreign key (item_id) references items (id)
);

create table carts (
	id serial primary key,
	user_id int not null,
	item_id int not null,
--	quantity int not null default 1,
	foreign key (user_id) references users (id),
	foreign key (item_id) references items (id)
);

create table users (
	id serial primary key,
	username varchar(40) not null,
	password varchar(40) not null,
	role smallint not null default 0
);

create table orders (
	id serial primary key,
	user_id int not null,
	item_id int not null,
	quantity int not null default 1,
	foreign key (user_id) references users (id),
	foreign key (item_id) references items (id)
);


ALTER TABLE public.items RENAME COLUMN name TO "description";
insert into users(username, password) values ('john', '000');
insert into items(description, price) values ('headphones', 200);
insert into carts(user_id, item_id) values (3, 1);