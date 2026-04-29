
-- Para ambos os usuários a senha é senha123

insert into users (email, password, roles) values ('admin@example.com', '$2a$10$HNAuz2b4.PtwTXoFFIBmZOb6JXSkScs4MSdLZMVfNg0wi5S2unLsW', 'ROLE_ADMIN');
insert into users (email, password, roles) values ('user@example.com', '$2a$10$HNAuz2b4.PtwTXoFFIBmZOb6JXSkScs4MSdLZMVfNg0wi5S2unLsW', 'ROLE_USER');
