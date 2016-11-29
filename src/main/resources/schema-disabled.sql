CREATE TABLE `csas_exchange_references` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `last_updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

CREATE TABLE `csas_exchange_rates` (
  `amout` bigint(20) NOT NULL,
  `cnb_mid` double NOT NULL,
  `country` varchar(255) NOT NULL,
  `curr_buy` double NOT NULL,
  `curr_mid` double NOT NULL,
  `curr_sell` double NOT NULL,
  `exchange_reference_id` bigint(20) NOT NULL,
  `move` double NOT NULL,
  `name` varchar(255) NOT NULL,
  `short_name` varchar(3) NOT NULL,
  `val_buy` double NOT NULL,
  `val_mid` double NOT NULL,
  `val_sel` double NOT NULL,
  `valid_from` datetime NOT NULL,
  `version` varchar(255) NOT NULL,
  PRIMARY KEY (`amout`,`cnb_mid`,`country`,`curr_buy`,`curr_mid`,`curr_sell`,`exchange_reference_id`,`move`,`name`,`short_name`,`val_buy`,`val_mid`,`val_sel`,`valid_from`,`version`),
  KEY `FKeilds3ku39btvglvx0wl2pmfo` (`exchange_reference_id`),
  CONSTRAINT `FKeilds3ku39btvglvx0wl2pmfo` FOREIGN KEY (`exchange_reference_id`) REFERENCES `csas_exchange_references` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

CREATE TABLE `fixer_exchange_references` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base` varchar(3) NOT NULL,
  `date` date NOT NULL,
  `last_updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

CREATE TABLE `fixer_exchange_rates` (
  `code` varchar(3) NOT NULL,
  `exchange_reference_id` bigint(20) NOT NULL,
  `rate` double NOT NULL,
  PRIMARY KEY (`code`,`exchange_reference_id`,`rate`),
  KEY `FKrlmk2auk4yh8jnwlei580mxy5` (`exchange_reference_id`),
  CONSTRAINT `FKrlmk2auk4yh8jnwlei580mxy5` FOREIGN KEY (`exchange_reference_id`) REFERENCES `fixer_exchange_references` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;
