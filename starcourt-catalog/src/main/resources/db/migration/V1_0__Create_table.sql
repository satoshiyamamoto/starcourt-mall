CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT,
    name        VARCHAR(200)                        NOT NULL,
    description TEXT,
    category    VARCHAR(100)                        NOT NULL,
    price       DECIMAL(8, 2)                       NOT NULL,
    manufacture VARCHAR(200)                        NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
) ENGINE InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;;

INSERT INTO products (name, description, category, price, manufacture)
VALUES ('シャンブレーボンバージャケット (キッズ)', 'シャンブレーデニム\nダークインディゴ\n長袖、バンドカフス\nバンド付きの襟、ジップフロント\n前にスラントポケット付き\n裾リブ ', 'FASHION',
        5900.00, 'gap'),
       ('スーパーデニムサロペットスカート (キッズ)',
        ' 7" Vintage Shorts with GapFlex\nプレミアム 1969デニム\nボタン開閉のストラップ、レーサーバック\nスクエアネック、ボタンフロント',
        'FASHION', 4900.00, 'gap'),
       ('変身スパンコールグラフィックTシャツ (キッズ)', '柔らかいジャージニット\n半袖ドルマン\n前襟ぐりはクルーネック、後ろ襟ぐりはやや深めのスクープネック\nスパンコールのグラフィック入り (バリエーションあり)',
        'FASHION', 2490.00, 'gap'),
       ('キッズグラフィック半袖Tシャツ', '柔らかいジャージニット\n半袖\n前襟ぐりはクルーネック、後ろ襟ぐりはやや深めのスクープネック\nフロントのグラフィック模様にバリエーションあり', 'FASHION',
        1900.00, 'gap'),
       ( 'ストロベリーショートケーキセレナーデ', '定番の「ショートケーキ」をコールドストーン風にアレンジしました。「セレナーデ」は恋人への愛の歌。いちごのショートケーキが奏でる最高のハーモニーをお楽しみに。'
       , 'ICE_CREAM', 500.00, 'Scoops Ahoy'),
       ( 'ベリーベリー ベリーグッド'
       , 'ベーシックなミルク味のスイートクリームアイスクリームに、3種のベリーをミックスした女性に人気のさっぱりとしたクリエーション。VeryとBerryをかけました。ベーシックなミルク味のスイートクリームアイスクリームに、3種のベリーをミックスした女性に人気のさっぱりとしたクリエーション。VeryとBerryをかけました。'
       , 'ICE_CREAM', 500.00, 'Scoops Ahoy'),
       ( 'オレオ®オーバーロード', '「オレオ、入れすぎ！」という名前の通り、オレオ®クッキーをたっぷりミックスした商品。クッキーのサクサク感は、私たちの作り方ならではの美味しさです。'
       , 'ICE_CREAM', 500.00, 'Scoops Ahoy'),
       ( 'Founder’s Favorite', 'コールドストーンの創業者「サザーランド夫妻」の愛したクリエーションコールドストーンの創業者「サザーランド夫妻」の愛したクリエーション'
       , 'ICE_CREAM', 500.00, 'Scoops Ahoy')
;