CREATE TABLE premiu (
    id_premiu INTEGER AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(100),
    categorie VARCHAR(100),
    an INTEGER,
    id_artist INTEGER,
    CONSTRAINT fk_premiu_artist FOREIGN KEY (id_artist) REFERENCES artist(id_artist)
);