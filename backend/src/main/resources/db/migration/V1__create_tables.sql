create Table users (
  id int AUTO_INCREMENT not null primary key,
  name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  role ENUM('USER', 'ADMIN') NOT NULL,
  created_by varchar(50) NOT NULL,
  updated_by varchar(50),
  created_at timestamp(5) NOT NULL default current_timestamp(5),
  updated_at timestamp(5) default current_timestamp(5) ON UPDATE current_timestamp(5),
  delete_flg boolean NOT NULL
);

create Table articles (
  id int AUTO_INCREMENT primary key,
  user_id int NOT NULL,
  title varchar(50) NOT NULL,
  content varchar(255),
  like_cnt int NOT NULL default 0,
  view_cnt int NOT NULL default 0,
  release_flg boolean NOT NULL,
  reservation_day timestamp,
  created_by varchar(50) NOT NULL,
  updated_by varchar(50),
  created_at timestamp(5) NOT NULL default current_timestamp(5),
  updated_at timestamp(5) default current_timestamp(5) ON UPDATE current_timestamp(5),
  delete_flg TINYINT(1) NOT NULL
);

create Table tags (
  tag_id int AUTO_INCREMENT primary key,
  tagname varchar(50) NOT NULL,
  created_by varchar(50) NOT NULL,
  updated_by varchar(50),
  created_at timestamp(5) NOT NULL default current_timestamp(5),
  updated_at timestamp(5) default current_timestamp(5) ON UPDATE current_timestamp(5),
  delete_flg TINYINT(1) NOT NULL
);

create Table articles_tags (
  tag_id int NOT NULL,
  article_id int NOT NULL,
  created_at timestamp(5) NOT NULL default current_timestamp(5),
  PRIMARY KEY(tag_id, article_id),
  FOREIGN KEY(tag_id)
      REFERENCES tags(tag_id)
      ON DELETE CASCADE,
  FOREIGN KEY(article_id)
      REFERENCES articles(id)
      ON DELETE CASCADE
);
