CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    phone VARCHAR(50),
    date_of_birth DATE,
    gender VARCHAR(10),
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role_id BIGINT
);
INSERT INTO users (first_name, last_name, email, password, phone, date_of_birth, gender, address, created_at, updated_at, role_id)
VALUES
('John', 'Doe', 'john@example.com', 'password123', '1234567890', '1990-01-01', 'Male', '123 Main Street', NOW(), NOW(), 1),
('Jane', 'Smith', 'jane@example.com', 'password456', '0987654321', '1995-05-15', 'Female', '456 Elm Street', NOW(), NOW(), 2),
('Alice', 'Brown', 'alice@example.com', 'password789', '1112223333', '1988-03-22', 'Female', '789 Oak Avenue', NOW(), NOW(), 1),
('Bob', 'Johnson', 'bob@example.com', 'password321', '4445556666', '1992-07-10', 'Male', '101 Pine Lane', NOW(), NOW(), 2),
('Charlie', 'Williams', 'charlie@example.com', 'password654', '7778889999', '1985-11-30', 'Male', '202 Maple Street', NOW(), NOW(), 1),
('Diana', 'Taylor', 'diana@example.com', 'password987', '5556667777', '1991-09-17', 'Female', '303 Cedar Road', NOW(), NOW(), 2),
('Evan', 'Anderson', 'evan@example.com', 'password159', '3334445555', '1993-12-25', 'Male', '404 Birch Boulevard', NOW(), NOW(), 1),
('Fiona', 'Thomas', 'fiona@example.com', 'password753', '2223334444', '1994-06-05', 'Female', '505 Willow Way', NOW(), NOW(), 2),
('George', 'Jackson', 'george@example.com', 'password852', '8889990000', '1989-04-18', 'Male', '606 Aspen Circle', NOW(), NOW(), 1),
('Hannah', 'White', 'hannah@example.com', 'password963', '6667778888', '1996-08-09', 'Female', '707 Redwood Street', NOW(), NOW(), 2);