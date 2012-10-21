drop table code;
create table code (
	category varchar(20),
	code tinyint,
	label varchar(50),
	primary key (category, code)
);
insert into code values('sales_management', 1, 'コース');
insert into code values('sales_management', 2, '指名');
insert into code values('sales_management', 3, '割引');
insert into code values('weekday', 0, '月曜日');
insert into code values('weekday', 1, '火曜日');
insert into code values('weekday', 2, '水曜日');
insert into code values('weekday', 3, '木曜日');
insert into code values('weekday', 4, '金曜日');
insert into code values('weekday', 5, '土曜日');
insert into code values('weekday', 6, '日曜日');

insert into code values('resigned', 0, '');
insert into code values('resigned', 1, '退職');

insert into code values('authority', 1, '従業員');
insert into code values('authority', 10, '管理者');


drop table employee;
create table employee (
	employee_id varchar(10),
	password varchar(10) not null,
	name varchar(10) not null,
	email varchar(50),
	share tinyint not null,
	authority tinyint not null,
	sort smallint,
	resigned tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	primary key (employee_id)
);
insert into employee values('smile', 'smileasdf', '管理者', null, 0, 10, 1, null, now(),'smile');
insert into employee values('rika', 'password', 'リカ', null, 50, 0, 2, null, now(),'smile');
insert into employee values('toh', 'password', 'トゥ', null, 50, 0, 3, null, now(),'smile');
insert into employee values('yuri', 'password', 'ユリ', null, 50, 0, 4, null, now(),'smile');
insert into employee values('napa', 'password', 'ナパー', null, 50, 0, 5, null, now(),'smile');
insert into employee values('mika', 'password', 'ミカ', null, 50, 0, 6, null, now(),'smile');
insert into employee values('dao', 'password', 'ダォ', null, 50, 0, 7, null, now(),'smile');
insert into employee values('nana', 'password', 'ナナ', null, 50, 0, 8, null, now(),'smile');
insert into employee values('yuki', 'password', 'ゆき', null, 50, 0, 9, null, now(),'smile');

drop table course_class;
create table course_class (
	course_class_id smallint auto_increment,
	name varchar(20) not null,
	sort smallint,
	deleted tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	primary key (course_class_id)
);
insert into course_class values(1, 'タイ古式',    1, null, now(), 'smile');
insert into course_class values(2, 'タイ古式VIP', 2, null, now(), 'smile');
insert into course_class values(3, 'オイル',      3, null, now(), 'smile');
insert into course_class values(4, 'オイルVIP',   4, null, now(), 'smile');
insert into course_class values(5, 'セット',      5, null, now(), 'smile');

drop table course;
create table course (
	course_class_id smallint,
	course_id smallint auto_increment,
	name varchar(30) not null,
	charge smallint not null,
	time smallint not null,
	sort smallint,
	deleted tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	foreign key (course_class_id) references course_class(course_class_id) on delete restrict,
	primary key (course_class_id, course_id)
);
insert into course values(1, 1, '30分男性',  3000,   30, 1, null, now(), 'smile');
insert into course values(1, 2, '30分女性',  3000,   30, 2, null, now(), 'smile');
insert into course values(1, 3, '70分男性',  6000,   70, 3, null, now(), 'smile');
insert into course values(1, 4, '70分女性',  5000,   70, 4, null, now(), 'smile');
insert into course values(1, 5, '100分男性', 9000,  100, 5, null, now(), 'smile');
insert into course values(1, 6, '100分女性', 7000,  100, 6, null, now(), 'smile');
insert into course values(1, 7, '130分男性', 12000, 130, 7, null, now(), 'smile');
insert into course values(1, 8, '130分女性', 9000,  130, 8, null, now(), 'smile');
insert into course values(2, 1, '70分男性',  9000,   70, 1, null, now(), 'smile');
insert into course values(2, 2, '70分女性',  7000,   70, 2, null, now(), 'smile');
insert into course values(2, 3, '100分男性', 12000, 100, 3, null, now(), 'smile');
insert into course values(2, 4, '100分女性', 9000,  100, 4, null, now(), 'smile');
insert into course values(2, 5, '130分男性', 14000, 130, 5, null, now(), 'smile');
insert into course values(2, 6, '130分女性', 11000, 130, 6, null, now(), 'smile');
insert into course values(3, 1, '70分男性',  8000,   70, 1, null, now(), 'smile');
insert into course values(3, 2, '70分女性',  7000,   70, 2, null, now(), 'smile');
insert into course values(3, 3, '100分男性', 12000, 100, 3, null, now(), 'smile');
insert into course values(3, 4, '100分女性', 9000,  100, 4, null, now(), 'smile');
insert into course values(3, 5, '130分男性', 15000, 130, 5, null, now(), 'smile');
insert into course values(3, 6, '130分女性', 11000, 130, 6, null, now(), 'smile');
insert into course values(4, 1, '70分男性',  10000,  70, 1, null, now(), 'smile');
insert into course values(4, 2, '70分女性',  9000,   70, 2, null, now(), 'smile');
insert into course values(4, 3, '100分男性', 15000, 100, 3, null, now(), 'smile');
insert into course values(4, 4, '100分女性', 12000, 100, 4, null, now(), 'smile');
insert into course values(4, 5, '130分男性', 20000, 130, 5, null, now(), 'smile');
insert into course values(4, 6, '130分女性', 15000, 130, 6, null, now(), 'smile');
insert into course values(5, 1, '100分男性', 11000, 100, 1, null, now(), 'smile');
insert into course values(5, 2, '100分女性', 10000, 100, 2, null, now(), 'smile');
insert into course values(5, 3, '130分男性', 14000, 130, 3, null, now(), 'smile');
insert into course values(5, 4, '130分女性', 12000, 130, 4, null, now(), 'smile');

