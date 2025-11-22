CREATE TABLE Usuarios(id INTEGER PRIMARY KEY AUTO_INCREMENT,
				nombre VARCHAR(20) NOT NULL,
				a_paterno VARCHAR(20) NOT NULL,
				a_materno VARCHAR(20),
				usuario VARCHAR(50) NOT NULL,
				password VARCHAR(50) NOT NULL,
				tipo_usuario ENUM('admin','empleado') NOT NULL);

CREATE TABLE Categorias(id INTEGER PRIMARY KEY AUTO_INCREMENT,
				nombre VARCHAR(25) NOT NULL);

CREATE TABLE Productos(id INTEGER PRIMARY KEY AUTO_INCREMENT,
				codigo VARCHAR(128) NOT NULL,
				nombre VARCHAR(50) NOT NULL,
				descripcion TEXT ,
				id_categoria INTEGER NOT NULL,
				unidad_medida VARCHAR(5),
				stock INTEGER NOT NULL,
				precio FLOAT NOT NULL,
				stock_minimo INTEGER NOT NULL,
				link_imagen VARCHAR(255),
				FOREIGN KEY(id_categoria) REFERENCES Categorias(id));

CREATE TABLE Proveedores(id INTEGER PRIMARY KEY AUTO_INCREMENT,
				nombre VARCHAR(50) NOT NULL,
				telefono VARCHAR(10),
				direccion TEXT,
				email VARCHAR(100));



-- Codigo para SQLITE


CREATE TABLE Usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(20) NOT NULL,
    a_paterno VARCHAR(20) NOT NULL,
    a_materno VARCHAR(20),
    usuario VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    tipo_usuario VARCHAR(10) NOT NULL CHECK (tipo_usuario IN ('admin', 'empleado'))
);

CREATE TABLE Categorias (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(25) NOT NULL
);

CREATE TABLE Productos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo VARCHAR(128) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT,
    id_categoria INTEGER NOT NULL,
    unidad_medida VARCHAR(5),
    stock INTEGER NOT NULL,
    precio REAL NOT NULL,
    stock_minimo INTEGER NOT NULL,
    link_imagen VARCHAR(255),
    FOREIGN KEY(id_categoria) REFERENCES Categorias(id)
);

CREATE TABLE Proveedores (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(50) NOT NULL,
    telefono VARCHAR(10),
    direccion TEXT,
    email VARCHAR(100)
);

CREATE TABLE Productos_Proveedores(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_producto INTEGER NOT NULL,
    id_proveedor INTEGER NOT NULL,
    FOREIGN KEY(id_producto) REFERENCES Productos(id),
    FOREIGN KEY(id_proveedor) REFERENCES Proveedores(id)
);

CREATE TABLE Clientes(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(40) NOT NULL,
    telefono VARCHAR(10),
    direccion TEXT
);

CREATE TABLE Tipos_Movimientos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(35) NOT NULL,
    signo_stock INTEGER,
    descripcion TEXT
);

CREATE TABLE Movimientos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo TEXT UNIQUE NOT NULL,
    id_cliente INTEGER,
    id_proveedor INTEGER,
    id_tipo_movimiento INTEGER NOT NULL,
    motivo VARCHAR(40) NOT NULL,
    total REAL,
    fecha DATE NOT NULL,
    FOREIGN KEY(id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY(id_proveedor) REFERENCES Proveedores(id),
    FOREIGN KEY(id_tipo_movimiento) REFERENCES Tipos_Movimientos(id)
);

CREATE TABLE Detalles_Movimientos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_producto INTEGER,
    id_movimiento INTEGER,
    cantidad INTEGER,
    stock_anterior INTEGER,
    stock_actual INTEGER,
    observaciones TEXT,
    precio_unitario REAL,
    FOREIGN KEY(id_producto) REFERENCES Productos(id),
    FOREIGN KEY(id_movimiento) REFERENCES Movimientos(id)
);

-- ========================================
-- DATOS DE PRUEBA
-- ========================================

-- Insertar Usuarios
INSERT INTO Usuarios (nombre, a_paterno, a_materno, usuario, password, tipo_usuario) VALUES
('Juan', 'Pérez', 'García', 'admin', 'admin123', 'admin'),
('María', 'López', 'Martínez', 'mlopez', 'emp123', 'empleado'),
('Carlos', 'Ramírez', 'Sánchez', 'cramirez', 'emp456', 'empleado'),
('Ana', 'González', 'Torres', 'agonzalez', 'emp789', 'empleado');

-- Insertar Categorías
INSERT INTO Categorias (nombre) VALUES
('Herramientas'),
('Pinturas'),
('Electricidad'),
('Plomería'),
('Construcción'),
('Ferretería General'),
('Jardinería'),
('Seguridad');

