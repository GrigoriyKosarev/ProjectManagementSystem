create table if not exists companies
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(150),
    full_name varchar(250),
    primary key (id)
);

create table if not exists customers
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(150),
    full_name varchar(250),
    primary key (id)
);

create table if not exists developers
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(150),
    age int,
    sex ENUM('MEN', 'WOMEN'),
    salary double,
    primary key (id)
);

create table if not exists skills
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(150),
    comment varchar(250),
    primary key (id)
);

create table if not exists skill_levels
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(150),
    comment varchar(250),
    primary key (id)
);

create table if not exists skills_developers
(
    id int NOT NULL AUTO_INCREMENT,
    developer_id int,
    skill_id int,
    skill_level_id int,
    primary key (id),
    foreign key (developer_id) references developers (id),
    foreign key (skill_id) references skills (id),
    foreign key (skill_level_id) references skill_levels (id)
);

create table if not exists projects
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(150),
    comment varchar(250),
    cost double,
    createDate DATE,
    primary key (id)
);

create table if not exists developers_projects
(
    id int NOT NULL AUTO_INCREMENT,
    developer_id int,
    project_id int,
    primary key (id),
    foreign key (developer_id) references developers (id),
    foreign key (project_id) references projects (id)
);

create table if not exists companies_projects
(
    id int NOT NULL AUTO_INCREMENT,
    company_id int,
    project_id int,
    primary key (id),
    foreign key (company_id) references companies (id),
    foreign key (project_id) references projects (id)
);

create table if not exists customers_projects
(
    id int NOT NULL AUTO_INCREMENT,
    customer_id int,
    project_id int,
    primary key (id),
    foreign key (customer_id) references customers (id),
    foreign key (project_id) references projects (id)
);