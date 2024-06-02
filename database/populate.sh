sqlplus <username>/<password>@//localhost:1521 << EOF

create table customer (
    cust_no varchar2(5) check (cust_no like 'C%'),
    name varchar2(20) not null,
    phone_no number(10),
    city varchar2(9) not null,
    primary key (cust_no)
);

insert into customer values ('C0001', 'Raj Anand Singh', 9861258466, 'Delhi');
insert into customer values ('C0002', 'Ankita Singh', '9879958651', 'Bangalore');
insert into customer values ('C0003', 'Soumya Jha', '9885623344', 'Mumbai');
insert into customer values ('C0004', 'Abhijit Mishra', '9455845425', 'Mumbai');
insert into customer values ('C0005', 'Yash Saraf', '9665854585', 'Kolkata');
insert into customer values ('C0006', 'Swaroop Ray', '9437855466', 'Chennai');
insert into customer values ('C0007', 'Surya Narayan', ' 9937955212', 'Gurgaon');
insert into customer values ('C0008', 'Pranav Praveen', '9336652441', 'Pune');
insert into customer values ('C0009', 'Stuti Mishra', '7870266534', 'Delhi');
insert into customer values ('C0010', 'Aslesha Tiwari', Null, 'Mumbai');

select * from customer;

create table branch (
    branch_code varchar2(4) primary key,
    branch_name varchar2(20) not null,
    branch_city varchar2(7) check (branch_city in ('Delhi', 'Mumbai', 'Kolkata', 'Chennai'))
);

insert into branch values ('B001', 'Janakpuri Branch', 'Delhi');
insert into branch values ('B002', 'Chadnichowk Branch', 'Delhi');
insert into branch values ('B003', 'Juhu Branch', 'Mumbai');
insert into branch values ('B004', 'Andheri Branch', 'Mumbai');
insert into branch values ('B005', 'Saltlake Branch', 'Kolkata');
insert into branch values ('B006', 'Srirampuram Branch', 'Chennai');

select * from branch;

create table account (
    account_no varchar2(5) check (account_no like 'A%'),
    type varchar2(2) check (type in ('SB', 'FD', 'CA')),
    balance number(7) check (balance < 10000000),
    branch_code varchar2(4),
    primary key (account_no),
    foreign key (branch_code) references Branch(branch_code)
);

insert into account values ('A0001', 'SB', 200000, 'B003');
insert into account values ('A0002', 'SB', 15000, 'B002');
insert into account values ('A0003', 'CA', 850000, 'B004');
insert into account values ('A0004', 'CA', 35000, 'B004');
insert into account values ('A0005', 'FD', 28500, 'B005');
insert into account values ('A0006', 'FD', 550000, 'B005');
insert into account values ('A0007', 'SB', 48000, 'B001');
insert into account values ('A0008', 'SB', 7200, 'B002');
insert into account values ('A0009', 'SB', 18750, 'B003');
insert into account values ('A0010', 'FD', 99000, 'B004');

select * from account;

create table depositor (
    cust_no varchar2(5) check (cust_no like 'C%'),
    account_no varchar2(5) check (account_no like 'A%'),
    primary key (cust_no, account_no),
    foreign key (cust_no) references Customer(cust_no),
    foreign key (account_no) references Account(account_no)
);

insert into depositor values ('C0003', 'A0001');
insert into depositor values ('C0004', 'A0001');
insert into depositor values ('C0004', 'A0002');
insert into depositor values ('C0006', 'A0003');
insert into depositor values ('C0006', 'A0004');
insert into depositor values ('C0001', 'A0005');
insert into depositor values ('C0002', 'A0005');
insert into depositor values ('C0010', 'A0006');
insert into depositor values ('C0009', 'A0007');
insert into depositor values ('C0008', 'A0008');
insert into depositor values ('C0007', 'A0009');
insert into depositor values ('C0006', 'A0010');

select * from depositor;

create table loan (
    loan_no varchar2(5) check (loan_no like 'L%'),
    primary key (loan_no),
    cust_no varchar2(5) check (cust_no like 'C%'),
    foreign key (cust_no) references Customer(cust_no),
    amount number(8) check (amount > 1000),
    branch_code varchar2(4),
    foreign key (branch_code) references Branch(branch_code)
);

insert into loan values ('L0001', 'C0005', 3000000, 'B006');
insert into loan values ('L0002', 'C0001', 50000, 'B005');
insert into loan values ('L0003', 'C0002', 8000000, 'B004');
insert into loan values ('L0004', 'C0010', 100000, 'B004');
insert into loan values ('L0005', 'C0009', 9500000, 'B005');
insert into loan values ('L0006', 'C0008', 25000, 'B006');

select * from loan;

create table installment (
    inst_no number(2) check (inst_no <= 10),
    loan_no varchar(5),
    inst_amount number(10),
    primary key (inst_no, loan_no),
    foreign key (loan_no) references loan(loan_no)
);

insert into installment values (1, 'L0005', 500000);
insert into installment values (1, 'L0002', 10000);
insert into installment values (1, 'L0003', 50000);
insert into installment values (1, 'L0004', 20000);
insert into installment values (2, 'L0005', 500000);
insert into installment values (1, 'L0006', 3000);
insert into installment values (2, 'L0002', 10000);
insert into installment values (3, 'L0002', 10000);
insert into installment values (2, 'L0003', 50000);
insert into installment values (2, 'L0004', 20000);

select * from installment;

EOF