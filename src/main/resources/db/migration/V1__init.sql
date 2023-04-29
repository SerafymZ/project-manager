CREATE TABLE Project (
    id bigint NOT NULL PRIMARY KEY,
    parentID bigint NULL_TO_DEFAULT,
    name varchar(55) NOT NULL,
    description varchar(255) NULL_TO_DEFAULT
);

INSERT INTO Project values (1, null, 'Development', 'Development');
INSERT INTO Project values (2, 1, 'Dev for prod', 'Development for production');
INSERT INTO Project values (3, 1, 'Dev for sales', 'Development for sales');
INSERT INTO Project values (4, null, 'Sales', 'Sales');
INSERT INTO Project values (5, null, 'Production', 'Production');

CREATE TABLE Task (
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    projectId bigint NOT NULL REFERENCES project(id),
--     userId varchar(20) NOT NULL REFERENCES project(id),
    status varchar(20) NOT NULL,
    description varchar(255) NULL_TO_DEFAULT,
    creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

