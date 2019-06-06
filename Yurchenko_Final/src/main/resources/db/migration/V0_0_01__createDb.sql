create table users
(
    user_id                   int auto_increment unique
        primary key,
    role                      int         null,
    login                     varchar(30) not null,
    name                      varchar(50) null,
    lastname                  varchar(50) null,
    hash                      char(40)    not null,
    salt                      binary(16)  not null,
    user_number_of_points     int         null,
    user_max_number_of_points int         null,
    magic_key                 char(40)    null,
    submitted                 int         null,
    constraint user_fk
        foreign key (user_id) references roles (role_id)
            on update cascade
            on delete cascade
)ENGINE MyISAM;;

create table roles
(
    role_id                   int unique auto_increment
        primary key,
    name                      varchar(30) not null
)ENGINE MyISAM;;

create table questions
(
    question_id               int auto_increment unique
        primary key,
    question_type             int         not null,
    question_theme_id         int         not null,
    right_answers             int         null,
    answers                   int         null,
    question                  varchar(1200)null,
    incorrect_option1         varchar(70)null,
    incorrect_option2         varchar(70)null,
    incorrect_option3         varchar(70)null,
    correct_option1           varchar(70)null,
    correct_option2           varchar(70)null,
    correct_option3           varchar(70)null,
    constraint question_fk
        foreign key (question_id) references question_type (question_type_id)
            on update cascade
            on delete cascade
)ENGINE MyISAM;;

create table questions_rus
(
    question_id               int auto_increment unique
        primary key,
    question_type             int         not null,
    question_theme_id         int         not null,
    right_answers             int         null,
    answers                   int         null,
     question                 varchar(1200)null,
    incorrect_option1         varchar(70)null,
    incorrect_option2         varchar(70)null,
    incorrect_option3         varchar(70)null,
    correct_option1           varchar(70)null,
    correct_option2           varchar(70)null,
    correct_option3           varchar(70)null,
    constraint question_fk
        foreign key (question_id) references question_type (question_type_id)
            on update cascade
            on delete cascade
)ENGINE MyISAM;;

create table question_type
(
    question_type_id          int auto_increment unique
        primary key,
    type                      varchar(15) not null
)ENGINE MyISAM;;

create table tests
(
    test_id                   int auto_increment unique
        primary key,
    test_user_id              int         not null,
    test_theme_id             int         not null,
    test_number_of_points     int         null,
    test_max_number_of_points int         null,
    date                      date        null,
    tests_status              int         not null,
    constraint test_user_fk
        foreign key (test_user_id) references users (user_id)
            on update cascade
            on delete cascade,
    constraint test_theme_fk
        foreign key (test_theme_id) references themes (theme_id)
            on update cascade
            on delete cascade,
    constraint test_status_fk
        foreign key (tests_status) references test_status (test_status_id)
            on update cascade
            on delete cascade
)ENGINE MyISAM;;

create table test_status
(
    test_status_id           int auto_increment unique
        primary key,
    status                   varchar(15) not null
)ENGINE MyISAM;;

create table themes
(
    theme_id                  int auto_increment unique
        primary key,
    theme_name                varchar(100)not null
)ENGINE MyISAM;;








