-- Добавляем цвета мотоциклов
INSERT INTO motorcycles_colors (name) VALUES
('Red'),
('Black'),
('Blue'),
('White'),
('Green');

-- Добавляем типы мотоциклов
INSERT INTO motorcycles_types (id, image, name, description, engine_volume, max_speed, fuel_rate) VALUES
(1, 'https://mediapool.bmwgroup.com/cache/P9/200909/P90052312/P90052312-bmw-hp2-sport-11-2009-2250px.jpg', 'Sport', 'High-speed racing motorcycle', 1000, 280, 6.5),
(2, 'https://avatars.mds.yandex.net/i?id=63cb03dee6803eba5b329692f74da62c88058e5facfb5296-4011134-images-thumbs&n=13', 'Cruiser', 'Comfortable long-distance motorcycle', 1800, 200, 5.8),
(3, 'https://mediapool.bmwgroup.com/cache/P9/201301/P90112971/P90112971-bmw-r-1200-gs-02-2013-2249px.jpg', 'Touring', 'Designed for long-distance travel', 1600, 220, 5.5),
(4, 'https://kickstart.bikeexif.com/wp-content/uploads/2017/10/ariel-ace-r-motorcycle.jpg', 'Naked', 'Minimalist street motorcycle', 800, 220, 4.7),
(5, 'https://www.ucar-33.fr/wp-content/uploads/2017/10/moto-cross-250cc-pas-chere.jpg', 'Dirt Bike', 'Off-road motorcycle', 450, 160, 3.9);

-- Добавляем мотоциклы
INSERT INTO motorcycles (type_id, created_at, vin, color_id) VALUES
(1, NOW(), '1HD1YZK16KB123456', 1),
(2, NOW(), 'JH2RC44677M654321', 2),
(3, NOW(), 'WB10G1200KZ987654', 3),
(4, NOW(), 'ZDM12345678901234', 4),
(5, NOW(), 'SMTTC10K5NT123789', 5);

-- Добавляем пользователя
INSERT INTO users (balance, enter_code_expired_at, enter_code, phone_number) VALUES
(100.00, NOW(), '1234', 1);

-- Добавляем аренды
INSERT INTO rentals (motorcycle_id, user_id, created_at, expired_at) VALUES
(1, 1, '2025-03-01 09:51:22.193605', '2025-03-02 09:51:22.193605'),
(1, 1, NOW(), '2025-03-10 09:51:22.193605');

-- Добавляем сроки аренды
INSERT INTO rentals_durations (days, cost) VALUES
(30, 100);
