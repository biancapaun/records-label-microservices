CREATE TABLE production (
    id_album NUMBER NOT NULL,
    id_producer NUMBER NOT NULL,
    production_type VARCHAR2(50) NOT NULL,
    budget NUMBER(12, 2) NOT NULL,
    CONSTRAINT production_pk PRIMARY KEY (id_album, id_producer),
    CONSTRAINT production_budget_positive CHECK (budget > 0)
);
