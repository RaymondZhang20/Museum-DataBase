CREATE TABLE Museum (Mid int NOT NULL PRIMARY KEY, location varchar(80) NOT NULL, Mname varchar(50) UNIQUE);

CREATE TABLE ExhibitionHall (
                                       Zid		int		PRIMARY KEY,
                                       floor	char(10)	NOT NULL,
                                       isOpen	char(5)		NOT NULL,
                                       Zname		char(50)	UNIQUE,
                                       Mid  	int		NOT NULL,
                                       FOREIGN KEY (Mid) REFERENCES Museum(Mid) ON DELETE CASCADE
);

CREATE TABLE Cinema (
                               Zid		int		NOT NULL PRIMARY KEY,
                               floor	char(10)	NOT NULL,
                               Mid		int		NOT NULL,
                               FOREIGN KEY (Mid) REFERENCES Museum(Mid) ON DELETE CASCADE
);

CREATE TABLE GiftStore (
                                  Zid		int		NOT NULL PRIMARY KEY,
                                  floor		char(10)	NOT NULL,
                                  Mid		int		NOT NULL,
                                  FOREIGN KEY (Mid) REFERENCES Museum(Mid) ON DELETE CASCADE
);

CREATE TABLE Activity (
                                 Aid		int		NOT NULL PRIMARY KEY,
                                 title		char(80)	UNIQUE,
                                 Adate		char(15)		NOT NULL,
                                 Zid		int		NOT NULL,
                                 FOREIGN KEY (Zid) REFERENCES ExhibitionHall (Zid) ON DELETE CASCADE
);

CREATE TABLE Guide1 (
                        Gid		int		NOT NULL PRIMARY KEY,
                        Gname		char(50)	UNIQUE,
                        field		char(50)	NOT NULL
);

CREATE TABLE Guide2 (
                        field		char(50)	NOT NULL PRIMARY KEY,
                        office		char(50)	NOT NULL
);

CREATE TABLE Exhibits2 (
                                                  category	char(50)	NOT NULL,
                                                  Gid		int		NOT NULL PRIMARY KEY,
                                                  FOREIGN KEY (Gid) REFERENCES Guide1(Gid) ON DELETE CASCADE
);

CREATE TABLE Exhibits3 (
                                                  Eid	int	 NOT NULL PRIMARY KEY,
                                                  birthplace	char(50),
                                                  Eyear		char(10),
                                                  Ename 		char(50)	UNIQUE,
                                                  Mid		int			NOT NULL,
                                                  Gid		int			NOT NULL,
                                                  FOREIGN KEY (Mid) REFERENCES Museum(Mid) ON DELETE CASCADE,
                                                  FOREIGN KEY (Gid) REFERENCES Guide1(Gid) ON DELETE CASCADE
);

CREATE TABLE Exhibits4 (
                                                  Mid		int		NOT NULL PRIMARY KEY,
                                                  Zid		int 	NOT NULL,
                                                  FOREIGN KEY (Mid) REFERENCES Museum(Mid) ON DELETE CASCADE,
                                                  FOREIGN KEY (Zid) REFERENCES ExhibitionHall (Zid) ON DELETE CASCADE
);

CREATE TABLE Author1 (Rid int NOT NULL PRIMARY KEY, Uname char(50), nation char(50));

CREATE TABLE Author2 (
                                  Rid		int		NOT NULL,
                                  Eid		int 	NOT NULL,
                                  PRIMARY KEY (Rid, Eid),
                                  FOREIGN KEY (Eid) REFERENCES Exhibits3(Eid) ON DELETE CASCADE
);

CREATE TABLE Participate (
                             Aid		int 		NOT NULL,
                             Gid		int			NOT NULL,
                             PRIMARY KEY (Aid, Gid),
                             FOREIGN KEY (Aid) REFERENCES Activity(Aid) ON DELETE CASCADE,
                             FOREIGN KEY (Gid) REFERENCES Guide1(Gid) ON DELETE CASCADE
);

CREATE TABLE Film (
                      Fid		int	NOT NULL PRIMARY KEY,
                      Ftime		int	NOT NULL,
                      Fname		char(50)	UNIQUE
);

CREATE TABLE Play (
                      Showtime 	char(50)	NOT NULL,
                      Fid		int			NOT NULL,
                      Zid		int			NOT NULL,
                      PRIMARY KEY (Fid, Zid),
                      FOREIGN KEY (Fid) REFERENCES Film(Fid) ON DELETE CASCADE,
                      FOREIGN KEY (Zid) REFERENCES Cinema (Zid) ON DELETE CASCADE
);

