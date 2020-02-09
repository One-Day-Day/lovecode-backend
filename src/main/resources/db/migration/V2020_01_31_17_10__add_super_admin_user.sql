insert into user(username, password, is_active, created_at, updated_at) values('superadmin', MD5('superadmin'), 1, now(), now());
insert into user_role(user_id, role, created_at, updated_at) values (last_insert_id(), 'SUPER_ADMIN', now(), now());
