CREATE TABLE Groups
(
    id         INT PRIMARY KEY IDENTITY(1,1),
    group_name NVARCHAR(100) NOT NULL
);

CREATE TABLE Students
(
    id         INT PRIMARY KEY IDENTITY(1,1),
    first_name NVARCHAR(100) NOT NULL,
    last_name  NVARCHAR(100) NOT NULL,
    email      NVARCHAR(255) UNIQUE,
    group_id   INT,
    CONSTRAINT FK_Student_Group FOREIGN KEY (group_id) REFERENCES Groups (id)
);


INSERT INTO Groups(group_name)
VALUES ('PMI-11');
INSERT INTO Groups(group_name)
VALUES ('PMI-12');
INSERT INTO Groups(group_name)
VALUES ('PMI-13');
INSERT INTO Groups(group_name)
VALUES ('PMI-14');

INSERT INTO Students(first_name, last_name, email, group_id)
VALUES ('Bob', 'St', 'bob@bob.bob', 2);
INSERT INTO Students(first_name, last_name, email, group_id)
VALUES ('Taras', 'Klum', 'taras@tkl.net', 2);
INSERT INTO Students(first_name, last_name, email, group_id)
VALUES ('Yurik', 'Fedya', 'fedy@gmail.com', 2);
INSERT INTO Students(first_name, last_name, email, group_id)
VALUES ('Yurik Duplicate', 'Fedya', 'fedya@gmail.com', 2);
