

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE skills_developers;
TRUNCATE TABLE developers_projects;
TRUNCATE TABLE companies_projects;
TRUNCATE TABLE customers_projects;
TRUNCATE TABLE companies;
TRUNCATE TABLE projects;
TRUNCATE TABLE developers;
TRUNCATE TABLE customers;
TRUNCATE TABLE skills;
TRUNCATE TABLE skill_levels;
SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO companies (id, name, full_name)
VALUES(1, 'GoIT', 'OOO GoIT');

INSERT INTO companies (id, name, full_name)
VALUES(2, 'Adobe', 'OOO Adobe');



INSERT INTO projects (id, name, comment, cost, createDate)
VALUES (1, 'boots', 'boots', 150000, '2020-01-01');

INSERT INTO projects (id, name, comment, cost, createDate)
VALUES (2, 'car', 'car', 50000, '2003-06-01');

INSERT INTO projects (id, name, comment, cost, createDate)
VALUES (3, 'moto', 'moto', 700000, '2010-05-01');

INSERT INTO projects (id, name, comment, cost, createDate)
VALUES (4, 'finance', 'finance', 9000099, '2019-10-20');

INSERT INTO projects (id, name, comment, cost, createDate)
VALUES (5, 'sneakers', 'sneakers', 3000, '2020-05-01');


INSERT INTO customers (id, name, full_name)
VALUES(1, 'Adidas', 'OOO Adidas');

INSERT INTO customers (id, name, full_name)
VALUES(2, 'BMW', 'OOO BMW');

INSERT INTO customers (id, name, full_name)
VALUES(3, 'Forex', 'OOO Forex');



INSERT INTO developers (id, name, age, sex, salary)
VALUES (1, 'Grigoriy Kosarev', 34, 'MEN', 600);

INSERT INTO developers (id, name, age, sex, salary)
VALUES (2, 'Kate', 30, 'WOMEN', 3000);

INSERT INTO developers (id, name, age, sex, salary)
VALUES (3, 'Mr.Bean', 60, 'MEN', 2000);



INSERT INTO skills (id, name, comment)
VALUES (1, 'Java', '');

INSERT INTO skills (id, name, comment)
VALUES (2, 'C++', '');

INSERT INTO skills (id, name, comment)
VALUES (3,'C#', '');

INSERT INTO skills (id, name, comment)
VALUES (4, 'C++', '');

INSERT INTO skills (id, name, comment)
VALUES (5, 'JS', '');



INSERT INTO skill_levels (id, name, comment)
VALUES (1, 'Junior', '');

INSERT INTO skill_levels (id, name, comment)
VALUES (2, 'Middle', '');

INSERT INTO skill_levels (id, name, comment)
VALUES (3, 'Senior', '');



INSERT INTO skills_developers (id, developer_id, skill_id, skill_level_id)
VALUES (1, 1, 1, 1);

INSERT INTO skills_developers (id, developer_id, skill_id, skill_level_id)
VALUES (2, 2, 2, 2);

INSERT INTO skills_developers (id, developer_id, skill_id, skill_level_id)
VALUES (3, 3, 1, 3);



INSERT INTO developers_projects (id, developer_id, project_id)
VALUES (1, 1, 1);

INSERT INTO developers_projects (id, developer_id, project_id)
VALUES (2, 1, 2);

INSERT INTO developers_projects (id, developer_id, project_id)
VALUES (3, 2, 3);

INSERT INTO developers_projects (id, developer_id, project_id)
VALUES (4, 3, 1);



INSERT INTO companies_projects (id, company_id, project_id)
VALUES (1, 1 , 1);

INSERT INTO companies_projects (id, company_id, project_id)
VALUES (2, 1 , 2);

INSERT INTO companies_projects (id, company_id, project_id)
VALUES (3, 2 , 3);

INSERT INTO companies_projects (id, company_id, project_id)
VALUES (4, 2 , 4);



INSERT INTO customers_projects (id, customer_id, project_id)
VALUES (1, 1, 1);

INSERT INTO customers_projects (id, customer_id, project_id)
VALUES (2, 1, 5);

INSERT INTO customers_projects (id, customer_id, project_id)
VALUES (3, 2, 2);

INSERT INTO customers_projects (id, customer_id, project_id)
VALUES (4, 2, 3);

INSERT INTO customers_projects (id, customer_id, project_id)
VALUES (5, 3, 4);







