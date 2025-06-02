CREATE TABLE Departamentos (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL
);

CREATE TABLE Empleados (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    Apellido TEXT NOT NULL,
    Salario REAL NOT NULL,
    DepartamentoID INTEGER,
    FOREIGN KEY (DepartamentoID) REFERENCES Departamentos(ID)
);

-- Datos de ejemplo
INSERT INTO Departamentos (Nombre) VALUES ('Ventas'), ('TI');
INSERT INTO Empleados (Nombre, Apellido, Salario, DepartamentoID) 
VALUES ('Ana', 'García', 50000, 1), ('Luis', 'Martínez', 60000, 2);