drop table course_extension;
create table course_extension (
	course_class_id smallint,
	course_extension_id smallint auto_increment,
	name varchar(30) not null,
	charge smallint not null,
	time smallint not null,
	sort smallint,
	deleted tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	foreign key (course_class_id) references course_class(course_class_id) on delete restrict,
	primary key (course_class_id, course_extension_id)
);
insert into course_extension values(1, 1, '延長10分', 1000, 10, 1, null, now(), 'smile');
insert into course_extension values(1, 2, '延長20分', 2000, 20, 2, null, now(), 'smile');
insert into course_extension values(1, 3, '延長30分', 3000, 30, 3, null, now(), 'smile');
insert into course_extension values(2, 1, '延長30分', 3000, 30, 1, null, now(), 'smile');
insert into course_extension values(3, 1, '延長30分', 4000, 30, 1, null, now(), 'smile');
insert into course_extension values(4, 1, '延長30分', 5000, 30, 1, null, now(), 'smile');
insert into course_extension values(5, 1, '古式30分', 3000, 30, 1, null, now(), 'smile');
insert into course_extension values(5, 2, 'オイル30分', 4000, 30, 1, null, now(), 'smile');

drop table discount;
create table discount (
	discount_id smallint auto_increment,
	name varchar(30) not null,
	charge smallint not null,
	share tinyint not null,
	sort smallint,
	deleted tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	primary key (discount_id)
);
insert into discount values(1, '5時まで割引', 1000, 50, 1, null, now(), 'smile');
insert into discount values(2, 'スタンプ割引', 1000, 0, 2, null, now(), 'smile');
insert into discount values(3, '割引券', 1000, 0, 3, null, now(), 'smile');
insert into discount values(4, 'イベント割引', 1000, 0, 4, null, now(), 'smile');
insert into discount values(5, '接待割500', 500, 0, 5, null, now(), 'smile');
insert into discount values(6, '接待割1000', 1000, 0, 6, null, now(), 'smile');
insert into discount values(7, '接待割2000', 2000, 0, 7, null, now(), 'smile');
insert into discount values(8, '接待割4000', 4000, 0, 8, null, now(), 'smile');

drop table optionmenu;
create table optionmenu (
	optionmenu_id smallint auto_increment,
	name varchar(30) not null,
	charge smallint not null,
	share tinyint not null,
	sort smallint,
	deleted tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	primary key (optionmenu_id)
);
insert into optionmenu values(1, '1000円オプション', 1000, 50, 1, null, now(), 'smile');
insert into optionmenu values(2, '2000円オプション', 2000, 50, 2, null, now(), 'smile');

drop table appoint;
create table appoint (
	appoint_id smallint auto_increment,
	name varchar(30) not null,
	charge smallint not null,
	share tinyint not null,
	sort smallint,
	deleted tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	primary key (appoint_id)
);
insert into appoint values(1, '指名', 1000, 100, 1, null, now(), 'smile');

drop table sales_slip_head;
create table sales_slip_head (
	slip_id int auto_increment,
	day date not null,
	employee_id varchar(10) not null,
	member_id varchar(10),
	start_time time not null,
	end_time time not null,
	course_class_id smallint not null,
	course_id smallint not null,
	course_charge smallint not null,
	course_extension_id smallint,
	course_extension_charge smallint,
	appoint_id smallint,
	appoint_charge smallint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	foreign key (employee_id) references employee(employee_id) on delete restrict,
	index idx_day_employee_id(day, employee_id),
	primary key (slip_id)
);

