-- PRODUCATOR
CREATE TABLE producator (
    id_producator NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    nume          VARCHAR2(100) CONSTRAINT producator_nume_nn NOT NULL,
    specializare  VARCHAR2(50),
    tara_origine  VARCHAR2(50) DEFAULT 'Necunoscut' CONSTRAINT producator_tara_origine_nn NOT NULL
);