CREATE TABLE About (
                       Fid		int		NOT NULL,
                       Eid		int		NOT NULL,
                       PRIMARY KEY (Fid, Eid),
                       FOREIGN KEY (Fid) REFERENCES Film(Fid) ON DELETE CASCADE,
                       FOREIGN KEY (Eid) REFERENCES Exhibits3(Eid) ON DELETE CASCADE
);

CREATE TABLE Souvenir (
                          Sid		int		NOT NULL PRIMARY KEY,
                          Sname		char(50)	UNIQUE,
                          price		decimal		NOT NULL
);

CREATE TABLE Sell(
                     inventory 	int			NOT NULL,
                     Sid		int 		NOT NULL,
                     Zid		int 		NOT NULL,
                     PRIMARY KEY (Sid, Zid),
                     FOREIGN KEY (Sid) REFERENCES Souvenir(Sid) ON DELETE CASCADE,
                     FOREIGN KEY (Zid) REFERENCES GiftStore(Zid) ON DELETE CASCADE
);
INSERT INTO Museum   VALUES (1001, '6339 Stores Rd', 'Pacific Museum of Earth');
INSERT INTO Museum   VALUES (1002, '2212 Main Mall', 'Beaty Biodiversity Museum');
INSERT INTO Museum   VALUES (1003, '6393 NM Marine Dr', 'Museum of Anthropology at UBC');
INSERT INTO Museum   VALUES (1004, '1575 Alma St', 'Old Hastings Mill Store Museum');
INSERT INTO Museum   VALUES (1005, '2545 Blanca St', 'BC Golf Museum');
INSERT INTO Museum   VALUES (1006, '680 17th St', 'West Vancouver Art Museum');

INSERT INTO ExhibitionHall   VALUES (2011, 'ground', 'true', 'Hominin Hall', 1001);
INSERT INTO ExhibitionHall   VALUES (2012, 'ground', 'true', 'Weather Alley', 1001);
INSERT INTO ExhibitionHall   VALUES (2021, 'ground', 'true', 'The Studio Hall', 1002);
INSERT INTO ExhibitionHall   VALUES (2022, 'ground', 'true', 'Blue Whale Display Hall', 1002);
INSERT INTO ExhibitionHall   VALUES (2041, 'ground', 'true', 'Heritage Hall', 1004);
INSERT INTO ExhibitionHall   VALUES (2051, 'ground', 'true', 'Golf Hall of Fame', 1005);
INSERT INTO ExhibitionHall   VALUES (2061, 'ground', 'true', 'Great Hall', 1006);

INSERT INTO Cinema   VALUES (2110, 'ground', 1001);
INSERT INTO Cinema   VALUES (2120, 'ground', 1002);
INSERT INTO Cinema  VALUES (2130, 'ground', 1003);
INSERT INTO Cinema   VALUES (2140, 'ground', 1004);
INSERT INTO Cinema   VALUES (2150, 'ground', 1005);

INSERT INTO GiftStore    VALUES (2210, 'ground', 1001);
INSERT INTO GiftStore    VALUES (2220, 'ground', 1002);
INSERT INTO GiftStore    VALUES (2230, 'ground', 1003);
INSERT INTO GiftStore    VALUES (2240, 'ground', 1004);
INSERT INTO GiftStore    VALUES (2250, 'ground', 1005);
INSERT INTO GiftStore    VALUES (2260, 'ground', 1006);

INSERT INTO Activity    VALUES (4021, 'Meet Big Blue!', '2010-05-01', 2022);
INSERT INTO Activity    VALUES (4022, 'The Curious World of Seaweed', '2022-05-15', 2021);
INSERT INTO Activity    VALUES (4061, 'Rabbit Lane: Douglas Coupland', '2022-03-30', 2061);
INSERT INTO Activity    VALUES (4062, 'A Twist of the Rules: The Architecture of Paul Merrick', '2022-06-08', 2061);
INSERT INTO Activity    VALUES (4063, 'Martha Sturdy: All Fall Down', '2022-10-22', 2061);

INSERT INTO Guide1   VALUES (9201, 'Bob', 'archaeology');
INSERT INTO Guide1   VALUES (9202, 'Mary', 'anthropology');
INSERT INTO Guide1   VALUES (9203, 'Tim', 'architecture');
INSERT INTO Guide1   VALUES (9204, 'Stephanie', 'paleobiology');
INSERT INTO Guide1   VALUES (9205, 'Becky', 'art');

INSERT INTO Guide2   VALUES ('archaeology', 'archaeology office');
INSERT INTO Guide2   VALUES ('anthropology', 'anthropology office');
INSERT INTO Guide2   VALUES ('architecture', 'architecture office');
INSERT INTO Guide2   VALUES ('biology', 'biology office');
INSERT INTO Guide2   VALUES ('art', 'art office');

