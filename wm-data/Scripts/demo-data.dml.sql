-- DATA FOR DEMO MAP
DO $$
DECLARE
    map_id INT;
BEGIN
    SELECT m.id INTO map_id FROM t_maps m
    JOIN t_users u ON m.user_id = u.id
    WHERE u.email = 'qwe@qwe.com' AND m.name = 'Demo Map - Greenshore';

    IF map_id IS NOT NULL THEN
        DELETE FROM t_maps WHERE id = map_id;
    END IF;
END $$;


INSERT INTO t_maps (user_id, name, width, height)
SELECT id, 'Demo Map - Greenshore', 12, 12 FROM t_users WHERE email = 'qwe@qwe.com';


DO $$
DECLARE
    map_id INT;
    x INT;
    y INT;
    terrain TEXT;
    description TEXT;
BEGIN
    SELECT id INTO map_id FROM t_maps WHERE name = 'Demo Map - Greenshore';

    FOR x IN 0..11 LOOP
        FOR y IN 0..11 LOOP
            IF x <= 3 AND y <= 3 THEN
                terrain := 'forest';
                description := 'Dense woodland filled with the chirping of unseen birds and tangled roots.';
            ELSIF x >= 8 AND y >= 8 THEN
                terrain := 'sea';
                description := 'Choppy blue waters stretching to the horizon. The scent of salt lingers in the air.';
            ELSIF x >= 6 AND y >= 6 THEN
                terrain := 'beach';
                description := 'Golden sands sloping toward the waves. Driftwood and seaweed mark the high tide.';
            ELSE
                terrain := 'grassland';
                description := 'Rolling plains with wildflowers and scattered rocks under a vast blue sky.';
            END IF;

            INSERT INTO t_tiles (map_id, x, y, terrain, description)
            VALUES (map_id, x, y, terrain, description);
        END LOOP;
    END LOOP;
END $$;

-- --- Insert POIs and Events ---
DO $$
DECLARE
    tile_rec RECORD;
    poi_id INT;
    poi_name TEXT;
    poi_desc TEXT;
    event_name TEXT;
    event_desc TEXT;
    poi_index INT;
    evt_index INT;
BEGIN
    FOR tile_rec IN SELECT id, x, y, terrain FROM t_tiles WHERE map_id = (SELECT id FROM t_maps WHERE name = 'Demo Map - Greenshore') LOOP
        FOR poi_index IN 1..3 LOOP
            -- Generate POI name/description based on terrain
            CASE tile_rec.terrain
                WHEN 'forest' THEN
                    poi_name := CASE poi_index
                        WHEN 1 THEN 'Abandoned Hunting Lodge'
                        WHEN 2 THEN 'Witch''s Hollow'
                        ELSE 'Ancient Oak Circle'
                    END;
                    poi_desc := 'Hidden within the thick trees, ' || lower(poi_name) || ' harbors old secrets.';

                WHEN 'grassland' THEN
                    poi_name := CASE poi_index
                        WHEN 1 THEN 'Weathered Cairn'
                        WHEN 2 THEN 'Hilltop Shrine'
                        ELSE 'Wandering Merchant Camp'
                    END;
                    poi_desc := 'Among the waving grass, ' || lower(poi_name) || ' marks a place of quiet reverence.';

                WHEN 'beach' THEN
                    poi_name := CASE poi_index
                        WHEN 1 THEN 'Shipwreck of the Starwind'
                        WHEN 2 THEN 'Saltspire Beacon'
                        ELSE 'Cave of Whispers'
                    END;
                    poi_desc := 'On the wind-swept beach, ' || lower(poi_name) || ' tells stories of sea and storm.';

                WHEN 'sea' THEN
                    poi_name := CASE poi_index
                        WHEN 1 THEN 'Coral Graveyard'
                        WHEN 2 THEN 'Sunken Chapel'
                        ELSE 'Merrow Lookout'
                    END;
                    poi_desc := 'Beneath the waves, ' || lower(poi_name) || ' is lost to most—but not forgotten.';

                ELSE
                    poi_name := 'Unknown Site';
                    poi_desc := 'An unmarked location of possible interest.';
            END CASE;

            INSERT INTO t_points_of_interest (tile_id, name, description)
            VALUES (tile_rec.id, poi_name, poi_desc)
            RETURNING id INTO poi_id;

            FOR evt_index IN 1..2 LOOP
                event_name := poi_name || ' - Event #' || evt_index;
                event_desc := CASE evt_index
                    WHEN 1 THEN 'An eerie silence falls over the area as strange lights flicker nearby.'
                    ELSE 'Locals whisper of disappearances and unnatural sounds in the vicinity.'
                END;

                INSERT INTO t_events (poi_id, name, description)
                VALUES (poi_id, event_name, event_desc);
            END LOOP;
        END LOOP;
    END LOOP;
END $$;