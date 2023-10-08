CREATE TABLE product (
    id TEXT NOT NULL,
    name TEXT NOT NULL,
    price_in_cents INT NOT NULL,
    PRIMARY KEY (id(255)) -- Definindo um comprimento de 255 caracteres para a chave primária
);