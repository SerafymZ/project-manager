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
    authorities varchar(255)
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

INSERT INTO UserDetail (username, password, authorities) VALUES(N'user', N'user', N'USER');
INSERT INTO UserDetail (username, password, authorities) VALUES(N'admin', N'admin', N'ADMIN');


-- CREATE TABLE users(
--     username varchar(15) PRIMARY KEY,
--     password varchar(150),
--     enabled tinyint
--     );
--
-- CREATE TABLE authorities(
--     username varchar(15) REFERENCES users(username),
--     authority varchar(15)
--     );
--
-- INSERT INTO users (username, password, enabled) VALUES
--     (N'user', N'{bcrypt}$2a$10$j5DUDV4o2reodruq9086FePFB4fxLrzq/0ZUqh3zXotYEaC2oemai', 1),
--     (N'admin', N'{bcrypt}$2a$10$50Oag0ifCFghZ1pMU5WeSO1hKHfpgY2DHBAb2TUv/vgK7SWy81IqS', 1);
--
-- INSERT INTO authorities (username, authority) VALUES
--     (N'user', N'ROLE_USER'),
--     (N'admin', N'ROLE_ADMIN');
