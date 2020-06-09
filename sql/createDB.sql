CREATE DATABASE `codeschool` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE users_groups(
     id INT AUTO_INCREMENT,
     name VARCHAR(256) UNIQUE,
     PRIMARY KEY (id)
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO users_groups (id, name) VALUES
    (1, 'Group ADMIN'),
    (2, 'Group A'),
    (3, 'Group B');

CREATE TABLE users(
      id INT AUTO_INCREMENT,
      username VARCHAR(256),
      email VARCHAR(256) UNIQUE,
      password VARCHAR(256) NOT NULL,
      group_id INT NOT NULL,
      is_admin BOOLEAN NOT NULL,
      PRIMARY KEY (id),
      FOREIGN KEY (group_id) REFERENCES users_groups(id)
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO users (username, email, password, group_id, is_admin) VALUES
    ('ADMIN', 'admin@gmail.com', 'adminpass', 1, true),
    ('Marcin Cieszkowski', 'marcin.cieszkowski@gmail.com', 'password', 2, false),
    ('Janusz Kowalski', 'jan.kowalski@wp.pl', 'password', 2, false),
    ('Anna Nowak', 'anna.nowak@o2.pl', 'password', 3, false);

CREATE TABLE exercises(
      id INT AUTO_INCREMENT,
      title VARCHAR(256),
      description TEXT,
      PRIMARY KEY (id)
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO exercises (title, description) VALUES
    ('Geometry - calculate the area of the triangle.', 'Calculate the area of​an equilateral triangle whose height is 4 cm.'),
    ('Vectors', 'Calculate the distance of the center of the segment AB from the beginning of the coordinate system: A (-7.7), B (11.1)');

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
    (2, 1, '2020-06-05', NOW() - INTERVAL 3 HOUR,
     'First we calculate a from the formula:
    h=aV3/2

    4=aV3/2
    a=8V3/3

    P=a^2*V3/4

    P=(8V3/3)^2*V3/4

    P=64/3*V3/4
    P=16V3/3 cm^2'
    ),
    (2, 2, '2020-06-01', NOW() - INTERVAL 2 HOUR, 'I don''t even know how to start, I give up'),
    (3, 1, '2020-05-20', NOW() - INTERVAL 2 DAY, 'I don''t know how to solve this exercise'),
    (3, 2, '2020-06-03', NOW() - INTERVAL 2 DAY,
            'A(-7;7)
    B(11,1)
    *we use the formula for the middle of the episode : S=[ ( x1+x2)/2 ; (y1+y2)/2]

    S=[ (-7+11)/2 ; (7+1)/2 ]

    S=(4/2 ; 8/2 ]

    S=-(2;4 )

    The origin of the coordinate system is a point P=(0;0)
    When calculating the distance between the middle of the segment and the beginning of the coordinate
    system, we use the formula:

    AB^2=(x2-x1)^2 + (y2-y1)^2

    calculated and given points S(2;4) i P(0;0) substitute into the formula as above and count:

    AB^2=(0-2)^2 + (0-4)^2= (-2)^2 +(-4)^2= 4+16=20

    AB=V20=V(4*5)
    AB=2V5'
    ),
    (4, 1, '2020-04-28', NOW() - INTERVAL 1 DAY,
     '  h=a√3/2
    h=4cm
    4=a√3/2 /·2
    8=a√3/:√3
    a=8:√3
    a=8/√3  ·√3/√3
    a=8√3/3
    P=a²√3/4
    P={ (8√3/3)² ·√3} /4
    P={64·3/9) ·√3} :4= 16√3/3
    P=16√3/3 cm²'
    );