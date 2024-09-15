USE Kort;

CREATE USER 'eksamen'@'localhost' IDENTIFIED BY 'eksamen1';

GRANT ALL PRIVILEGES ON Kort.* TO eksamen@'localhost';