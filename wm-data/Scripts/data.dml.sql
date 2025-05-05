-- --- CLEAR TABLES IN PROPER ORDER ---
TRUNCATE t_events, t_points_of_interest, t_tiles, t_maps, t_exercise, t_roles, t_users RESTART IDENTITY CASCADE;

-- --- INSERT ROLES ---
INSERT INTO t_roles (code, is_default) VALUES
  ('ROLE_adm', FALSE),
  ('ROLE_pc', TRUE),
  ('ROLE_dm', FALSE);

-- --- INSERT USERS ---
INSERT INTO t_users (email, password, username) VALUES
  ('qwe@qwe.com', '$2a$14$h4RFsH7Phnd2rQZ2IhUiN.peX9gotoHzKam1xTbzIaIrGYcj/8x4m', 'QweUser'),
  ('arabic@fakemail.com', '$2a$14$h4RFsH7Phnd2rQZ2IhUiN.peX9gotoHzKam1xTbzIaIrGYcj/8x4m', 'مستخدم'),
  ('dungeon@master.com', '$2a$14$h4RFsH7Phnd2rQZ2IhUiN.peX9gotoHzKam1xTbzIaIrGYcj/8x4m', 'DungeonMaster'),
  ('admin@moot.com', '$2a$14$h4RFsH7Phnd2rQZ2IhUiN.peX9gotoHzKam1xTbzIaIrGYcj/8x4m', 'AdminUser'),
  ('player5@game.com', '$2a$14$h4RFsH7Phnd2rQZ2IhUiN.peX9gotoHzKam1xTbzIaIrGYcj/8x4m', 'PlayerFive');

-- --- ASSIGN ROLES ---
INSERT INTO t_exercise (user_id, role_id)
SELECT u.id, r.id FROM t_users u
JOIN t_roles r ON (
    (u.email = 'admin@moot.com' AND r.code = 'ROLE_adm') OR
    (u.email = 'dungeon@master.com' AND r.code = 'ROLE_dm') OR
    (u.email IN ('qwe@qwe.com', 'arabic@fakemail.com', 'player5@game.com') AND r.code = 'ROLE_pc')
);

-- --- CREATE MAPS FOR DM and PCs (admin has none) ---
-- DM gets 1 map
INSERT INTO t_maps (user_id, name, width, height)
SELECT u.id, 'Dungeon World', 15, 10 FROM t_users u WHERE u.email = 'dungeon@master.com';

-- PCs get 2+ maps
INSERT INTO t_maps (user_id, name, width, height)
SELECT u.id, 'Adventure Land', 10, 12 FROM t_users u WHERE u.email = 'qwe@qwe.com';
INSERT INTO t_maps (user_id, name, width, height)
SELECT u.id, 'Draft Realm', 7, 14 FROM t_users u WHERE u.email = 'qwe@qwe.com';

INSERT INTO t_maps (user_id, name, width, height)
SELECT u.id, 'Mystic Desert', 8, 8 FROM t_users u WHERE u.email = 'arabic@fakemail.com';
INSERT INTO t_maps (user_id, name, width, height)
SELECT u.id, 'Lost Jungle', 12, 9 FROM t_users u WHERE u.email = 'arabic@fakemail.com';

INSERT INTO t_maps (user_id, name, width, height)
SELECT u.id, 'Frozen Tundra', 9, 11 FROM t_users u WHERE u.email = 'player5@game.com';
INSERT INTO t_maps (user_id, name, width, height)
SELECT u.id, 'Burning Cliffs', 11, 13 FROM t_users u WHERE u.email = 'player5@game.com';

-- --- INSERT TILES ---
-- Populate tiles for each map with 10+ entries
DO $$
DECLARE
    map_rec RECORD;
    tile_counter INT;
    terrains TEXT[] := ARRAY['grassland','sea','ocean','forest','mountain','desert','urban','beach','swamp','snow','roughland','volcanic'];
BEGIN
    FOR map_rec IN SELECT id, width, height FROM t_maps LOOP
        FOR tile_counter IN 1..LEAST(map_rec.width * map_rec.height, 20) LOOP
            INSERT INTO t_tiles (map_id, x, y, terrain, description)
            VALUES (
                map_rec.id,
                (tile_counter - 1) % map_rec.width,
                (tile_counter - 1) / map_rec.width,
                terrains[1 + floor(random() * array_length(terrains, 1))],
                'Test Tile ' || tile_counter
            );
        END LOOP;
    END LOOP;
END
$$;

-- --- INSERT POINTS OF INTEREST AND EVENTS ---
DO $$
DECLARE
    tile_rec RECORD;
    poi_id INT;
    evt_counter INT;
    user_is_qwe BOOLEAN;
    poi_counter INT;
BEGIN
    FOR tile_rec IN SELECT t.id, u.email FROM t_tiles t JOIN t_maps m ON m.id = t.map_id JOIN t_users u ON u.id = m.user_id LOOP
        user_is_qwe := tile_rec.email = 'qwe@qwe.com';

        FOR poi_counter IN 1..(CASE WHEN user_is_qwe THEN 3 + floor(random()*3)::int ELSE 1 + floor(random()*2)::int END) LOOP
            INSERT INTO t_points_of_interest (tile_id, name, description)
            VALUES (
                tile_rec.id,
                'POI ' || poi_counter || ' Tile ' || tile_rec.id,
                'Description for POI ' || poi_counter || ' at tile ' || tile_rec.id
            )
            RETURNING id INTO poi_id;

            FOR evt_counter IN 1..(CASE WHEN user_is_qwe THEN 2 + floor(random()*3)::int ELSE 1 + floor(random()*2)::int END) LOOP
                INSERT INTO t_events (poi_id, name, description)
                VALUES (
                    poi_id,
                    'Event ' || evt_counter || ' POI ' || poi_id,
                    'Description for Event ' || evt_counter || ' at POI ' || poi_id
                );
            END LOOP;
        END LOOP;
    END LOOP;
END
$$;
