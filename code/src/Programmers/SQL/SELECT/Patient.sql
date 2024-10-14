-- 코드를 입력하세요
SELECT
    PT_NAME,
    PT_NO,
    GEND_CD,
    AGE,
    NVL(TLNO, 'NONE') AS TLNO
FROM PATIENT
WHERE AGE <= 12 and GEND_CD = 'W'
ORDER BY AGE DESC, PT_NAME

-- 10/9
SELECT
    PT_NAME,
    PT_NO,
    GEND_CD,
    AGE,
    NVL(TLNO, 'NONE')
FROM PATIENT
WHERE GEND_CD = 'W' AND AGE <= 12
ORDER BY AGE DESC, PT_NAME ASC;










