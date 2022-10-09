create table users (
	id SERIAL PRIMARY KEY,
	username TEXT not null,
    password TEXT not null
);

create table user_roles (
	user_id BIGINT,
	roles TEXT not null,

	constraint fk_users_roles_user foreign key(user_id) references users(id)
);