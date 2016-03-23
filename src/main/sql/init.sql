CREATE TABLE submitter
(
  submitter_id BIGINT NOT NULL,
  ico          TEXT,
  name         TEXT
);

CREATE SEQUENCE submitter_submitter_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER SEQUENCE submitter_submitter_id_seq OWNED BY submitter.submitter_id;

ALTER TABLE ONLY submitter ALTER COLUMN submitter_id SET DEFAULT nextval(
    'submitter_submitter_id_seq' :: REGCLASS);


ALTER TABLE submitter ADD PRIMARY KEY (submitter_id);


CREATE TABLE contract
(
  contract_id BIGINT NOT NULL,
  submitter_id BIGINT,
  code1 text,
  code2 text,
  name        TEXT,
  state       TEXT,
  kind        TEXT
);

CREATE SEQUENCE contract_contract_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER SEQUENCE contract_contract_id_seq OWNED BY contract.contract_id;

ALTER TABLE ONLY contract ALTER COLUMN contract_id SET DEFAULT nextval(
    'contract_contract_id_seq' :: REGCLASS);


ALTER TABLE contract ADD PRIMARY KEY (contract_id);

ALTER TABLE ONLY contract
ADD CONSTRAINT fk_contract_submitter FOREIGN KEY (submitter_id) REFERENCES submitter (submitter_id);



CREATE TABLE candidate
(
  candidate_id BIGINT NOT NULL,
  contract_id BIGINT,
  ico        TEXT,
  name       TEXT,
  price DOUBLE PRECISION
);

CREATE SEQUENCE candidate_candidate_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER SEQUENCE candidate_candidate_id_seq OWNED BY candidate.candidate_id;

ALTER TABLE ONLY candidate ALTER COLUMN candidate_id SET DEFAULT nextval(
    'candidate_candidate_id_seq' :: REGCLASS);


ALTER TABLE candidate ADD PRIMARY KEY (candidate_id);

ALTER TABLE ONLY candidate
ADD CONSTRAINT fk_candidate_contract FOREIGN KEY (contract_id) REFERENCES contract (contract_id);


CREATE TABLE supplier
(
  supplier_id BIGINT NOT NULL,
  contract_id BIGINT,
  ico        TEXT,
  name       TEXT,
  price DOUBLE PRECISION

);

CREATE SEQUENCE supplier_supplier_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER SEQUENCE supplier_supplier_id_seq OWNED BY supplier.supplier_id;

ALTER TABLE ONLY supplier ALTER COLUMN supplier_id SET DEFAULT nextval(
    'supplier_supplier_id_seq' :: REGCLASS);


ALTER TABLE supplier ADD PRIMARY KEY (supplier_id);

ALTER TABLE ONLY supplier
ADD CONSTRAINT fk_supplier_contract FOREIGN KEY (contract_id) REFERENCES contract (contract_id);




CREATE TABLE subsupplier
(
  subsupplier_id BIGINT NOT NULL,
  supplier_id BIGINT,
  ico        TEXT,
  name       TEXT

);

CREATE SEQUENCE subsupplier_subsupplier_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER SEQUENCE subsupplier_subsupplier_id_seq OWNED BY subsupplier.subsupplier_id;

ALTER TABLE ONLY subsupplier ALTER COLUMN subsupplier_id SET DEFAULT nextval(
    'subsupplier_subsupplier_id_seq' :: REGCLASS);


ALTER TABLE subsupplier ADD PRIMARY KEY (subsupplier_id);

ALTER TABLE ONLY subsupplier
ADD CONSTRAINT fk_subsupplier_supplier FOREIGN KEY (supplier_id) REFERENCES supplier (supplier_id);




