create table employee(
e_username varchar(255) primary key,
e_role boolean DEFAULT 'false',
e_email varchar(255),
e_name varchar(255),
e_password varchar(255)
);


create table requests(
r_id int primary key,
r_status varchar(255),
r_amount int,
r_type varchar(255),
r_requester varchar(255),
foreign key (r_requester) references employee(e_username)
);
