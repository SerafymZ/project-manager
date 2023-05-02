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
    userId bigint NOT NULL REFERENCES UserDetail(ID),
    taskTypeId bigint NOT NULL REFERENCES TaskType(ID),
    taskStatusId bigint NOT NULL REFERENCES TaskStatus(ID),
    description varchar(255) NULL_TO_DEFAULT,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    branch varchar(100) NULL_TO_DEFAULT,
    managerDocs varchar(100) NULL_TO_DEFAULT
);

INSERT INTO Authority (ID, authority) VALUES (1, N'ROLE_USER');
INSERT INTO Authority (ID, authority) VALUES (2, N'ROLE_ADMIN');

INSERT INTO UserDetail (username, password, authorityID) VALUES(N'user', N'$2a$12$z.zYYlRb1poY0CeP/s1rG.zarpbiP1Nbw2BvJ/QkB81Z15mDy4E36', 1);
INSERT INTO UserDetail (username, password, authorityID) VALUES(N'admin', N'$2a$12$6CLUFtsJhxRgp5iTRva1O.vg7z7Jd1QMwWbZC.v32f65cAHBkY2Re', 2);

INSERT INTO Project(parentid, name, description) VALUES (null, N'Development', N'Development');
INSERT INTO Project(parentid, name, description) VALUES (1, N'Industry', N'Development for industry');
INSERT INTO Project(parentid, name, description) VALUES (1, N'Sales', N'Development for sales');
INSERT INTO Project(parentid, name, description) VALUES (null, N'Administration', N'Administration');
INSERT INTO Project(parentid, name, description) VALUES (null, N'Production', N'Production');
INSERT INTO Project(parentid, name, description) VALUES (2, N'Cars', N'Cars');


INSERT INTO TaskType VALUES (1, N'MANAGER_TASK');
INSERT INTO TaskType VALUES (2, N'TECHNICAL_TASK');

INSERT INTO TaskStatus VALUES (1, N'NEW');
INSERT INTO TaskStatus VALUES (2, N'PROGRESS');
INSERT INTO TaskStatus VALUES (3, N'DONE');

INSERT INTO Task(projectID, userId, taskTypeId, taskStatusId, description, creationDate, updateDate, branch, managerDocs) VALUES
(1, 1, 2, 1, N'Develop new feature', '2023-04-17 18:47:52.69', '2023-04-18 15:50:52.69',null, N'develop_docs'),
(3, 2, 2, 2, N'Need to fix bug on milk sales', '2023-04-11 14:45:52.69', '2023-04-12 09:53:52.69', N'branch123', null),
(2, 2, 2, 1, N'Improve wheels development', '2023-04-09 13:41:52.69', '2023-04-15 09:55:52.69', N'branch23742364', null),
(5, 1, 1, 2, N'Improve production', '2023-04-05 10:15:52.69', '2023-04-05 10:15:52.69', null, N'prod_docs'),
(6, 2, 2, 3, N'Fixed engines development', '2023-04-23 17:36:52.69', '2023-04-25 07:51:52.69', N'branch746548', null),
(5, 2, 2, 2, N'Fixed product apps download', '2023-04-16 14:45:52.69', '2023-04-18 08:23:52.69', N'branch83478', null),
(2, 1, 1, 3, N'Develop navigation', '2023-04-10 17:25:52.69', '2023-04-16 14:12:52.69', null, N'nav_docs'),
(1, 2, 2, 3, N'Develop conference calls', '2023-04-01 05:12:52.69', '2023-04-05 09:01:52.69', N'branch6756', null);

