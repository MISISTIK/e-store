-- auto-generated definition
CREATE TABLE users
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  login    VARCHAR(40) NOT NULL,
  password VARCHAR(80) NOT NULL,
  name     VARCHAR(30) NULL,
  age      INT         NULL,
  gender   VARCHAR(10) NULL,
  address  VARCHAR(50) NULL,
  CONSTRAINT login
  UNIQUE (login)
) ENGINE = InnoDB;

-- auto-generated definition
CREATE TABLE products
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  name        VARCHAR(50) NOT NULL,
  price       INT         NOT NULL,
  description TEXT        NULL,
  category    INT         NULL
) ENGINE = InnoDB;

-- ADMIN CREATION
INSERT INTO dbitea.users (id, login, password, name, age, gender, address) VALUES (2, 'admin@itea.ua', '-6205113e2b4e77abb4a93625b91e43c', 'admin', 28, 'Male', 'Ukraine');

-- PRODUCTS FILL
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (1, 'Intel Core i3', 4050, 'Семейство процессора: Intel Core i3 Тип разъема: Socket 1151  Поколение процессора Intel: Coffee Lake (восьмое)  Количество ядер: 4 Внутренняя тактовая частота: 3600 МГц Объем кэш памяти 3 уровня: 6 МБ ', 1);
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (2, 'Intel Core i5', 6480, 'Семейство процессора: Intel Core i5 Тип разъема: Socket 1151 Поколение процессора Intel: Coffee Lake (восьмое) Количество ядер: 6 Интегрированная графика: Intel UHD Graphics 630 Внутренняя тактовая частота: 2800 МГц', 1);
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (3, 'Intel Core i7', 12650, 'Семейство процессора: Intel Core i7 Тип разъема: Socket 1151 Поколение процессора Intel: Coffee Lake (восьмое)  Количество ядер: 6  Внутренняя тактовая частота: 3200 МГц Объем кэш памяти 3 уровня: 12 МБ', 1);
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (4, 'AMD Ryzen', 43260, 'Семейство процессора: AMD Ryzen Threadripper Тип разъема: Socket sTR4 Количество ядер: 24 Интегрированная графика: Нет Внутренняя тактовая частота: 3000 МГц Объем кэш памяти 3 уровня: 64 МБ Страна регистрации бренда: США', 2);
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (5, 'AMD Ryzen', 7650, 'Семейство процессора: AMD Ryzen Threadripper Тип разъема: Socket sTR4 Количество ядер: 8 Интегрированная графика: Нет Внутренняя тактовая частота: 3800 МГц Объем кэш памяти 3 уровня: 16 МБ Страна регистрации бренда: США', 2);
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (6, 'AMD Ryzen', 5120, 'Семейство процессора: AMD Ryzen 5 Тип разъема: Socket AM4 Количество ядер: 6 Интегрированная графика: Нет Внутренняя тактовая частота: 3400 МГц Объем кэш памяти 3 уровня: 16 МБ Страна регистрации бренда: США', 2);
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (7, 'MSI GeForce ', 47850, 'Графический чип: GeForce RTX 2080 Ti Объем памяти: 11 ГБ Разрядность шины памяти: 352 бит Тип памяти: GDDR6 Тип системы охлаждения: Активная Страна регистрации бренда: Тайвань', 3);
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (8, 'MSI GeForce ', 34195, 'Графический чип: GeForce RTX 2080 Ti Объем памяти: 11 ГБ Разрядность шины памяти: 352 бит Тип памяти: GDDR6 Тип системы охлаждения: Активная Страна регистрации бренда: Тайвань', 3);
INSERT INTO dbitea.products (id, name, price, description, category) VALUES (9, 'Asus GeForce', 37150, 'Графический чип: GeForce RTX 2080 Ti Объем памяти: 11 ГБ Разрядность шины памяти: 352 бит Тип памяти: GDDR6 Тип системы охлаждения: Активная Страна регистрации бренда: Тайвань', 3);


