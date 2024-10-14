-- 코드를 작성해주세요
-- 10/11
SELECT
    E.ITEM_ID,
    E.ITEM_NAME,
    E.RARITY
FROM ITEM_INFO E JOIN (
    SELECT
        T.ITEM_ID
    FROM ITEM_TREE T JOIN (
        SELECT
            *
        FROM ITEM_INFO
        WHERE RARITY = 'RARE'
    ) I
                          ON T.PARENT_ITEM_ID = I.ITEM_ID
) R
                      ON E.ITEM_ID = R.ITEM_ID
ORDER BY R.ITEM_ID DESC
