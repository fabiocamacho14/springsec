create table users (
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,

    constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);

insert into users (username, password, enabled) values ('user', '{noop}EazyBytes@12345', 1);
insert into authorities (username, authority) values ('user', 'read');

insert into users (username, password, enabled) values ('admin', '{bcrypt}$2a$12$rcN9Y3prF2zUpRuD6w0CVOiblSUV5mGBYRS5.ExoT4crSFwNVZA7m', 1);
insert into authorities (username, authority) values ('admin', 'admin');

create table customer (
    id int primary key not null auto_increment,
    email varchar(45) not null,
    pwd varchar(200) not null,
    role varchar(45) not null
);

insert into customer (email, pwd, role) values('happy@example.com', '{noop}EazyBytes@12345', 'read');
insert into customer (email, pwd, role) values('admin@example.com',
                                               '{bcrypt}$2a$12$rcN9Y3prF2zUpRuD6w0CVOiblSUV5mGBYRS5
.ExoT4crSFwNVZA7m', 'admin');


# Start here
drop table authorities;
drop table customer;
drop table users;

create table customer (
                          customer_id int primary key not null auto_increment,
                          name varchar(100) not null,
                          email varchar(100) not null,
                          mobile_number varchar(20) not null,
                          pwd varchar(500) not null,
                          role varchar(100) not null,
                          create_dt date default null
);

desc customer;

insert into customer (name, email, mobile_number, pwd, role, create_dt)
values ('fabio', 'fabio@example.com', '40028922', '{bcrypt}$2a$12$Fv4KtGx78RLmb4xtckZoQ.x5yTHUiQV.SRfwsD1cMPbo7fE7IliyO', 'admin', curdate());

create table accounts (
                          customer_id int not null,
                          account_number int primary key not null,
                          account_type varchar(100) not null,
                          branch_address varchar(200) not null,
                          create_dt date default null
);

alter table accounts
    add constraint fk_accounts_customer foreign key (customer_id) references customer (customer_id) on delete cascade;

insert into accounts (customer_id, account_number, account_type, branch_address, create_dt)
values (1, '33677889', 'Savings', 'Rua Sebastião Pereira', curdate());

create table account_transactions (
                                      transaction_id varchar(200) primary key not null,
                                      account_number int not null,
                                      customer_id int not null,
                                      transaction_dt date not null,
                                      transaction_summary varchar(200) not null,
                                      transaction_type varchar(100) not null,
                                      transaction_amt int not null,
                                      closing_balance int not null,
                                      create_dt date default null
);

alter table account_transactions
    add constraint fk_account_transactions_accounts foreign key (account_number) references accounts (account_number);

alter table account_transactions
    add constraint fk_account_transactions_customer foreign key (customer_id) references customer (customer_id);

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 33677889, 1, DATE_SUB(CURDATE(), INTERVAL 7 DAY), 'Coffee Shop', 'Withdrawal', 30,34500,DATE_SUB(CURDATE(), INTERVAL 7 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 33677889, 1, DATE_SUB(CURDATE(), INTERVAL 6 DAY), 'Uber', 'Withdrawal', 100,34400,DATE_SUB(CURDATE(), INTERVAL 6 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 33677889, 1, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'Self Deposit', 'Deposit', 500,34900,DATE_SUB(CURDATE(), INTERVAL 5 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 33677889, 1, DATE_SUB(CURDATE(), INTERVAL 4 DAY), 'Ebay', 'Withdrawal', 600,34300,DATE_SUB(CURDATE(), INTERVAL 4 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 33677889, 1, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'OnlineTransfer', 'Deposit', 700,35000,DATE_SUB(CURDATE(), INTERVAL 2 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 33677889, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'Amazon.com', 'Withdrawal', 100,34900,DATE_SUB(CURDATE(), INTERVAL 1 DAY));

create table loans (
                       loan_number int primary key not null auto_increment,
                       customer_id int not null,
                       start_dt date not null,
                       loan_type varchar(100) not null,
                       total_loan int not null,
                       amount_paid int not null,
                       outstanding_amount int not null,
                       create_dt date default null
);

alter table loans
    add constraint fk_loans_customer foreign key (customer_id) references customer (customer_id);

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
VALUES ( 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
VALUES ( 1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
VALUES ( 1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
VALUES ( 1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');

create table cards (
                       card_id int primary key not null auto_increment,
                       card_number varchar(100) not null,
                       customer_id int not null,
                       card_type varchar(100) not null,
                       total_limit int not null,
                       amount_used int not null,
                       available_amount int not null,
                       create_dt date default null
);

alter table cards
    add constraint fk_cards_customer foreign key (customer_id) references customer (customer_id);

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
VALUES ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
VALUES ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());

create table notice_details (
                                notice_id int primary key not null auto_increment,
                                notice_summary varchar(200) not null,
                                notice_details varchar(500) not null,
                                notic_beg_dt date not null,
                                notic_end_dt date default null,
                                create_dt date default null,
                                update_dt date default null
);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Net Banking Offers', 'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Mobile App Downtime', 'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('E Auction notice', 'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Launch of Millennia Cards', 'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('COVID-19 Insurance', 'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

create table contact_messages (
                                  contact_id varchar(50) primary key not null,
                                  contact_name varchar(50) not null,
                                  contact_email varchar(100) not null,
                                  subject varchar(500) not null,
                                  message varchar(2000) not null,
                                  create_dt date default null
);

alter table customer
    add column create_dt date default null;

create table authorities (
                             id int primary key not null auto_increment,
                             customer_id int not null,
                             name varchar(50)
);

alter table authorities
    add constraint fk_authorities_customer foreign key (customer_id) references customer (customer_id);

insert into authorities (customer_id, name) values (1, 'VIEWACCOUNT');
insert into authorities (customer_id, name) values (1, 'VIEWCARDS');
insert into authorities (customer_id, name) values (1, 'VIEWLOANS');
insert into authorities (customer_id, name) values (1, 'VIEWBALANCE');

