-- Добавляем цвета мотоциклов
INSERT INTO motorcycles_colors (name) VALUES
('Red'),
('Black'),
('Blue'),
('White'),
('Green');

-- Добавляем типы мотоциклов
INSERT INTO motorcycles_types (id, image, name, description, engine_volume, max_speed, fuel_rate) VALUES
(1, 'sport.jpg', 'Sport', 'High-speed racing motorcycle', 1000, 280, 6.5),
(2, 'cruiser.jpg', 'Cruiser', 'Comfortable long-distance motorcycle', 1800, 200, 5.8),
(3, 'touring.jpg', 'Touring', 'Designed for long-distance travel', 1600, 220, 5.5),
(4, 'naked.jpg', 'Naked', 'Minimalist street motorcycle', 800, 220, 4.7),
(5, 'dirtbike.jpg', 'Dirt Bike', 'Off-road motorcycle', 450, 160, 3.9);

-- Добавляем мотоциклы
INSERT INTO motorcycles (type_id, created_at, vin, color_id) VALUES
(1, NOW(), '1HD1YZK16KB123456', 1),
(2, NOW(), 'JH2RC44677M654321', 2),
(3, NOW(), 'WB10G1200KZ987654', 3),
(4, NOW(), 'ZDM12345678901234', 4),
(5, NOW(), 'SMTTC10K5NT123789', 5);
