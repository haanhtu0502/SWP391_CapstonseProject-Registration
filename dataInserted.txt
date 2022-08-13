use master

CREATE DATABASE SWP391
DROP DATABASE SWP391
-- SELECT * FROM Student
-- SELECT * FROM Lecturer
-- SELECT * FROM Semester
-- SELECT * FROM Users
-- SELECT * FROM Category
-- SELECT * FROM Roles

USE  SWP391

CREATE TABLE Roles (
	RoleId INT PRIMARY KEY,
	RoleName VARCHAR(30),
)

CREATE TABLE Category (
    CategoryId INT PRIMARY KEY,
    CategoryName VARCHAR(30),
)

CREATE TABLE Department (
	DepartmentId INT PRIMARY KEY,
	Name VARCHAR(30),	
)

CREATE TABLE Users (
    UserId INT PRIMARY KEY,
    Name VARCHAR(30),
    Password VARCHAR(30) NOT NULL,
    Status INT,
	DepartmentId INT FOREIGN KEY (DepartmentId) REFERENCES Department(DepartmentId),
    Email VARCHAR(50) UNIQUE,
	RoleId INT,
	FOREIGN KEY (RoleId) REFERENCES Roles(RoleId),
	Cookie VARCHAR(10)	
)

CREATE TABLE Semester (
    SemesterId INT PRIMARY KEY,
    Name VARCHAR(30),
    StartDate DATE,
    EndDate DATE
)

CREATE TABLE Topic(
	TopicId INT PRIMARY KEY,
	Name VARCHAR(30),
	CategoryId INT FOREIGN KEY (CategoryId) REFERENCES Category(CategoryId),
	Description VARCHAR(200),
	BusinessId INT FOREIGN KEY (BusinessId) REFERENCES Users(UserId),
	DepartmentId INT FOREIGN KEY (DepartmentId) REFERENCES Department(DepartmentId),
	SemesterId INT FOREIGN KEY (SemesterId) REFERENCES Semester(SemesterId),
	Status INT
)

CREATE TABLE LecturerTopic (
	ID INT PRIMARY KEY,
	LecturerId INT NOT NULL,
	FOREIGN KEY (LecturerId) REFERENCES Users(UserId),
	TopicId INT NOT NULL,
	FOREIGN KEY (TopicId) REFERENCES Topic(TopicId)
)

CREATE TABLE Groups (
	GroupId INT PRIMARY KEY not null,
	GroupName VARCHAR(30),
	SemID INT FOREIGN KEY (SemID) REFERENCES Semester(SemesterId),
	groupStatus VARCHAR(10),
	members INT,
	TopicStatus INT,	
)

CREATE TABLE StudentGroup (
	Id INT PRIMARY KEY not null,
	StudentId INT NOT NULL FOREIGN KEY (StudentId) REFERENCES Users(UserId),
	GroupId INT NOT NULL FOREIGN KEY (GroupId) REFERENCES Groups(GroupId),
	LeaderStatus INT,
)

CREATE TABLE PendingGroupTopic (
	Id INT identity(1,1) PRIMARY KEY not null,
	GroupId INT NOT NULL FOREIGN KEY (GroupId) REFERENCES Groups(GroupId),
	TopicId INT NOT NULL FOREIGN KEY (TopicId) REFERENCES Topic(TopicId),	
	DepartmentId INT FOREIGN KEY (DepartmentId) REFERENCES Department(DepartmentId)
)


CREATE TABLE Project(
	ProjectId INT PRIMARY KEY,
	Description VARCHAR(200),
	Name VARCHAR(30),
	SourceCode VARCHAR(200),
	TopicId INT FOREIGN KEY(TopicId) REFERENCES Topic(TopicId),
	Status INT,
	GroupId INT NOT NULL FOREIGN KEY(GroupId) REFERENCES Groups(GroupId)
)

CREATE TABLE ProjectLecturer(
	Id INT PRIMARY KEY,
	ProjectId INT NOT NULL FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId),
	LecturerId INT NOT NULL FOREIGN KEY (LecturerId) REFERENCES Users(UserId)
)

insert into Department values(1,'Quan tri kinh doanh')
insert into Department values(2,'Cong nghe thong tin')
insert into Department values(3,'Ngon ngu Anh')
insert into Department values(4,'Ngon ngu Han Quoc')
insert into Department values(5,'Ngon ngu Nhat')

