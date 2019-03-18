-- Create syntax for TABLE 'roles_permissions'
CREATE TABLE `roles_permissions`
(
  `id`         int(11)      NOT NULL AUTO_INCREMENT,
  `role_name`  varchar(255) DEFAULT NULL,
  `permission` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- Create syntax for TABLE 'user_roles'
CREATE TABLE `user_roles`
(
  `id`        int(11) NOT NULL AUTO_INCREMENT,
  `username`  varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- Create syntax for TABLE 'users'
CREATE TABLE `users`
(
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `roles_permissions`
VALUES (1, 'admin', 'user:select'),
       (2, 'admin', 'user:delete'),
       (3, 'user', 'user:select');
INSERT INTO `user_roles`
VALUES (1, 'Yourtion', 'admin'),
       (2, 'Yourtion', 'user');
INSERT INTO `users`
VALUES (1, 'Yourtion', '123456');


-- TestUser
CREATE TABLE `test_user`
(
  `id`        int(11)      NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password`  varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
INSERT INTO `test_user` (`id`, `user_name`, `password`)
VALUES (1, 'xiaoming', '654321');
