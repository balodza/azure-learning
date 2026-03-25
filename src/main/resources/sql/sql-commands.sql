DROP TABLE IF EXISTS [dbo].[Make];
DROP TABLE IF EXISTS [dbo].[Car];


SELECT *
FROM Car as C
         LEFT JOIN Make as M
                   ON C.make_id = M.id;
