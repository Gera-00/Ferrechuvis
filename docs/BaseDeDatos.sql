CREATE TABLE Usuarios(id INTEGER PRIMARY KEY AUTO_INCREMENT,
				nombre VARCHAR(20) NOT NULL,
				a_paterno VARCHAR(20) NOT NULL,
				a_materno VARCHAR(20),
				usuario VARCHAR(50) NOT NULL,
				password VARCHAR(50) NOT NULL,
				tipo_usuario ENUM('admin','empleado') NOT NULL,);

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
    codigo VARCHAR(128) NOT NULL,
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