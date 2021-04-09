CREATE DATABASE tests;
CREATE USER Sylvain;
grant all privileges on database tests to Sylvain;

REVOKE ALL privileges on database tests FROM sylvain;
DROP USER  IF EXISTS sylvain;

CREATE USER sylvain with password 'sylvain' --no-password --role=postgres --login;
grant all privileges on database tests to sylvain;

Drop DATABASE tests WITH (FORCE);

select * from Project

Update project set title='Project1', description='Project 1 innovation' where id =1

Update project set title='Project2', description='Project 2 for the next generation', create_date_time='2020-11-29 17:43:50.78922' where id =2

delete from project where id IN(6,7)

select * from person