select * from Department

insert into Category values(1,'Digital Marketing')
insert into Category values(2,'Kinh doanh quoc te')
insert into Category values(3,'Quan tri khach san')
insert into Category values(4,'du lich & lu hanh')
insert into Category values(5,'Truyen thong da phuong tien')
insert into Category values(6,'Ky thuat phan mem')
insert into Category values(7,'He thong thong tin')
insert into Category values(8,'Tri tue nhan tao')
insert into Category values(9,'An toan thong tin')
insert into Category values(10,'Thiet ke do hoa')
insert into Category values(11,'Internet of things')
insert into Category values(12,'Phan mem o to')
insert into Category values(13,'Xu li dich vu so')
insert into Category values(14,'Ngon ngu Anh')
insert into Category values(15,'Ngon ngu Nhat')
insert into Category values(16,'Ngon ngu Han Quoc')

select * from Category

insert into  Roles Values (1, 'Student')
insert into  Roles Values (2, 'Lecturer')
insert into  Roles Values (3, 'Business')

select * from Roles

insert into Users values(1,'Le Hai Long','12345678',1,2,'lehailong@gmail.com',1,null)
insert into Users values(2,'Pham Cong Minh','12345678',1,2,'phamcongminh@gmail.com',1,null)
insert into Users values(3,'Ha Anh Tu','12345678',1,2,'haanhtu@gmail.com',1,null)
insert into Users values(4,'Pham Nguyen Hung Anh','12345678',1,2,'hunganh@gmail.com',1,null)
insert into Users values(5,'Pham Hong Quang','12345678',1,2,'phamhongquang@gmail.com',1,null)
select * from Users


insert into Users values(6,'Lam Huu Khanh Phuong','12345678',1,2,'phuonglhk@fe.edu.vn',2,null)

insert into Users Values (7, 'FPT','12345678',null,null,'FPT@gmail.com',3,null)
insert into Users Values (8, 'FPT Software HCM','12345678',null,null,'FPTsotfware@gmail.com',3,null)
insert into Users Values (9, 'Cty TNHH Job Test','12345678',null,null,'jobtest@gmail.com',3,null)
insert into Users Values (10, 'Cty SmartPay','12345678',null,null,'smartpay@gmail.com',3,null)
insert into Users Values (11, 'NashTech Global','12345678',null,null,'nashtech@gmail.com',3,null)


--more students
insert into Users values(12,'Nguyen Van A','12345678',1,2,'ngguyenvana@gmail.com',1,null)
insert into Users values(13,'Pham Van B','12345678',1,2,'phamvanb@gmail.com',1,null)
insert into Users values(14,'Ha Van C','12345678',1,2,'havanc@gmail.com',1,null)
insert into Users values(15,'Pham Phu D','12345678',1,2,'phamphud@gmail.com',1,null)
insert into Users values(16,'Le Van E','12345678',1,2,'levane@gmail.com',1,null)

insert into Users values(17,'Vu Anh Thuyen','12345678',1,3,'vuanhthuyen@gmail.com',1,null)
insert into Users values(18,'Vien Quoc Binh','12345678',1,3,'vienquocbinh@gmail.com',1,null)
insert into Users values(19,'Huynh Chau Hai Trieu','12345678',1,3,'huynhchauhaitrieu@gmail.com',1,null)
insert into Users values(20,'Tham Hoang Minh','12345678',1,3,'thamhoangminh@gmail.com',1,null)
insert into Users values(21,'Lam Tuan Lac','12345678',1,3,'lamtuanlac@gmail.com',1,null)


insert into Users values(22,'Vu Anh T','12345678',1,3,'vuanht@gmail.com',1,null)
insert into Users values(23,'Vien Quoc B','12345678',1,3,'vienquocb@gmail.com',1,null)
insert into Users values(24,'Huynh Chau Hai T','12345678',1,3,'huynhchauhait@gmail.com',1,null)
insert into Users values(25,'Tham Hoang M','12345678',1,3,'thamhoangm@gmail.com',1,null)
insert into Users values(26,'Lam Tuan L','12345678',1,3,'lamtuanl@gmail.com',1,null)
--must more
insert into Users values(27,'Vu Anh Binh','12345678',1,5,'vuanhbinh@gmail.com',1,null)
insert into Users values(28,'Vien Quoc Ca','12345678',1,5,'vienquocca@gmail.com',1,null)
insert into Users values(29,'Huynh Chau Hai Chien','12345678',1,5,'huynhchauhaichien@gmail.com',1,null)
insert into Users values(30,'Tham Hoang Hau','12345678',1,5,'thamhoanghau@gmail.com',1,null)
insert into Users values(31,'Lam Lac Loi','12345678',1,5,'lamlacloi@gmail.com',1,null)

