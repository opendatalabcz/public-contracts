INSERT INTO download_rule (rule_id, condition, downloader) VALUES (1, 'nen.nipez.cz', 'eu.profinit.publiccontracts.util.download.NenNipezDownloader');
INSERT INTO download_rule (rule_id, condition, downloader) VALUES (2, 'ezak', 'eu.profinit.publiccontracts.util.download.EZakDownloader');

UPDATE source SET active = False;

UPDATE source SET active = True
WHERE ico IN (
'66003008', --MD
'00006947', --MF
'45769851', --MZV
'60162694', --MO
'00551023', --MPSV
'00007064', --MV
'00164801', --MZP
'66002222', --MMR
'47609109', --MPO
'00020478', --MZem
'00022985', --MSMT
'00023671', --MK
'00024341', --MZ
'00025429' --MS
)