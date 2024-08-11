DROP TABLE IF EXISTS t_campaigns;

CREATE TABLE t_campaigns(
	id INT GENERATED ALWAYS AS IDENTITY,
  	campaign_name VARCHAR(200),
   	campaign_desc VARCHAR(2000),
   	campaign_img VARCHAR(42),
   	campaign_is_public boolean NOT NULL,
   	CONSTRAINT t_campaigns_ukey UNIQUE (campaign_name),
   	CONSTRAINT t_campaigns_pkey PRIMARY KEY(id)
);