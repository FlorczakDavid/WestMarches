DROP TABLE IF EXISTS t_events, t_points_of_interest, t_tiles, t_maps, t_users, t_roles;

CREATE TABLE t_users(
   email VARCHAR(255),
   password CHAR(60) NOT NULL, -- bcrypt
   username VARCHAR(50) NOT NULL,
   PRIMARY KEY(email)
);

CREATE TABLE t_roles(
   code CHAR(8),
   is_default boolean;
   PRIMARY KEY(code)
);

CREATE TABLE t_exercises (
	user_email VARCHAR(255),
	role_code CHAR(8),
	PRIMARY KEY (user_email, role_id),
	CONSTRAINT t_exercise_user_email_fkey FOREIGN KEY (user_email) REFERENCES t_users(email),
	CONSTRAINT t_exercise_role_code_fkey FOREIGN KEY (role_code) REFERENCES t_roles(code)
);

CREATE TABLE t_maps(
   email VARCHAR(255),
   name VARCHAR(100),
   width SMALLINT CHECK (width > 0),
   height SMALLINT CHECK (width > 0),
   PRIMARY KEY(email, name),
   FOREIGN KEY(email) REFERENCES t_users(email) ON DELETE CASCADE
);

CREATE TABLE t_tiles(
   email VARCHAR(255),
   map_name VARCHAR(100),
   coordinates GEOMETRY, -- PostGIS
   terrain VARCHAR(50),
   description TEXT,
   PRIMARY KEY(email, map_name, coordinates),
   FOREIGN KEY(email, map_name) REFERENCES t_maps(email, name) ON DELETE CASCADE
);

CREATE TABLE t_points_of_interest(
   email VARCHAR(255),
   map_name VARCHAR(100),
   coordinates GEOMETRY,
   name VARCHAR(100),
   description TEXT,
   PRIMARY KEY(email, map_name, coordinates, name),
   FOREIGN KEY(email, map_name, coordinates) REFERENCES t_tiles(email, map_name, coordinates) ON DELETE CASCADE
);

CREATE TABLE t_events(
   email VARCHAR(255),
   map_name VARCHAR(100),
   coordinates GEOMETRY,
   poi_name VARCHAR(100),
   name VARCHAR(100),
   description TEXT,
   PRIMARY KEY(email, map_name, coordinates, poi_name, name),
   FOREIGN KEY(email, map_name, coordinates, poi_name) REFERENCES t_points_of_interest(email, map_name, coordinates, name) ON DELETE CASCADE
);