CREATE DATABASE `codeschool` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE users_groups(
     id INT AUTO_INCREMENT,
     name VARCHAR(256) UNIQUE,
     PRIMARY KEY (id)
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO users_groups (name) VALUES
    ('Grupa A'),
    ('Grupa B');

CREATE TABLE users(
      id INT AUTO_INCREMENT,
      username VARCHAR(256),
      email VARCHAR(256) UNIQUE,
      password VARCHAR(256),
      group_id INT NOT NULL,
      PRIMARY KEY (id),
      FOREIGN KEY (group_id) REFERENCES users_groups(id)
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO users (username, email, group_id) VALUES
    ('mcieslak', 'marcin.cieslak@coderslab.pl', 1),
    ('jkowalski', 'jan.kowalski@coderslab.pl', 1),
    ('anowak', 'anna.nowak@coderslab.pl', 2);

CREATE TABLE exercises(
      id INT AUTO_INCREMENT,
      title VARCHAR(256),
      description TEXT,
      PRIMARY KEY (id)
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

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
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO solutions (user_id, exercise_id, created, updated, description) VALUES
    (1, 1, '2020-03-10', NOW() - INTERVAL 3 HOUR, 'To jest moje rozwiazanie'),
    (1, 2, '2020-03-10', NOW() - INTERVAL 2 HOUR, 'To jest rozwiazanie trudnego zadania'),
    (2, 1, '2020-03-09', NOW() - INTERVAL 2 DAY, 'Błahostka'),
    (2, 2, '2020-03-09', NOW() - INTERVAL 2 DAY, 'Ciężko!'),
    (3, 1, '2020-03-08', NOW() - INTERVAL 1 DAY, 'Nie mam pojęcia o co chodzi');

INSERT INTO exercises VALUES (null, 'trójkąt', 'c');


# CREATE TABLE users(
#       id INT AUTO_INCREMENT,
#       username VARCHAR(256),
#       email VARCHAR(256) UNIQUE,
#       password VARCHAR(256),
#       group_id INT NOT NULL,
#       PRIMARY KEY (id),
#       FOREIGN KEY (group_id) REFERENCES `users_groups`(id)
# ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;
#
# INSERT INTO users (username, email, group_id) VALUES
#     ('zjanicka', 'zosia.janicka@coderslab.pl', 1),
#     ('jkowalski', 'jan.kowalski@coderslab.pl', 1),
#     ('anowak', 'anna.nowak@coderslab.pl', 2);
#
# CREATE TABLE users_groups(
#      id INT NOT NULL,
#      name VARCHAR(256),
#      PRIMARY KEY (id)
# ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;
#
# INSERT INTO users_groups(name) VALUES
#     ('Grupa A'),
#     ('Grupa B');
#
# CREATE TABLE solutions(
#     id INT AUTO_INCREMENT,
#     user_id INT NOT NULL,
#     exercise_id INT NOT NULL,
#     created DATETIME,
#     updated DATETIME,
#     description TEXT,
#     PRIMARY KEY (id),
#     FOREIGN KEY (user_id) REFERENCES users(id)
# ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;
#
# INSERT INTO solutions (user_id, exercise_id, created, updated, description) VALUES
# (1, 1, '2020-03-10', NOW() - INTERVAL 3 HOUR, 'To jest moje rozwiazanie'),
# (1, 2, '2020-03-10', NOW() - INTERVAL 2 HOUR, 'To jest rozwiazanie trudnego zadania'),
# (2, 1, '2020-03-09', NOW() - INTERVAL 2 DAY, 'Błahostka'),
# (2, 2, '2020-03-09', NOW() - INTERVAL 2 DAY, 'Ciężko!'),
# (3, 1, '2020-03-08', NOW() - INTERVAL 1 DAY, 'Nie mam pojęcia o co chodzi');
#
# CREATE TABLE exercises(
#       id INT AUTO_INCREMENT,
#       title VARCHAR(256),
#       description TEXT,
#       solution_id INT,
#       PRIMARY KEY (id),
#       FOREIGN KEY (solution_id) REFERENCES solutions(id) ON DELETE CASCADE
# ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;
#
# INSERT INTO exercises (title, description) VALUES
#     ('Zadanie 1', 'proste'),
#     ('Zadanie 2', 'trudne'),
#     ('Oblicz pole trójkąta', 'Łatwe zadanie dla początkujących');

SELECT * FROM solutions;
SELECT * FROM users;
SELECT * FROM users_groups;
SELECT * FROM exercises;
# SELECT * FROM users u join users_groups ug on u.group_id = ug.group_id where ug.group_id = 3;