-- Insertar Productos
INSERT INTO Productos (codigo, nombre, descripcion, id_categoria, unidad_medida, stock, precio, stock_minimo, link_imagen) VALUES
('MART-001', 'Martillo de Garra 16oz', 'Martillo profesional con mango de fibra de vidrio', 1, 'PZA', 25, 189.50, 5, NULL),
('DEST-002', 'Destornillador Plano 6"', 'Destornillador plano profesional', 1, 'PZA', 40, 45.00, 10, NULL),
('DEST-003', 'Destornillador Phillips 6"', 'Destornillador phillips profesional', 1, 'PZA', 35, 45.00, 10, NULL),
('PINT-004', 'Pintura Vinílica Blanca 4L', 'Pintura lavable para interiores', 2, 'LT', 50, 285.00, 15, NULL),
('PINT-005', 'Brocha 3 pulgadas', 'Brocha para pintura de cerda natural', 2, 'PZA', 30, 55.00, 8, NULL),
('CABL-006', 'Cable Calibre 12 AWG', 'Cable eléctrico THW 90°C', 3, 'MTS', 200, 18.50, 50, NULL),
('APAG-007', 'Apagador Sencillo', 'Apagador tipo escalera blanco', 3, 'PZA', 60, 25.00, 15, NULL),
('CONT-008', 'Contacto Doble', 'Contacto polarizado blanco', 3, 'PZA', 55, 30.00, 15, NULL),
('TUBE-009', 'Tubo PVC 1/2" 6mts', 'Tubo hidráulico cédula 40', 4, 'PZA', 45, 95.00, 10, NULL),
('CODO-010', 'Codo PVC 1/2"', 'Codo 90° para instalación hidráulica', 4, 'PZA', 100, 8.50, 25, NULL),
('CEM-011', 'Cemento Gris 50kg', 'Cemento Portland Compuesto', 5, 'BTO', 80, 185.00, 20, NULL),
('AREN-012', 'Arena de Río', 'Arena cernida para construcción', 5, 'M3', 15, 450.00, 5, NULL),
('TORN-013', 'Tornillos 1" caja x100', 'Tornillos para madera punta broca', 6, 'CJA', 70, 65.00, 15, NULL),
('CLAV-014', 'Clavos 2.5" kg', 'Clavos para construcción', 6, 'KG', 90, 28.00, 20, NULL),
('MANG-015', 'Manguera de Jardín 1/2" 20m', 'Manguera reforzada para riego', 7, 'PZA', 20, 320.00, 5, NULL),
('PALA-016', 'Pala Cuadrada', 'Pala para jardinería mango de madera', 7, 'PZA', 15, 245.00, 5, NULL),
('CAND-017', 'Candado 40mm', 'Candado de seguridad con 2 llaves', 8, 'PZA', 35, 95.00, 10, NULL),
('GUANT-018', 'Guantes de Carnaza', 'Guantes de seguridad reforzados', 8, 'PAR', 50, 45.00, 15, NULL);

-- Insertar Proveedores
INSERT INTO Proveedores (nombre, telefono, direccion, email) VALUES
('Ferretería del Centro SA de CV', '7771234567', 'Av. Principal 123, Cuernavaca, Morelos', 'ventas@ferreteriacentro.com'),
('Distribuidora de Pinturas del Sur', '7779876543', 'Calle Reforma 456, Cuautla, Morelos', 'contacto@pinturassur.com'),
('Eléctricos y Plomería SA', '7775551234', 'Blvd. Zapata 789, Cuernavaca, Morelos', 'pedidos@electricosyplomeria.com'),
('Materiales de Construcción López', '7774443322', 'Carretera Federal 321, Jiutepec, Morelos', 'ventas@materialeslopez.com'),
('Herramientas Profesionales MX', '7772221133', 'Av. Universidad 555, Cuernavaca, Morelos', 'info@herramientaspro.com');

-- Insertar Productos_Proveedores
INSERT INTO Productos_Proveedores (id_producto, id_proveedor) VALUES
(1, 5), (2, 5), (3, 5),  -- Herramientas -> Herramientas Profesionales
(4, 2), (5, 2),          -- Pinturas -> Distribuidora de Pinturas
(6, 3), (7, 3), (8, 3),  -- Eléctricos -> Eléctricos y Plomería
(9, 3), (10, 3),         -- Plomería -> Eléctricos y Plomería
(11, 4), (12, 4),        -- Construcción -> Materiales López
(13, 1), (14, 1),        -- Ferretería General -> Ferretería del Centro
(15, 1), (16, 1),        -- Jardinería -> Ferretería del Centro
(17, 1), (18, 1);        -- Seguridad -> Ferretería del Centro

