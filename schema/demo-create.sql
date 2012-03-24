CREATE DATABASE IF NOT EXISTS `demo`;
use `demo`;

CREATE TABLE `todo` (
    `id` integer auto_increment,
    `content` varchar(1024) not null,
    `done` boolean not null,
    `order` integer not null,
    primary key (`id`)
)
ENGINE=InnoDB, DEFAULT CHARACTER SET=utf8;