drop table sales_slip_discount;
create table sales_slip_discount (
	slip_id int,
	detail_id smallint,
	discount_id smallint not null,
	charge smallint not null,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	foreign key (slip_id) references sales_slip_head(slip_id) on delete cascade,
	primary key (slip_id, detail_id)
);

drop table sales_slip_optionmenu;
create table sales_slip_optionmenu (
	slip_id int,
	detail_id smallint,
	optionmenu_id smallint not null,
	charge smallint not null,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	foreign key (slip_id) references sales_slip_head(slip_id) on delete cascade,
	primary key (slip_id, detail_id)
);

drop table payment_slip_head;
create table payment_slip_head (
	slip_id int auto_increment,
	day date not null,
	payee varchar(20),
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	primary key (slip_id)
);

drop table payment_slip_detail;
create table payment_slip_detail (
	slip_id int,
	detail_id smallint,
	account smallint not null,
	name varchar(20) not null,
	unit_price int not null,
	amount smallint not null,
	comment varchar(20),
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	foreign key (slip_id) references payment_slip_head(slip_id) on delete cascade,
	primary key (slip_id, detail_id)
);

drop table account;
create table account (
	id smallint,
	kind smallint not null,
	name varchar(20) not null,
	sort smallint,
	deleted tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	primary key (id)
);
insert into account values(1, 1, '給料賃金', null, null, now(), 'smile');
insert into account values(2, 1, '減価償却費', null, null, now(), 'smile');
insert into account values(3, 1, '地代家賃', null, null, now(), 'smile');
insert into account values(4, 1, '租税公課', null, null, now(), 'smile');
insert into account values(5, 1, '水道光熱費', null, null, now(), 'smile');
insert into account values(6, 1, '旅費交通費', null, null, now(), 'smile');
insert into account values(7, 1, '通信費', null, null, now(), 'smile');
insert into account values(8, 1, '広告宣伝費', null, null, now(), 'smile');
insert into account values(9, 1, '接待交際費', null, null, now(), 'smile');
insert into account values(10, 1, '修繕費', null, null, now(), 'smile');
insert into account values(11, 1, '消耗品費', null, null, now(), 'smile');
insert into account values(12, 1, 'リース代', null, null, now(), 'smile');
insert into account values(13, 1, 'おしぼり代', null, null, now(), 'smile');
insert into account values(14, 1, '雑費', null, null, now(), 'smile');

drop table close_head;
create table close_head (
	slip_id int,
	day date not null,
	employee_id varchar(10) not null,
	member_id varchar(10),
	start_time time,
	end_time time,
	course_class_id smallint,
	course_id smallint,
	course_charge smallint not null,
	course_charge_employee smallint not null,
	course_extension_id smallint,
	course_extension_charge smallint null,
	course_extension_charge_employee smallint null,
	appoint_id smallint,
	appoint_charge smallint,
	appoint_charge_employee smallint,
	tax smallint,
	payment_slip_id int not null,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	foreign key (slip_id) references sales_slip_head(slip_id) on delete restrict,
	foreign key (employee_id) references employee(employee_id) on delete restrict,
	index idx_slip_id(slip_id),
	index idx_payment_slip_id(payment_slip_id),
	index idx_day(day),
	index idx_day_employee_id(day, employee_id)
);

drop table close_optionmenu;
create table close_optionmenu (
	slip_id int not null,
	detail_id smallint not null,
	optionmenu_id smallint not null,
	charge smallint not null,
	charge_employee smallint not null,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	index idx_slip_id(slip_id),
	index idx_slip_id_detail_id(slip_id, detail_id)
);

drop table close_discount;
create table close_discount (
	slip_id int not null,
	detail_id smallint not null,
	discount_id smallint not null,
	charge smallint not null,
	charge_employee smallint not null,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	index idx_slip_id(slip_id),
	index idx_slip_id_detail_id(slip_id, detail_id)
);

drop table mail_customer;
create table mail_customer (
	id int auto_increment,
	name varchar(20),
	mailaddress varchar(100),
	deleted tinyint,
	up_day datetime not null,
	up_employee_id varchar(10) not null,
	primary key (id)
);

drop table mail_setting;
create table mail_setting (
	smtp varchar(100),
	port varchar(5),
	sendaddress varchar(100),
	sendname varchar(20),
	userid varchar(20),
	password varchar(20)
);
