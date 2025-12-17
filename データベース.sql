create table public.users (
  id integer not null
  , username character varying(255) not null
  , password character varying(255) not null
  , role character varying(255) not null
  , email_address character varying(255)
  , telephone_number character varying(255)
  , deleted boolean default false
  , deleted_at date
  , primary key (id)
);

create sequence seq_user CACHE 1 no maxvalue;

insert into users values (nextval('seq_user'), '佐々木陸', '587412369rk', 'admin');
insert into users values (nextval('seq_user'), 'しいたけ', '9699690rk', 'user');

create table public.cards (
  id integer not null
  , card_number character varying(255) not null
  , card_name character varying(255) not null
  , card_name_furigana character varying(255)
  , image_file_path character varying(255) not null
  , card_color character varying(255) not null
  , card_color_2 character varying(255)
  , card_type character varying(255) not null
  , card_pack character varying(255) not null
  , card_block_icon integer not null
  , card_rarity character varying(255) not null
  , card_cost_or_life integer not null
  , card_power integer
  , card_features character varying(255)
  , card_attribute_1 character varying(255) not null
  , card_attribute_2 character varying(255)
  , card_attribute_3 character varying(255)
  , card_attribute_4 character varying(255)
  , card_attribute_5 character varying(255)
  , card_counter character varying(255)
  , card_text character varying(255)
  , card_trigger boolean
  , card_trigger_text character varying(255)
  , card_appearance boolean
  , card_launch_main boolean
  , card_attack boolean
  , card_ko boolean
  , card_block boolean
  , card_during_your_turn boolean
  , card_during_opponent_turn boolean
  , card_your_turn_end boolean
  , card_opponent_attack boolean
  , card_main boolean
  , card_event_counter boolean
  , card_one_turn boolean
  , card_don_hang boolean
  , card_don_use boolean
  , card_don_minus boolean
  , card_blocker boolean
  , card_haste boolean
  , card_double_attack boolean
  , card_vanish boolean
  , primary key (id)
);

create table public.decks (
  id integer not null
  , user_name_id integer not null
  , deck_name character varying(50) not null
  , publish_deck boolean
  , leader_card_id integer not null
  , deck_card_id integer[]
  , created_date date default CURRENT_DATE not null
  , deleted boolean
  , deleted_date date
  , primary key (id)
);

create table public.news (
  id bigint not null
  , title character varying(200) not null
  , category character varying(50) not null
  , image_path character varying(255)
  , content text not null
  , created_at date default CURRENT_DATE not null
  , updated_at date
  , published_date date not null
  , is_published boolean default true
  , primary key (id)
);

