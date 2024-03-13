create database cinema;
use cinema;
create table Adminn(
	AID varchar(20) primary key not null,
    APassword varchar(20) not null
);
create table Hall(
	hallId varchar(20) primary key not null,
    AID VARCHAR(20),
    FOREIGN KEY (AID) REFERENCES Adminn(AID) 
);

create table Customer(
    userName varchar(50) primary key not null
);

create table Movie(
	mID varchar(20) primary key not null,
    mName varchar(20) not null,
	moviePoster BLOB,
	AID VARCHAR(20),
	FOREIGN KEY (AID) REFERENCES Adminn(AID) ,
    userName varchar(20),
    foreign key (userName) references Customer(userName)
    
);

create table seat (
-- 	seatID varchar(20) primary key not null,
    sQuantity int primary key not null,
    userName varchar(20),
    foreign key (userName) references Customer(userName)
);

create table food(
    fName varchar(20) primary key,
    foodPhoto blob,
    fQuantity int
	
);

CREATE TABLE pay (
    -- trID VARCHAR(20) PRIMARY KEY NOT NULL,
	userName VARCHAR(20) primary key,
    amount INT,
    mName VARCHAR(20),
    FOREIGN KEY (userName) REFERENCES customer(userName),
    FOREIGN KEY (mName) REFERENCES movie(mID)
);

-- Drop foreign key constraint
ALTER TABLE Movie
DROP FOREIGN KEY movie_ibfk_2;

-- Drop the userName column
ALTER TABLE Movie
DROP COLUMN userName;

