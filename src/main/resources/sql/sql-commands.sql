DROP TABLE IF EXISTS [dbo].[Students];
DROP TABLE IF EXISTS [dbo].[Groups];


SELECT *
FROM Students as S
         LEFT JOIN Groups as G
                   ON S.group_id = G.id;
