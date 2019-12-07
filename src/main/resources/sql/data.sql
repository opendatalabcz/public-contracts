INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (1, 'download_rule', 'nen.nipez.cz', 'eu.profinit.publiccontracts.service.impl.NenNipezDownloader', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (2, 'download_rule', 'ezak', 'eu.profinit.publiccontracts.service.impl.EZakDownloader', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (3, 'download_rule', 'zakazky', 'eu.profinit.publiccontracts.service.impl.EZakDownloader', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (5, 'download_rule', 'gemin', 'eu.profinit.publiccontracts.service.impl.GeminDownloader', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (6, 'supported_mime_type', 'X-DOWNLOAD', 'application/x-download', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (7, 'supported_mime_type', 'WORD_01', 'application/msword', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (8, 'supported_mime_type', 'WORD_02', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (9, 'supported_mime_type', 'EXCEL_01', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (10, 'supported_mime_type', 'EXCEL_02', 'application/vnd.ms-excel', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (11, 'supported_mime_type', 'PDF', 'application/pdf', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (12, 'max_filesize', 'MAX_SIZE', '26214400', True, 'in bytes (25 MB)');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (13, 'supported_mime_type', 'ZIP_01', 'application/zip', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (14, 'download_rule', 'vhodne-uverejneni', 'eu.profinit.publiccontracts.service.impl.VhodneUverejneniDownloader', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (15, 'application', 'SKIP_DOWNLOADING', '', False, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (16, 'supported_mime_type', 'ODT', 'application/vnd.oasis.opendocument.text', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (17, 'supported_mime_type', 'ZIP_02', 'application/x-zip-compressed', True, '');
INSERT INTO parameter (parameter_id, category, param_key, param_value, active, description) VALUES (18, 'download_rule', 'URL_AWAITING', '', True, '');


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