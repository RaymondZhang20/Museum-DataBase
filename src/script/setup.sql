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
                                                  Zid		int			NOT NULL,
                                                  Gid		int			NOT NULL,
                                                  FOREIGN KEY (Zid) REFERENCES ExhibitionHall(Zid) ON DELETE CASCADE,
                                                  FOREIGN KEY (Gid) REFERENCES Guide1(Gid) ON DELETE CASCADE
);

CREATE TABLE Exhibits4 (
                                                  Mid		int		NOT NULL,
                                                  Zid		int 	NOT NULL PRIMARY KEY,
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
INSERT INTO ExhibitionHall   VALUES (2031, 'ground', 'true', 'Great Hall', 1003);
INSERT INTO ExhibitionHall   VALUES (2041, 'ground', 'true', 'Heritage Hall', 1004);
INSERT INTO ExhibitionHall   VALUES (2051, 'ground', 'true', 'Golf Hall of Fame', 1005);
INSERT INTO ExhibitionHall   VALUES (2061, 'ground', 'true', 'Main Exhibition Hall', 1006);

INSERT INTO Cinema   VALUES (2110, 'ground', 1001);
INSERT INTO Cinema   VALUES (2120, 'ground', 1002);
INSERT INTO Cinema   VALUES (2130, 'ground', 1003);
INSERT INTO Cinema   VALUES (2140, 'ground', 1004);
INSERT INTO Cinema   VALUES (2150, 'ground', 1005);
INSERT INTO Cinema   VALUES (2160, 'ground', 1006);

INSERT INTO GiftStore    VALUES (2210, 'ground', 1001);
INSERT INTO GiftStore    VALUES (2220, 'ground', 1002);
INSERT INTO GiftStore    VALUES (2230, 'ground', 1003);
INSERT INTO GiftStore    VALUES (2240, 'ground', 1004);
INSERT INTO GiftStore    VALUES (2250, 'ground', 1005);
INSERT INTO GiftStore    VALUES (2260, 'ground', 1006);

INSERT INTO Activity    VALUES (4011, 'Welcome to your family reunion!', '2022-03-02', 2011);
INSERT INTO Activity    VALUES (4021, 'Meet Big Blue!', '2010-05-01', 2022);
INSERT INTO Activity    VALUES (4022, 'The Curious World of Seaweed', '2022-05-15', 2021);
INSERT INTO Activity    VALUES (4031, 'The Great Hall Renewal Project', '2020-12-29', 2031);
INSERT INTO Activity    VALUES (4041, 'Vancouver in Fashion, the 1950s', '2022-04-09', 2041);
INSERT INTO Activity    VALUES (4051, '125 Years of Women’s Golf in BC', '2017-10-18', 2051);
INSERT INTO Activity    VALUES (4061, 'Rabbit Lane: Douglas Coupland', '2022-03-30', 2061);
INSERT INTO Activity    VALUES (4062, 'A Twist of the Rules: The Architecture of Paul Merrick', '2022-06-08', 2061);
INSERT INTO Activity    VALUES (4063, 'Martha Sturdy: All Fall Down', '2022-10-22', 2061);

INSERT INTO Guide1   VALUES (9201, 'Bob', 'archaeology');
INSERT INTO Guide1   VALUES (9202, 'Mary', 'anthropology');
INSERT INTO Guide1   VALUES (9203, 'Tim', 'architecture');
INSERT INTO Guide1   VALUES (9204, 'Stephanie', 'paleobiology');
INSERT INTO Guide1   VALUES (9205, 'Becky', 'art');
INSERT INTO Guide1   VALUES (9206, 'Irene', 'fashion');
INSERT INTO Guide1   VALUES (9207, 'Ben', 'golf history');

INSERT INTO Guide2   VALUES ('archaeology', 'archaeology office');
INSERT INTO Guide2   VALUES ('anthropology', 'anthropology office');
INSERT INTO Guide2   VALUES ('architecture', 'architecture office');
INSERT INTO Guide2   VALUES ('biology', 'biology office');
INSERT INTO Guide2   VALUES ('art', 'art office');
INSERT INTO Guide2   VALUES ('fashion', 'fashion office');
INSERT INTO Guide2   VALUES ('golf history', 'golf office');

INSERT INTO Exhibits2   VALUES ('archaeology', 9201);
INSERT INTO Exhibits2   VALUES ('anthropology', 9202);
INSERT INTO Exhibits2   VALUES ('architecture', 9203);
INSERT INTO Exhibits2   VALUES ('paleobiology', 9204);
INSERT INTO Exhibits2   VALUES ('art', 9205);
INSERT INTO Exhibits2   VALUES ('fashion', 9206);
INSERT INTO Exhibits2   VALUES ('golf history', 9207);

INSERT INTO Exhibits3   VALUES (6001, 'Nail Pond, Prince Edward Island, Canada', '2010', 'Big whale', 2022, 9201);
INSERT INTO Exhibits3   VALUES (6002, NULL, NULL, 'Human Skull',2011, 9202);
INSERT INTO Exhibits3   VALUES (6003, 'British Columbia, Canada', '1973', 'Eagle Harbour', 2061, 9203);
INSERT INTO Exhibits3   VALUES (6004, 'British Columbia, Canada', '2000', 'Sculpture',2061, 9205);
INSERT INTO Exhibits3   VALUES (6005, 'British Columbia, Canada', '1998', 'Girlfriend in a Coma', 2061, 9205);
INSERT INTO Exhibits3   VALUES (6006, 'Canada', NULL, 'The Curious World of Seaweed', 2021, 9201);
INSERT INTO Exhibits3   VALUES (6007, 'British Columbia, Canada', NULL , 'The Great Hall in Renewal', 2031, 9203);
INSERT INTO Exhibits3   VALUES (6008, 'Canada', '1950', 'Outfit 1 of Women’s 1950s fashion', 2041, 9206);
INSERT INTO Exhibits3   VALUES (6009, 'Canada', '1950', 'Outfit 2 of Women’s 1950s fashion', 2041, 9206);
INSERT INTO Exhibits3   VALUES (6010, 'Canada', '1950', 'Outfit 3 of Women’s 1950s fashion', 2041, 9206);
INSERT INTO Exhibits3   VALUES (6011, 'Canada', NULL, 'Golf trophies', 2051, 9207);
INSERT INTO Exhibits3   VALUES (6012, 'Canada', NULL, 'Golf clothing 125 years ago', 2051, 9207);

INSERT INTO Exhibits4   VALUES (1001, 2011);
INSERT INTO Exhibits4   VALUES (1001, 2012);
INSERT INTO Exhibits4   VALUES (1002, 2021);
INSERT INTO Exhibits4   VALUES (1002, 2022);
INSERT INTO Exhibits4   VALUES (1003, 2031);
INSERT INTO Exhibits4   VALUES (1004, 2041);
INSERT INTO Exhibits4   VALUES (1005, 2051);
INSERT INTO Exhibits4   VALUES (1006, 2061);

INSERT INTO Author1    VALUES (9101, 'Dr. Andrew Trites', 'Canada');
INSERT INTO Author1    VALUES (9102, 'Josie Iselin', NULL);
INSERT INTO Author1    VALUES (9103, 'Martha Sturdy', NULL);
INSERT INTO Author1    VALUES (9104, 'Paul Merrick', NULL);
INSERT INTO Author1    VALUES (9105, 'Douglas Coupland', NULL);
INSERT INTO Author1    VALUES (9106, 'Arthur Erickson', 'Canada');
INSERT INTO Author1    VALUES (9107, 'Ivan Sayers', 'Canada');
INSERT INTO Author1    VALUES (9108, 'Brent Franklin', 'Canada');
INSERT INTO Author1    VALUES (9109, 'Holly Horwood', 'Canada');
INSERT INTO Author1    VALUES (9110, 'Homo Sapiens', NULL);

INSERT INTO Author2    VALUES (9101, 6001);
INSERT INTO Author2    VALUES (9102, 6006);
INSERT INTO Author2    VALUES (9103, 6004);
INSERT INTO Author2    VALUES (9104, 6003);
INSERT INTO Author2    VALUES (9105, 6005);
INSERT INTO Author2    VALUES (9106, 6007);
INSERT INTO Author2    VALUES (9107, 6008);
INSERT INTO Author2    VALUES (9107, 6009);
INSERT INTO Author2    VALUES (9107, 6010);
INSERT INTO Author2    VALUES (9108, 6011);
INSERT INTO Author2    VALUES (9109, 6012);
INSERT INTO Author2    VALUES (9110, 6002);

INSERT INTO Participate   VALUES (4011, 9202);
INSERT INTO Participate   VALUES (4021, 9201);
INSERT INTO Participate   VALUES (4022, 9205);
INSERT INTO Participate   VALUES (4031, 9203);
INSERT INTO Participate   VALUES (4041, 9206);
INSERT INTO Participate   VALUES (4051, 9207);
INSERT INTO Participate   VALUES (4061, 9205);
INSERT INTO Participate   VALUES (4062, 9203);
INSERT INTO Participate   VALUES (4063, 9205);

INSERT INTO Film   VALUES (5111, 20, 'Where did we come from?');
INSERT INTO Film   VALUES (5121, 60, 'Story of the Big Blue');
INSERT INTO Film   VALUES (5122, 30, 'Seaweeds!');
INSERT INTO Film   VALUES (5131, 30, 'History of the Great Hall');
INSERT INTO Film   VALUES (5141, 30, 'Fashion in the 1950s');
INSERT INTO Film   VALUES (5151, 30, 'Golf Glory');
INSERT INTO Film   VALUES (5161, 45, 'Background of Douglas Coupland');
INSERT INTO Film   VALUES (5162, 20, 'Background of Paul Merrick');
INSERT INTO Film   VALUES (5163, 25, 'Background of Martha Sturdy');

INSERT INTO Play   VALUES ('13:30', 5111, 2110);
INSERT INTO Play   VALUES ('14:00', 5121, 2120);
INSERT INTO Play   VALUES ('13:00', 5122, 2120);
INSERT INTO Play   VALUES ('11:30', 5131, 2130);
INSERT INTO Play   VALUES ('11:45', 5141, 2140);
INSERT INTO Play   VALUES ('13:15', 5151, 2150);
INSERT INTO Play   VALUES ('15:00', 5161, 2160);
INSERT INTO Play   VALUES ('16:00', 5162, 2160);
INSERT INTO Play   VALUES ('14:30', 5163, 2160);

INSERT INTO About   VALUES (5111, 6002);
INSERT INTO About   VALUES (5121, 6001);
INSERT INTO About   VALUES (5122, 6006);
INSERT INTO About   VALUES (5131, 6007);
INSERT INTO About   VALUES (5141, 6008);
INSERT INTO About   VALUES (5141, 6009);
INSERT INTO About   VALUES (5141, 6010);
INSERT INTO About   VALUES (5151, 6011);
INSERT INTO About   VALUES (5151, 6012);
INSERT INTO About   VALUES (5161, 6005);
INSERT INTO About   VALUES (5162, 6003);
INSERT INTO About   VALUES (5163, 6004);

INSERT INTO Souvenir   VALUES (5201, 'Big Whale Key Chain', 20.0);
INSERT INTO Souvenir   VALUES (5202, 'Dinosaur Mug', 15.5);
INSERT INTO Souvenir   VALUES (5203, 'Love Martha Postcard', 5.0);
INSERT INTO Souvenir   VALUES (5204, 'Golf Ball', 30.0);
INSERT INTO Souvenir   VALUES (5205, 'Paul’s Building Micromodel', 150.0);
INSERT INTO Souvenir   VALUES (5206, 'Your Skull Model', 80.0);
INSERT INTO Souvenir   VALUES (5207, 'Great Hall Blueprint', 35.0);
INSERT INTO Souvenir   VALUES (5208, 'Handcrafted gifts', 15.0);

INSERT INTO Sell   VALUES (100, 5201, 2220);
INSERT INTO Sell   VALUES (50, 5202, 2220);
INSERT INTO Sell   VALUES (100, 5203, 2260);
INSERT INTO Sell   VALUES (30, 5204, 2250);
INSERT INTO Sell   VALUES (35, 5205, 2260);
INSERT INTO Sell   VALUES (80, 5206, 2210);
INSERT INTO Sell   VALUES (100, 5207, 2230);
INSERT INTO Sell   VALUES (200, 5208, 2240);