-- Insertar Clientes
INSERT INTO Clientes (nombre, telefono, direccion) VALUES
('Constructor Ramírez Hnos', '7771112233', 'Col. Centro, Cuernavaca, Morelos'),
('Instalaciones García SA', '7773334455', 'Col. Reforma, Cuautla, Morelos'),
('Pinturas y Acabados del Sur', '7775556677', 'Col. Vista Hermosa, Cuernavaca, Morelos'),
('Juan Pérez (Cliente Público)', '7777778888', 'Col. Lomas de la Selva, Cuernavaca'),
('María González (Cliente Público)', '7779990011', 'Col. Flores, Jiutepec'),
('Plomería Moderna SA', '7772223344', 'Col. Cantarranas, Cuernavaca, Morelos'),
('Eléctricos del Valle', '7774445566', 'Col. Primavera, Temixco, Morelos');

-- Insertar Tipos_Movimientos
INSERT INTO Tipos_Movimientos (nombre, signo_stock, descripcion) VALUES
('Compra a Proveedor', 1, 'Entrada de mercancía por compra'),
('Venta a Cliente', -1, 'Salida de mercancía por venta'),
('Devolución de Cliente', 1, 'Entrada por devolución de mercancía'),
('Devolución a Proveedor', -1, 'Salida por devolución a proveedor'),
('Ajuste de Inventario Positivo', 1, 'Corrección de stock por conteo físico'),
('Ajuste de Inventario Negativo', -1, 'Corrección de stock por merma o robo'),
('Traspaso Entrada', 1, 'Entrada por traspaso entre almacenes'),
('Traspaso Salida', -1, 'Salida por traspaso entre almacenes');

-- Insertar Movimientos
INSERT INTO Movimientos (codigo, id_cliente, id_proveedor, id_tipo_movimiento, motivo, total, fecha) VALUES
('C00001', NULL, 5, 1, 'Compra inicial de herramientas', 3250.50, '2024-01-15'),
('C00002', NULL, 2, 1, 'Compra de pinturas', 4560.00, '2024-01-20'),
('V00001', 1, NULL, 2, 'Venta materiales construcción', 2850.00, '2024-02-05'),
('V00002', 4, NULL, 2, 'Venta mostrador', 567.50, '2024-02-10'),
('C00003', NULL, 3, 1, 'Compra material eléctrico', 5200.00, '2024-02-15'),
('V00003', 2, NULL, 2, 'Venta instalación eléctrica', 1850.00, '2024-03-01'),
('D00001', 4, NULL, 3, 'Devolución pintura defectuosa', 285.00, '2024-03-05'),
('V00004', 6, NULL, 2, 'Venta materiales plomería', 980.50, '2024-03-10'),
('A00001', NULL, NULL, 5, 'Ajuste por inventario físico', 0.00, '2024-03-15'),
('V00005', 5, NULL, 2, 'Venta mostrador jardinería', 565.00, '2024-03-20');

-- Insertar Detalles_Movimientos
-- Compra de herramientas (COMP-2024-001)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(1, 1, 15, 0, 15, 'Compra inicial', 180.00),
(2, 1, 20, 0, 20, 'Compra inicial', 42.00),
(3, 1, 20, 0, 20, 'Compra inicial', 42.00);

-- Compra de pinturas (COMP-2024-002)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(4, 2, 30, 0, 30, 'Compra inicial pinturas', 270.00),
(5, 2, 20, 0, 20, 'Compra inicial brochas', 50.00);

-- Venta construcción (VENTA-2024-001)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(11, 3, 10, 90, 80, 'Venta constructor', 185.00),
(12, 3, 5, 20, 15, 'Venta constructor', 450.00);

-- Venta mostrador (VENTA-2024-002)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(1, 4, 3, 15, 12, 'Venta mostrador', 189.50);

-- Compra material eléctrico (COMP-2024-003)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(6, 5, 200, 0, 200, 'Compra cable', 17.50),
(7, 5, 60, 0, 60, 'Compra apagadores', 23.00),
(8, 5, 60, 0, 60, 'Compra contactos', 28.00);

-- Venta instalación eléctrica (VENTA-2024-006)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(6, 6, 50, 200, 150, 'Venta instalación', 18.50),
(7, 6, 20, 60, 40, 'Venta instalación', 25.00),
(8, 6, 15, 60, 45, 'Venta instalación', 30.00);

-- Devolución pintura (DEV-2024-001)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(4, 7, 1, 29, 30, 'Pintura con defecto de fabricación', 285.00);

-- Venta plomería (VENTA-2024-004)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(9, 8, 5, 50, 45, 'Venta tubos PVC', 95.00),
(10, 8, 20, 120, 100, 'Venta codos', 8.50);

-- Ajuste inventario (AJUSTE-2024-001)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(2, 9, 20, 20, 40, 'Ajuste por reconteo físico - se encontraron 20 unidades más', 45.00);

-- Venta jardinería (VENTA-2024-005)
INSERT INTO Detalles_Movimientos (id_producto, id_movimiento, cantidad, stock_anterior, stock_actual, observaciones, precio_unitario) VALUES
(15, 10, 1, 21, 20, 'Venta manguera', 320.00),
(16, 10, 1, 16, 15, 'Venta pala', 245.00);

