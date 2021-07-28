DELETE FROM demo.patient;

INSERT INTO demo.patient(patient_id, family, given, date_of_birth, sex, address, phone) VALUES (01, 'TestNone', 'Test', '1966-12-31', 'F', '1 Brookside St', '100-222-3333');
INSERT INTO demo.patient(patient_id, family, given, date_of_birth, sex, address, phone) VALUES (02, 'TestBorderline', 'Test', '1945-06-24', 'M', '2 High St', '200-333-4444');
INSERT INTO demo.patient(patient_id, family, given, date_of_birth, sex, address, phone) VALUES (03, 'TestInDanger', 'Test', '2004-06-18', 'M', '3 Club Road', '300-444-5555');
INSERT INTO demo.patient(patient_id, family, given, date_of_birth, sex, address, phone) VALUES (04, 'TestEarlyOnset', 'Test', '2002-06-28', 'F', '4 Valley Dr', '400-555-6666');

