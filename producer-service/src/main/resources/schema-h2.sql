-- PRODUCATOR
CREATE TABLE producator (
    id_producator INTEGER AUTO_INCREMENT PRIMARY KEY,
    nume VARCHAR(100) NOT NULL,
    specializare VARCHAR(50),
    tara_origine VARCHAR(50) DEFAULT 'Necunoscut'
);

