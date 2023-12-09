
CREATE database studentsdatabase1;
use studentsdatabase1;
CREATE TABLE admindata (
    username VARCHAR(255),
    password VARCHAR(255)
);
INSERT INTO admindata (username, password) VALUES ('admin', 'admin123');
ALTER TABLE admindata ADD COLUMN id INTEGER;
UPDATE admindata SET id = 1 WHERE username = 'admin';

Create Table course ( 
     course varchar(255) PRIMARY KEY,
     description varchar(255),
     degree varchar(255));

CREATE TABLE student (
    studentNum INT PRIMARY KEY,
    cur_year VARCHAR(255),
    course VARCHAR(255),
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    gender VARCHAR(10),
    birth DATE,
    status VARCHAR(255),
    image VARCHAR(255)
);
Create Table grades ( 
     studentNum INT,
     cur_year VARCHAR(255),
     course varchar(255),
     first_sem DOUBLE,
     second_sem DOUBLE,
     final DOUBLE);
