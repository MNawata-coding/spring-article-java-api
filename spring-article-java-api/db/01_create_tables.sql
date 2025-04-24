create Table users (
  id int AUTO_INCREMENT not null primary key,
  name varchar(50),
  email varchar(50),
  role ENUM('USER', 'ADMIN'),
  created_by varchar(50),
  updated_by varchar(50),
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp,
  delete_flg boolean 
);

create Table articles (
  id int AUTO_INCREMENT primary key,
  user_id int,
  title varchar(50),
  content varchar(255),
  like_cnt int,
  view_cnt int,
  release_flg boolean,
  reservation_day timestamp default current_timestamp,
  created_by varchar(50),
  updated_by varchar(50),
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp,
  delete_flg TINYINT(1)
);

create Table tags (
  tag_id int AUTO_INCREMENT primary key,
  tagname varchar(50),
  created_by varchar(50),
  updated_by varchar(50),
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp,
  delete_flg TINYINT(1)
);

create Table articles_tags (
  tag_id int not null,
  article_id int not null,
  created_at timestamp default current_timestamp,
  PRIMARY KEY(tag_id, article_id),
  FOREIGN KEY(tag_id)
      REFERENCES tags(tag_id)
      ON DELETE CASCADE,
  FOREIGN KEY(article_id)
      REFERENCES articles(id)
      ON DELETE CASCADE
);