-- Diagrafh prwta autou tou pinaka giati anafairetai stous allous duo
DROP TABLE IF EXISTS `Comments`;
DROP TABLE IF EXISTS `Works`;
DROP TABLE IF EXISTS `Tasks`;
DROP TABLE IF EXISTS `Staff`;
DROP TABLE IF EXISTS `Projects`;
DROP TABLE IF EXISTS `Users`;

-- Epeidh einai sugkekrimenoi oi roloi, topothetountai ws ENUM gia min
-- boroun na boun asxetes times
CREATE TABLE `Users` (
	`username` VARCHAR(16) NOT NULL PRIMARY KEY,
	`password` VARCHAR(16) NOT NULL,
	`name`	   VARCHAR(20) NOT NULL,
	`surname`  VARCHAR(30) NOT NULL,
	`email`	   VARCHAR(30) NOT NULL,
	`role`	   ENUM('ADMINISTRATOR', 'MANAGER', 'STAFF', 'GUEST') NOT NULL
);

INSERT INTO `Users`(
	`username`, `password`, `name`, `surname`, `email`, `role`) VALUES 
	('admin', 'admin', 'Administrator', 'Administrator', 'sdi0800169@di.uoa.gr', 'ADMINISTRATOR'),
	('guest', 'guest', 'guest', 'guest', 'guest@guest.gr', 'GUEST'),
	('manager', 'manager', 'manager', 'manager', 'manager@manager.gr', 'MANAGER'),
	('manager1', 'manager1', 'manager1', 'manager1', 'manager1@manager1.gr', 'MANAGER'),
	('mastoras', 'mastoras', 'mastoras', 'mastoras', 'mastoras@a.com', 'GUEST'),
	('staff1', 'staff1', 'staff1', 'staff1', 'staff1@staff1.gr', 'STAFF'),
	('staff2', 'staff2', 'staff2', 'staff2', 'staff2@staff2.gr', 'STAFF');


-- to visiblity tha exei duo times, gi' auto kai orizetai ws ENUM
-- manager, xeno kleidh sto username tou pinaka user
-- ON UPDATE CASCADE: se periptwsh pou allaxei o xrhsths (to username tou
-- manager) allazei automata kai edw.
-- ON DELETE CASCADE: se periptwsh pou diagrafei o xrhsths (to username tou
-- manager), diagrafontai kai ola ta erga pou dieuthinei
CREATE TABLE `Projects` (
    `name` VARCHAR(16) NOT NULL PRIMARY KEY,
    `description` VARCHAR(256) NOT NULL,
    `visibility` ENUM('PUBLIC', 'PRIVATE') NOT NULL,
    `manager` VARCHAR(16) NOT NULL,
    FOREIGN KEY (`manager`) REFERENCES `Users` (`username`) ON UPDATE CASCADE ON DELETE CASCADE
);

-- H antistoixia tou pinaka Projects kai tou Users einai polla pros polla
-- (m pros n), gi' auto dhmiourgeitai neos pinakas Staff
-- An allaxei/diagrafei kapoios xrhsths, enhmeronontai automata ta erga pou douleue
-- An allaxei/diagrafei kapoio ergo, enhmeronontai automata oi xrhstes pou douleuane
CREATE TABLE `Staff` (
    `user` VARCHAR(16) NOT NULL,
    `project` VARCHAR(16) NOT NULL,
    PRIMARY KEY (`user`, `project`),
    FOREIGN KEY (`user`) REFERENCES `Users` (`username`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`project`) REFERENCES `Projects` (`name`) ON UPDATE CASCADE ON DELETE CASCADE
);

-- ON UPDATE CASCADE ON DELETE CASCADE: ama svistei ena project tha svinontai kai oi ergasies tou
CREATE TABLE `Tasks` (
	`project` VARCHAR(16) NOT NULL,
	`name` VARCHAR(16) NOT NULL,
	`description` VARCHAR(256) NOT NULL,
	`startDate` DATE NOT NULL,
	`endDate` DATE NOT NULL,
	`state` ENUM('UNCOMPLETED', 'COMPLETED'),
	PRIMARY KEY (`project`, `name`),
	FOREIGN KEY (`project`) REFERENCES `Projects` (`name`) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Works einai (m pros n) susxetish metaxu xrhstwn (Users) kai ergasiwn (Tasks)
CREATE TABLE `Works` (
	`user` VARCHAR(16) NOT NULL,
	`project` VARCHAR(16) NOT NULL,
	`task` VARCHAR(16) NOT NULL,
	PRIMARY KEY (`user`, `project`, `task`),
	FOREIGN KEY (`user`) REFERENCES `Users` (`username`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`project`,`task`) REFERENCES `Tasks` (`project`,`name`) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Comments einai (m pros n) susxetish metaxu xrhstwn (Users) kai ergasiwn (Tasks)
CREATE TABLE `Comments` (
	`user` VARCHAR(16) NOT NULL,
	`project` VARCHAR(16) NOT NULL,
	`task` VARCHAR(16) NOT NULL,
	`date` TIMESTAMP NOT NULL,
	`text` VARCHAR(256) NOT NULL,
	PRIMARY KEY (`user`, `project`, `task`, `date`),
	FOREIGN KEY (`user`) REFERENCES `Users` (`username`) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (`project`,`task`) REFERENCES `Tasks` (`project`,`name`) ON UPDATE CASCADE ON DELETE CASCADE
);

