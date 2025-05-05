DROP TABLE IF EXISTS t_events, t_points_of_interest, t_tiles, t_maps, t_exercise, t_roles, t_users CASCADE;

CREATE TABLE t_users(
	id INT GENERATED ALWAYS AS IDENTITY,
    email VARCHAR(255) NOT NULL,
    password CHAR(60) NOT NULL, -- bcrypt
    username VARCHAR(50) NOT NULL,
    CONSTRAINT t_users_pkey PRIMARY KEY(id),
    CONSTRAINT t_users_ukey UNIQUE (email)
);

CREATE TABLE t_roles(
	id INT GENERATED ALWAYS AS IDENTITY,
    code char(8) NOT NULL,
    is_default boolean,
    CONSTRAINT t_roles_pkey PRIMARY KEY(id),
    CONSTRAINT t_roles_ukey UNIQUE (code)
);

CREATE TABLE t_exercise (
	user_id INT,
	role_id INT,
	PRIMARY KEY (user_id, role_id),
	CONSTRAINT t_exercise_user_id_fkey FOREIGN KEY (user_id) REFERENCES t_users(id),
	CONSTRAINT t_exercise_role_id_fkey FOREIGN KEY (role_id) REFERENCES t_roles(id)
);

CREATE TABLE t_maps(
	id INT GENERATED ALWAYS AS IDENTITY,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    width SMALLINT CHECK (width > 0),
    height SMALLINT CHECK (height > 0),
    CONSTRAINT t_maps_pkey PRIMARY KEY (id),
    CONSTRAINT t_maps_ukey UNIQUE (user_id, name),
    CONSTRAINT t_users_maps_fkey FOREIGN KEY(user_id) REFERENCES t_users(id) ON DELETE CASCADE
);

CREATE TABLE t_tiles(
	id INT GENERATED ALWAYS AS IDENTITY,
    map_id INT NOT NULL,
	x INT NOT NULL,
	y INT NOT NULL,
    terrain VARCHAR(50),
    description TEXT,
    CONSTRAINT t_maps_tiles_pkey PRIMARY KEY(id),
    CONSTRAINT t_maps_tiles_ukey UNIQUE(map_id, x, y),
    CONSTRAINT t_maps_tiles_fkey FOREIGN KEY(map_id) REFERENCES t_maps(id) ON DELETE CASCADE
);

CREATE TABLE t_points_of_interest(
	id INT GENERATED ALWAYS AS IDENTITY,
	tile_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    CONSTRAINT t_tiles_points_of_interest_pkey PRIMARY KEY(id),
    CONSTRAINT t_tiles_points_of_interest_ukey UNIQUE(tile_id, name),
    CONSTRAINT t_tiles_points_of_interest_fkey FOREIGN KEY(tile_id) REFERENCES t_tiles(id) ON DELETE CASCADE
);

CREATE TABLE t_events(
	id INT GENERATED ALWAYS AS IDENTITY,
	poi_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    CONSTRAINT t_points_of_interest_events_pkey PRIMARY KEY(id),
    CONSTRAINT t_points_of_interest_events_ukey UNIQUE(poi_id, name),
    CONSTRAINT t_points_of_interest_events_fkey FOREIGN KEY(poi_id) REFERENCES t_points_of_interest(id) ON DELETE CASCADE
);