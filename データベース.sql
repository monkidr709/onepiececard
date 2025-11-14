create table users (
    id INTEGER primary key,
    username VARCHAR(50) not null,
    password VARCHAR(50) not null,
    role VARCHAR(50) not null
);

create sequence seq_user CACHE 1 no maxvalue;

insert into users values (nextval('seq_user'), '佐々木陸', '587412369rk', 'admin');
insert into users values (nextval('seq_user'), 'しいたけ', '9699690rk', 'user');

create table cards (
    id INTEGER primary key, --主キー
    card_number VARCHAR(50) not null, --カードナンバー
    card_name VARCHAR(50) not null, --カードの名前
    image_file_path VARCHAR(500) not null, --画像のファイルパス
    
    card_color VARCHAR(50) not null, --カードの色
    card_type VARCHAR(50) not null, --カードの種類
    card_pack VARCHAR(50) not null, --カードが収録されているパックやスターターデッキなど
    card_block_icon INTEGER not null, --カードのブロックアイコン
    card_rarity VARCHAR(50) not null, --カードのレアリティ
    card_cost_or_life INTEGER not null, --カードのコストまたはライフ
    card_power INTEGER, --カードのパワー
    card_features VARCHAR(50), --カードの属性
    
    card_attribute VARCHAR(50) not null, --カードの特徴
    card_counter VARCHAR(50), --カードのカウンター値
    card_text VARCHAR(500), --カードのテキスト効果
    card_trigger boolean, --カードのトリガーの有無
    card_trigger_text VARCHAR(500), --カードのトリガーのテキスト
    card_appearance boolean, --カードの登場時効果の有無
    card_launch_main boolean, --カードの起動メイン効果の有無
    card_attack boolean, --カードのアタック時効果の有無
    
    card_KO boolean, --カードのKO時効果の有無
    card_block boolean, --カードのブロック時効果の有無
    card_during_your_turn boolean, --カードの自分のターン中効果の有無
    card_during_opponent_turn boolean, --カードの相手のターン中効果の有無
    card_your_turn_end boolean, --カードの自分のターン終了時効果の有無
    card_opponent_attack boolean, --カードの相手のアタック時効果の有無
    card_main boolean, --カードのメイン効果の有無
    card_event_counter boolean, --カードのイベントカウンター効果の有無
    
    card_one_turn boolean, --カードのターン1回効果の有無
    card_don_hang boolean, --カードのドン!!×n効果の有無
    card_don_use boolean, --カードのドン使用効果の有無
    card_don_minus boolean, --カードのドン!!-n効果の有無
    card_blocker boolean, --カードのブロッカー効果の有無
    card_haste boolean, --カードの速攻効果の有無
    card_double_attack boolean, --カードのダブルアタック効果の有無
    card_vanish boolean --カードのバニッシュ効果の有無
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

