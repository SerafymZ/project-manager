CREATE TABLE Project (
    ID bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    parentID bigint NULL_TO_DEFAULT,
    name varchar(55) NOT NULL,
    description varchar(255) NULL_TO_DEFAULT
);

CREATE TABLE TaskType (
    ID bigint NOT NULL PRIMARY KEY,
    taskType varchar(50) NOT NULL
);

CREATE TABLE TaskStatus (
   ID bigint NOT NULL PRIMARY KEY,
   taskStatus varchar(50) NOT NULL
);

CREATE TABLE Task (
    ID bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    projectID bigint NOT NULL REFERENCES project(ID),
    userId bigint NOT NULL, // REFERENCES user(id),
    taskTypeId bigint NOT NULL REFERENCES TaskType(ID),
    taskStatusId bigint NOT NULL REFERENCES TaskStatus(ID),
    description varchar(255) NULL_TO_DEFAULT,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    branch varchar(100) NULL_TO_DEFAULT,
    managerDocs varchar(100) NULL_TO_DEFAULT
);

CREATE TABLE UserDetail (
    ID bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username varchar(20) NOT NULL,
    password varchar(150),
    authorityID bigint NOT NULL
);

CREATE TABLE Authority(
    ID bigint NOT NULL PRIMARY KEY,
    authority varchar(15)
);

INSERT INTO Project(parentid, name, description) VALUES (null, 'Development', 'Development');
INSERT INTO Project(parentid, name, description) VALUES (1, 'Dev for prod', 'Development for production');
INSERT INTO Project(parentid, name, description) VALUES (1, 'Dev for sales', 'Development for sales');
INSERT INTO Project(parentid, name, description) VALUES (null, 'Sales', 'Sales');
INSERT INTO Project(parentid, name, description) VALUES (null, 'Production', 'Production');
INSERT INTO Project(parentid, name, description) VALUES (2, 'Production', 'Production');
INSERT INTO Project(parentid, name, description) VALUES (3, 'Production', 'Production');
INSERT INTO Project(parentid, name, description) VALUES (6, 'Production', 'Production');
INSERT INTO Project(parentid, name, description) VALUES (7, 'Production', 'Production');

INSERT INTO TaskType VALUES (1, 'MANAGER_TASK');
INSERT INTO TaskType VALUES (2, 'TECHNICAL_TASK');

INSERT INTO TaskStatus VALUES (1, 'NEW');
INSERT INTO TaskStatus VALUES (2, 'PROGRESS');
INSERT INTO TaskStatus VALUES (3, 'DONE');

INSERT INTO Task(projectID, userId, taskTypeId, taskStatusId, description, creationDate, updateDate, branch, managerDocs) VALUES
(1, 1, 2, 1, 'description', '2023-04-17 18:47:52.69', '2023-04-18 15:50:52.69','branch1', null),
(4, 2, 1, 2, 'description', '2023-04-11 14:45:52.69', '2023-04-12 09:53:52.69', null, 'docs');

INSERT INTO Authority (ID, authority) VALUES (1, N'ROLE_USER');
INSERT INTO Authority (ID, authority) VALUES (2, N'ROLE_ADMIN');

INSERT INTO UserDetail (username, password, authorityID) VALUES(N'user', N'$2a$12$z.zYYlRb1poY0CeP/s1rG.zarpbiP1Nbw2BvJ/QkB81Z15mDy4E36', 1);
INSERT INTO UserDetail (username, password, authorityID) VALUES(N'admin', N'$2a$12$6CLUFtsJhxRgp5iTRva1O.vg7z7Jd1QMwWbZC.v32f65cAHBkY2Re', 2);