insert into Users values(32,'Lam Tuan Hung','12345678',1,2,'lamtuanhung@gmail.com',1,null)
insert into Users values(33,'Le Van Be','12345678',1,2,'levanbe@gmail.com',1,null)
insert into Users values(34,'Ha Van Ce','12345678',1,2,'havance@gmail.com',1,null)
insert into Users values(35,'Pham Phu De','12345678',1,2,'phamphude@gmail.com',1,null)
insert into Users values(36,'Le Van En','12345678',1,2,'levanen@gmail.com',1,null)

insert into Users values(37,'Vu Anh Trang','12345678',1,3,'vuanhtrang@gmail.com',1,null)
insert into Users values(38,'Vien Hoa Binh','12345678',1,3,'vienhoabinh@gmail.com',1,null)
insert into Users values(39,'Le Van Vo','12345678',1,3,'levanvo@gmail.com',1,null)
insert into Users values(40,'Nguyen Van Nhat','12345678',1,3,'nguyenvannhat@gmail.com',1,null)
insert into Users values(41,'le Tuan Lac','12345678',1,3,'letuanlac@gmail.com',1,null)

insert into Users values(42,'Nguyen Van An','12345678',1,1,'ngguyenvanan@gmail.com',1,null)
insert into Users values(43,'Pham Van Bi','12345678',1,1,'phamvanbi@gmail.com',1,null)
insert into Users values(44,'Ha Van Ci','12345678',1,1,'havanci@gmail.com',1,null)
insert into Users values(45,'Pham Phu Di','12345678',1,1,'phamphudi@gmail.com',1,null)
insert into Users values(46,'Le Van Em','12345678',1,1,'levanem@gmail.com',1,null)

insert into Users values(47,'Vu Anh Tuyen','12345678',1,5,'vuanhtuyen@gmail.com',1,null)
insert into Users values(48,'Vien Quoc Bin','12345678',1,5,'vienquocbin@gmail.com',1,null)
insert into Users values(49,'Chau Hai Trieu','12345678',1,5,'chauhaitrieu@gmail.com',1,null)
insert into Users values(50,'Tham Hoan Minh','12345678',1,5,'thamhoanminh@gmail.com',1,null)
insert into Users values(51,'Lan Tuan Lac','12345678',1,5,'lantuanlac@gmail.com',1,null)

insert into Users values(52,'binzet yeh a ho','12345678',1,3,'binzet@gmail.com',1,null)
insert into Users values(53,'bich phuong','12345678',1,3,'bichphuong@gmail.com',1,null)
insert into Users values(54,'Cha vit cot','12345678',1,3,'chavitcot@gmail.com',1,null)
insert into Users values(55,'Dam Vinh Hang','12345678',1,3,'damvinhhang@gmail.com',1,null)
insert into Users values(56,'Thong Soai Ca','12345678',1,3,'thongsoaica@gmail.com',1,null)


--more lecturer
insert into Users values(57,'Nguyen Bich Nga','12345678',1,1,'nganb@fe.edu.vn',2,Null)
insert into Users values(58,'Vu Hong Quan','12345678',1,3,'quanvh@fe.edu.vn',2,Null)
insert into Users values(59,'Tran Vu Quang','12345678',1,4,'quangtv@fe.edu.vn',2,Null)
insert into Users values(60,'Tran Thi Hong Bich','12345678',1,5,'bichtth@fe.edu.vn',2,Null)


--more new student
insert into Users values(61,'Le Quang Khai','12345678',1,4,'lequangkhai@gmail.com',1,null)
insert into Users values(62,'Tuan Soai Ca','12345678',1,4,'tuansoaica@gmail.com',1,null)

