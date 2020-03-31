CREATE DATABASE codeschool CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE users_groups(
    id INT AUTO_INCREMENT,
    name VARCHAR(256),
    PRIMARY KEY (id)
);

INSERT INTO users_groups (name) VALUES
('Grupa A'),
('Grupa B');

CREATE TABLE users(
    id INT AUTO_INCREMENT,
    username VARCHAR(256),
    email VARCHAR(256) UNIQUE,
    password VARCHAR(256),
    user_group_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_group_id) REFERENCES users_groups(id)
);

INSERT INTO users (username, email, user_group_id) VALUES
('mcieslak', 'marcin.cieslak@coderslab.pl', 1),
('jkowalski', 'jan.kowalski@coderslab.pl', 1),
('anowak', 'anna.nowak@coderslab.pl', 2);

CREATE TABLE exercises(
    id INT AUTO_INCREMENT,
    title VARCHAR(256),
    description TEXT,
    PRIMARY KEY (id)
);

INSERT INTO exercises (title, description) VALUES
('Zadanie 1', 'proste'),
('Zadanie 2', 'trudne');

CREATE TABLE solutions(
    id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    exercise_id INT NOT NULL,
    created DATETIME,
    updated DATETIME,
    description TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE CASCADE
);

INSERT INTO solutions (user_id, exercise_id, created, updated, description) VALUES
(1, 1, '2020-03-10', NOW() - INTERVAL 3 HOUR, 'To jest moje rozwiazanie'),
(1, 2, '2020-03-10', NOW() - INTERVAL 2 HOUR, 'To jest rozwiazanie trudnego zadania'),
(2, 1, '2020-03-09', NOW() - INTERVAL 2 DAY, 'Błahostka'),
(2, 2, '2020-03-09', NOW() - INTERVAL 2 DAY, 'Ciężko!'),
(3, 1, '2020-03-08', NOW() - INTERVAL 1 DAY, 'Nie mam pojęcia o co chodzi');

SELECT * FROM solutions;
SELECT * FROM users;
SELECT * FROM users_groups;
SELECT * FROM exercises;
SELECT * FROM users u join users_groups ug on u.user_group_id = ug.id where ug.id = ?

