===============
SQLite 語法筆記
===============

* 建立 Table ::

    CREATE TABLE "shopping_cart_contact" (
        "user_id" integer NOT NULL PRIMARY KEY REFERENCES "email_auth_user" ("id"),
        "name" varchar(30) NOT NULL,
        "dorm" smallint unsigned NOT NULL,
        "room" varchar(3) NOT NULL,
        "phone" varchar(15) NOT NULL
    );

* 列出值 ::

    SELECT * from {{table-name}};

  - 列出時加上一些欄位 ::

      SELECT id, "text" from {{table-name}};

  - 過濾掉重覆的 ::

      SELECT DISTINCT id from {{table-name}};

* 插入值 ::

    INSERT INTO {{table-name}} ({{field}}, {{fields}}) VALUES ({{value}}, {{values}});

  - 從別的 Table 中取值來插入 ::

    INSERT INTO {{table-name}} SELECT * from {{other-table}};

* 更新值 ::

    UPDATE {{table-name}} SET {{col}}={{value}}, {{col2}}={{value2}}, ... WHERE {{condition}};

* 刪除一些 row ::

    DELETE FROM {{table-name}} WHERE {{condition}};

* 重新命名 Table ::

    ALTER TABLE {{old-name}} RENAME TO {{new-name}};

* 刪除 Table ::

    DROP TABLE {{table-name}};

* 增加 Column ::

    ALTER TABLE {{table-name}} ADD COLUMN {{col}} {{type}};
