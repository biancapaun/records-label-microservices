CREATE TABLE contract (
    id_contract INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_artist INTEGER,
    id_label INTEGER,
    data_start DATE,
    data_incheiere DATE,
    CONSTRAINT fk_contract_artist FOREIGN KEY (id_artist) REFERENCES artist(id_artist),
    CONSTRAINT fk_contract_label FOREIGN KEY (id_label) REFERENCES label(id_label)
);