insert into Users values(63,'Nguyen Vo Minh Trung','12345678',1,2,'trungnvm@gmail.com',1,null)
insert into Users values(64,'Nguyen Le Nghia Hiep','12345678',1,2,'hiepnln@gmail.com',1,null)
insert into Users values(65,'Le Huu Trung Tin','12345678',1,2,'tinlht@gmail.com',1,null)
insert into Users values(66,'Nguyen Trung Hieu','12345678',1,2,'hieunt@gmail.com',1,null)
insert into Users values(67,'Le Tat Huy','12345678',1,2,'huylt@gmail.com',1,null)

insert into Users values(68,'Nguyen Minh Khang','12345678',1,2,'khangnm@gmail.com',1,null)
insert into Users values(69,'Phan Hong Ha','12345678',1,2,'haph@gmail.com',1,null)
insert into Users values(70,'Mai Thanh Phong','12345678',1,2,'phongmt@gmail.com',1,null)
insert into Users values(71,'Dao Hong Tin','12345678',1,2,'tindh@gmail.com',1,null)
insert into Users values(72,'Vu Duong Tuong Vi','12345678',1,2,'vivdt@gmail.com',1,null)

select * from Users



insert into Semester values(1,'SU2022','2022-05-29','2022-09-30')
insert into Semester values(2,'FA2021','2021-07-10','2021-10-10')
insert into Semester values(3,'SU2021','2021-05-28','2021-09-28')
insert into Semester values(4,'SP2022','2022-01-10','2022-05-10')

select * from Semester

delete from Semester


insert into Topic values(1,'CPManagement-Review',6,'Manage and review on this web',8,2,1,2)
insert into Topic values(2,'PetCareSystem',6,'The best solution to manage and service your pet',9,2,1,2)
insert into Topic values(3,'PT Tranning app',6,'The way to transform to the monster',11,2,1,2)
insert into Topic values(4,'Game Animation',10,'Like a little MCU movie',7,2,1,2)
insert into Topic values(5,'Research And Developing Brands',2,'Make and marketing a brand to users',7,1,1,2)
insert into Topic values(6,'Social issues',14,'Write an essay about social isues',10,3,1,2)
insert into Topic values(7,'An analysis on cultural',16,'Write an essay analyze cultural',11,4,1,2)
insert into Topic values(8,'Economic Policy of Japan',15,'Research and presentation',9,5,1,2)
insert into Topic values(9,'Restaurant researching',2,'Manage a restaurant',7,1,2,2)
insert into Topic values(10,'Cycle of whales',14,'Study about whales',10,3,2,2)
insert into Topic values(11,'Jewelry System',6,'Ecommerce jewelry selling website',8,2,1,2)
insert into Topic values(12,'Online CV builder',6,'Generate CV automatically',9,2,1,2)



select * from Topic

delete from Topic



insert into Groups values(1,'Beaky Blinders',1,'Public',5,2)
insert into Groups values(2,'Group 2',1,'Public',5,2)	
insert into Groups values(3,'Group 3',1,'Private',5,2)
insert into Groups values(4,'Showbit team',1,'Public',5,2)
insert into Groups values(5,'Group 5',1,'Public',5,2)
insert into Groups values(6,'Group 6',1,'Private',5,2)
insert into Groups values(7,'Group 7',1,'Public',5,2)
insert into Groups values(8,'Group 8',1,'Public',5,2)
insert into Groups values(9,'Group 9',2,'Public',5,0)
insert into Groups values(10,'Group 10',2,'Public',5,0)
insert into Groups values(11,'Group ABC',3,'Private',5,0)
insert into Groups values(12,'No name',3,'Private',5,0)
insert into Groups values(13,'Gr 15',4,'Public',5,0)
insert into Groups values(14,'G63',4,'Public',5,0)
insert into Groups values(15,'Eagles',1,'Public',5,0)
insert into Groups values(16,'Tigers',1,'Public',5,0)


select * from Groups

delete from Groups 




insert into StudentGroup values(1,1,1,1)
insert into StudentGroup values(2,2,1,0)
insert into StudentGroup values(3,3,1,0)
insert into StudentGroup values(4,4,1,0)
insert into StudentGroup values(5,5,1,0)
--more groups
insert into StudentGroup values(6,12,2,1)
insert into StudentGroup values(7,13,2,0)
insert into StudentGroup values(8,14,2,0)
insert into StudentGroup values(9,15,2,0)
insert into StudentGroup values(10,16,2,0)

