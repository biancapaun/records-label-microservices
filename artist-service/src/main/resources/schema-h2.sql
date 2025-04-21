-- ARTIST
CREATE TABLE artist (
    id_artist INTEGER AUTO_INCREMENT PRIMARY KEY,
    nume_scena VARCHAR(50) NOT NULL,
    nume_real VARCHAR(100),
    data_nasterii DATE,
    tara_origine VARCHAR(50) DEFAULT 'Necunoscut'
);

