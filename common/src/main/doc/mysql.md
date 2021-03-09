 
 ## mysql多维度统计
 SELECT  user_id,`status`,count('status') as count FROM blue_business.company_boss  group by user_id,`status`;