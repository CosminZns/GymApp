CREATE TABLE `aliments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `weight` int NOT NULL DEFAULT 0,
  `calories` int NOT NULL DEFAULT 0,
  `carbs` int NOT NULL DEFAULT 0,
  `fats` int NOT NULL DEFAULT 0,
  `proteins` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `aliments_diets` (
  `aliment_id` int NOT NULL,
  `diet_id` int NOT NULL,
  PRIMARY KEY (`aliment_id`,`diet_id`),
  CONSTRAINT `fk_aliments_has_Diets_aliments1` FOREIGN KEY (`aliment_id`) REFERENCES `aliments` (`id`),
  CONSTRAINT `fk_aliments_has_Diets_Diets1` FOREIGN KEY (`diet_id`) REFERENCES `diets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `diets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `calories` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `exercise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `group` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `weight` int NOT NULL,
  `goal_weight` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `users_diets` (
  `user_id` int NOT NULL,
  `diet_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`diet_id`),
  CONSTRAINT `fk_users_has_Diets_Diets1` FOREIGN KEY (`diet_id`) REFERENCES `diets` (`id`),
  CONSTRAINT `fk_users_has_Diets_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `users_workouts` (
  `user_id` int NOT NULL,
  `workout_id` int NOT NULL,
  CONSTRAINT `fk_users_has_workouts_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_users_has_workouts_workouts1` FOREIGN KEY (`workout_id`) REFERENCES `workouts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `workouts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `calories` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `workouts_excercises` (
  `workout_id` int NOT NULL,
  `exercise_id` int NOT NULL,
  `number_of_sets` int NOT NULL,
  `number_of_reps` int NOT NULL,
  PRIMARY KEY (`workouts_id`,`exercise_id`),
  CONSTRAINT `fk_workouts_has_excercise_excercise1` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`),
  CONSTRAINT `fk_workouts_has_excercise_workouts1` FOREIGN KEY (`workouts_id`) REFERENCES `workouts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
