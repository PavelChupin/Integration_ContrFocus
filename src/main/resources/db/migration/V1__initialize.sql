
drop table if exists person_bankruptcy cascade;

create table person_bankruptcy
(
    id              bigint(15) NOT NULL AUTO_INCREMENT,
    inn             varchar(255),
    fio             varchar(255),
    birth_date       datetime,
    snils           varchar(255),
    stage           varchar(255),
    stage_date       datetime,
    case_number      varchar(255),
    last_message_date datetime,
    primary key (id)
);
