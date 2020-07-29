drop table if exists card_type;
drop table if exists merchant;
drop table if exists card;
drop table if exists customer;
drop table if exists transaction;
drop table if exists historical_breakage_rate;

-- card type
create table card_type(
                      gc_type_code varchar(16) not null,
                      gc_name char(128),
                      gc_business_model varchar(32),
                      gc_merchant_code varchar(16),
                      gc_validity_days integer,
                      gc_category varchar(32),
                      gc_reloadable boolean,
                      gc_funds_expire boolean
                      );

-- merchant
create table merchant(
    merchant_code varchar(16) not null,
    merchant_name varchar(64)
);

-- card
create table card(
    gc_uuid varchar(64) not null,
    gc_type_code varchar(16),
    gc_original_value decimal(5,2),
    gc_issue_data date,
    gc_expiry_date date,
    customer_id varchar(64)
);

-- customer table
create table customer(
    customer_id varchar(64) not null,
    customer_name varchar(64),
    customer_city varchar(32),
    customer_zip_code varchar(10),
    customer_dob date,
    customer_registration_date date,
    customer_email varchar(64),
    customer_phone varchar(16)
);

-- transaction log
create table transaction(
    gc_uuid varchar(64) not null,
    tx_id varchar(64),
    gc_type_code varchar(16),
    merchant_code varchar(16),
    tx_type varchar(32),
    tx_value decimal(5,2),
    tx_date date
);

-- transaction log
create table historical_breakage_rate(
                            gc_name varchar(64) not null,
                            gc_type_code varchar(16),
                            ytd_purchase_value decimal(9,2),
                            ytd_breakage decimal(9,2),
                            breakage_rate decimal(5,2),
                            as_of_year integer

);