insert into StudentGroup values(11,17,3,1)
insert into StudentGroup values(12,18,3,0)
insert into StudentGroup values(13,19,3,0)
insert into StudentGroup values(14,20,3,0)
insert into StudentGroup values(15,21,3,0)
--must more

insert into StudentGroup values(16,27,5,1)
insert into StudentGroup values(17,28,5,0)
insert into StudentGroup values(18,29,5,0)
insert into StudentGroup values(19,30,5,0)
insert into StudentGroup values(20,31,5,0)

insert into StudentGroup values(21,22,12,1)
insert into StudentGroup values(22,23,12,0)
insert into StudentGroup values(23,24,12,0)
insert into StudentGroup values(24,25,12,0)
insert into StudentGroup values(25,26,12,0)


insert into StudentGroup values(26,32,6,1)
insert into StudentGroup values(27,33,6,0)
insert into StudentGroup values(28,34,6,0)
insert into StudentGroup values(29,35,6,0)
insert into StudentGroup values(30,36,6,0)

insert into StudentGroup values(31,37,7,1)
insert into StudentGroup values(32,38,7,0)
insert into StudentGroup values(33,39,7,0)
insert into StudentGroup values(34,40,7,0)
insert into StudentGroup values(35,41,7,0)

insert into StudentGroup values(36,42,8,1)
insert into StudentGroup values(37,43,8,0)
insert into StudentGroup values(38,44,8,0)
insert into StudentGroup values(39,44,8,0)
insert into StudentGroup values(40,46,8,0)

insert into StudentGroup values(41,47,9,1)
insert into StudentGroup values(42,48,9,0)
insert into StudentGroup values(43,49,9,0)
insert into StudentGroup values(44,50,9,0)
insert into StudentGroup values(45,51,9,0)

insert into StudentGroup values(46,52,4,1)
insert into StudentGroup values(47,53,4,0)
insert into StudentGroup values(48,54,4,0)
insert into StudentGroup values(49,55,4,0)
insert into StudentGroup values(50,56,4,0)


insert into StudentGroup values(51,63,15,1)
insert into StudentGroup values(52,64,15,0)
insert into StudentGroup values(53,65,15,0)
insert into StudentGroup values(54,66,15,0)
insert into StudentGroup values(55,67,15,0)

insert into StudentGroup values(56,68,16,1)
insert into StudentGroup values(57,69,16,0)
insert into StudentGroup values(58,70,16,0)
insert into StudentGroup values(59,71,16,0)
insert into StudentGroup values(60,72,16,0)
select * from StudentGroup

insert into LecturerTopic values(1,6,1)
insert into LecturerTopic values(2,6,2)
insert into LecturerTopic values(3,6,3)
insert into LecturerTopic values(4,6,4)
insert into LecturerTopic values(5,57,5)
insert into LecturerTopic values(6,58,6)
insert into LecturerTopic values(7,59,7)
insert into LecturerTopic values(8,60,8)
insert into LecturerTopic values(9,57,9)
insert into LecturerTopic values(10,58,10)
insert into LecturerTopic values(11,6,11)
insert into LecturerTopic values(12,6,12)



select * from LecturerTopic

delete from LecturerTopic

insert into Project values(1,'Manage and review on this web','CPManagement-Review','https://github.com/phhgquang/SWP391',1,1,1)
insert into Project values(2,'The best solution to manage and service your pet','PetCareSystem','',2,1,2)
insert into Project values(3,'The way to transform to the monster','PT Tranning app','',3,1,3)
insert into Project values(4,'Like a little MCU movie','Game Animation','',4,1,4)
insert into Project values(5,'Make and marketing a brand to users','Research And Developing Brands','',5,1,5)
insert into Project values(6,'Write an essay about social isues','Social issues','',6,1,6)
insert into Project values(7,'Write an essay analyze cultural','An analysis on cultural','',7,1,7)
insert into Project values(8,'Research and presentation','Economic Policy of Japan','',8,1,8)



select * from Project
select * from topic
delete from Project 

insert into ProjectLecturer values(1,1,6)
insert into ProjectLecturer values(2,2,6)

select * from ProjectLecturer 