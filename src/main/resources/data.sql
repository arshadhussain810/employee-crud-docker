INSERT IGNORE INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT IGNORE INTO roles(name) VALUES ('ROLE_USER');

INSERT IGNORE INTO department
(department_code, name, description, status)
VALUES
('HR01', 'HR', 'Human Resources', 'ACTIVE'),
('IT01', 'IT', 'Information Technology', 'ACTIVE'),
('FIN01', 'FINANCE', 'Finance Department', 'ACTIVE');