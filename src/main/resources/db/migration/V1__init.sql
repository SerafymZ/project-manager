CREATE TABLE Project (
    ID bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    parentID bigint NULL_TO_DEFAULT,
    name varchar(55) NOT NULL,
    description varchar(255) NULL_TO_DEFAULT
);

INSERT INTO Project(parentid, name, description) VALUES (null, 'Development', 'Development');
INSERT INTO Project(parentid, name, description) VALUES (1, 'Dev for prod', 'Development for production');
INSERT INTO Project(parentid, name, description) VALUES (1, 'Dev for sales', 'Development for sales');
INSERT INTO Project(parentid, name, description) VALUES (null, 'Sales', 'Sales');
INSERT INTO Project(parentid, name, description) VALUES (null, 'Production', 'Production');

CREATE TABLE Task (
    ID bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    projectID bigint NOT NULL REFERENCES project(ID),
--     userId varchar(20) NOT NULL REFERENCES project(id),
    status varchar(20) NOT NULL,
    description varchar(255) NULL_TO_DEFAULT,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

