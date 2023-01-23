
INSERT INTO drone (serial_number,model, weight_limit, battery_capacity, state, created_at, updated_at) VALUES ('cd-20001', 'LIGHT_WEIGHT', 100, 90, 'IDLE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO drone (serial_number,model, weight_limit, battery_capacity, state, created_at, updated_at) VALUES ('cd-20002', 'MIDDLE_WEIGHT', 200, 80, 'LOADING', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO drone (serial_number,model, weight_limit, battery_capacity, state, created_at, updated_at) VALUES ('cd-20003', 'CRUISER_WEIGHT', 300.50, 12, 'LOADED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO drone (serial_number,model, weight_limit, battery_capacity, state, created_at, updated_at) VALUES ('cd-20004', 'HEAVY_WEIGHT', 500, 16, 'DELIVERING', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO drone (serial_number,model, weight_limit, battery_capacity, state, created_at, updated_at) VALUES ('cd-20005', 'LIGHT_WEIGHT', 90.70, 92, 'DELIVERED', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO drone (serial_number,model, weight_limit, battery_capacity, state, created_at, updated_at) VALUES ('cd-20006', 'LIGHT_WEIGHT', 95.70, 92, 'RETURNING', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO medication(id, name, code, weight, drone_serial_number, created_at, updated_at) VALUES (10001, 'med-10', 'M-CODE_10', 10.67, 'cd-20001', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO medication(id, name, code, weight, drone_serial_number, created_at, updated_at) VALUES (10002, 'med-11', 'M-CODE_11', 45.19, 'cd-20001', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO medication(id, name, code, weight, drone_serial_number, created_at, updated_at) VALUES (10003, 'med-12', 'M-CODE_12', 11.67, 'cd-20002', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO medication(id, name, code, weight, drone_serial_number, created_at, updated_at) VALUES (10004, 'med-13', 'M-CODE_13', 30.57, 'cd-20003', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO medication(id, name, code, weight, drone_serial_number, created_at, updated_at) VALUES (10005, 'med-14', 'M-CODE_14', 19.69, 'cd-20003', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO medication(id, name, code, weight, drone_serial_number, created_at, updated_at) VALUES (10006, 'med-15', 'M-CODE_15', 56.19, 'cd-20004', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO medication(id, name, code, weight, drone_serial_number, created_at, updated_at) VALUES (10007, 'med-16', 'M-CODE_16', 68.89, 'cd-20004', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO medication(id, name, code, weight, drone_serial_number, created_at, updated_at) VALUES (10008, 'med-17', 'M-CODE_17', 56.79, 'cd-20004', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