INSERT INTO Exhibits2   VALUES ('archaeology', 9201);
INSERT INTO Exhibits2   VALUES ('anthropology', 9202);
INSERT INTO Exhibits2   VALUES ('architecture', 9203);
INSERT INTO Exhibits2   VALUES ('paleobiology', 9204);
INSERT INTO Exhibits2   VALUES ('art', 9205);

INSERT INTO Exhibits3   VALUES (6001, 'Nail Pond, Prince Edward Island, Canada', '2010', 'Big whale', 1002, 9201);
INSERT INTO Exhibits3   VALUES (6002, NULL, NULL, 'Human Skull',1001, 9202);
INSERT INTO Exhibits3   VALUES (6003, 'British Columbia, Canada', '1973', 'Eagle Harbour', 1006, 9203);
INSERT INTO Exhibits3   VALUES (6004, 'British Columbia, Canada', '2000', 'Sculpture',1006, 9205);
INSERT INTO Exhibits3   VALUES (6005, 'British Columbia, Canada', '1998', 'Girlfriend in a Coma', 1006, 9205);
INSERT INTO Exhibits3   VALUES (6006, 'Canada', NULL, 'The Curious World of Seaweed', 1002, 9201);

INSERT INTO Exhibits4   VALUES (1002, 2021);
INSERT INTO Exhibits4   VALUES (1001, 2011);
INSERT INTO Exhibits4   VALUES (1006, 2061);
INSERT INTO Exhibits4   VALUES (1004, 2041);
INSERT INTO Exhibits4   VALUES (1005, 2051);

INSERT INTO Author1    VALUES (9101, 'Dr. Andrew Trites', 'Canada');
INSERT INTO Author1    VALUES (9102, 'Josie Iselin', NULL);
INSERT INTO Author1    VALUES (9103, 'Martha Sturdy', NULL);
INSERT INTO Author1    VALUES (9104, 'Paul Merrick', NULL);
INSERT INTO Author1    VALUES (9105, 'Douglas Coupland', NULL);

INSERT INTO Author2    VALUES (9101, 6001);
INSERT INTO Author2    VALUES (9102, 6006);
INSERT INTO Author2    VALUES (9103, 6004);
INSERT INTO Author2    VALUES (9104, 6003);
INSERT INTO Author2    VALUES (9105, 6005);

INSERT INTO Participate   VALUES (4021, 9201);
INSERT INTO Participate   VALUES (4022, 9205);
INSERT INTO Participate   VALUES (4061, 9205);
INSERT INTO Participate   VALUES (4062, 9203);
INSERT INTO Participate   VALUES (4063, 9205);

INSERT INTO Film   VALUES (5101, 60, 'Story of the Big Blue');
INSERT INTO Film   VALUES (5102, 30, 'Seaweeds!');
INSERT INTO Film   VALUES (5103, 45, 'Background of Douglas Coupland');
INSERT INTO Film   VALUES (5105, 20, 'Background of Paul Merrick');
INSERT INTO Film   VALUES (5106, 25, 'Background of Martha Sturdy');

INSERT INTO Play   VALUES ('14:00', 5101, 2110);
INSERT INTO Play   VALUES ('13:00', 5102, 2120);
INSERT INTO Play   VALUES ('15:00', 5103, 2130);
INSERT INTO Play   VALUES ('16:00', 5105, 2140);
INSERT INTO Play   VALUES ('13:30', 5106, 2150);

INSERT INTO About   VALUES (5101, 6001);
INSERT INTO About   VALUES (5102, 6006);
INSERT INTO About   VALUES (5103, 6005);
INSERT INTO About   VALUES (5105, 6003);
INSERT INTO About   VALUES (5106, 6004);

INSERT INTO Souvenir   VALUES (5201, 'Big Whale Key Chain', 20.0);
INSERT INTO Souvenir   VALUES (5202, 'Dinosaur Mug', 15.5);
INSERT INTO Souvenir   VALUES (5203, 'Love Martha Postcard', 5.0);
INSERT INTO Souvenir   VALUES (5204, 'Golf Ball', 30.0);
INSERT INTO Souvenir   VALUES (5205, 'Paul’s Building Picromodel', 150.0);

INSERT INTO Sell   VALUES (100, 5201, 2220);
INSERT INTO Sell   VALUES (50, 5202, 2220);
INSERT INTO Sell   VALUES (100, 5203, 2260);
INSERT INTO Sell   VALUES (30, 5204, 2250);
INSERT INTO Sell   VALUES (10, 5205, 2260);
