CREATE TABLE gen_muzical (
    id_gen BIGINT AUTO_INCREMENT PRIMARY KEY,
    nume_gen VARCHAR(50) NOT NULL
);

CREATE TABLE album (
    id_album BIGINT AUTO_INCREMENT PRIMARY KEY,
    titlu VARCHAR(100) NOT NULL,
    data_lansare DATE,
    id_artist BIGINT,
    id_gen BIGINT,
    CONSTRAINT fk_album_gen FOREIGN KEY (id_gen) REFERENCES gen_muzical(id_gen)
);
