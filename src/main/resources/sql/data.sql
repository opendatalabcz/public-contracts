INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (1, 'download_rule', 'nen.nipez.cz', 'eu.profinit.publiccontracts.util.download.NenNipezDownloader', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (2, 'download_rule', 'ezak', 'eu.profinit.publiccontracts.util.download.EZakDownloader', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (3, 'supported_mime_type', 'X-DOWNLOAD', 'application/x-download', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (4, 'supported_mime_type', 'WORD_01', 'application/msword', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (5, 'supported_mime_type', 'WORD_02', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (6, 'supported_mime_type', 'PDF', 'application/pdf', True, '');

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