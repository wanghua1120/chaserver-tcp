select a.id,packageType,b.name 系统编码,c.name 命令编码,crc,mn,flag,remoteIp,rawText from e_traffic_package a
 left join e_coding_system b on a.codingSystemId=b.id
 left join e_coding_command c on a.codingCommandId=c.id
where qn='20190513134200260'
order by a.id desc
limit 100
