ALTER TABLE entity ALTER COLUMN is_public DROP NOT NULL;

CREATE TABLE submitter
(
  submitter_id BIGINT NOT NULL,
  entity_id    INTEGER
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

ALTER TABLE ONLY submitter
ADD CONSTRAINT fk_submitter_entity FOREIGN KEY (entity_id) REFERENCES entity (entity_id);

CREATE TABLE contract
(
  contract_id  BIGINT NOT NULL,
  submitter_id BIGINT,
  code1        TEXT,
  code2        TEXT,
  name         TEXT,
  state        TEXT,
  kind         TEXT,
  year         INT
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
  contract_id  BIGINT,
  entity_id    INTEGER,
  price        DOUBLE PRECISION
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
ALTER TABLE ONLY candidate
ADD CONSTRAINT fk_candidate_entity FOREIGN KEY (entity_id) REFERENCES entity (entity_id);

CREATE TABLE supplier
(
  supplier_id BIGINT NOT NULL,
  contract_id BIGINT,
  entity_id   INTEGER,
  price       DOUBLE PRECISION

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
ALTER TABLE ONLY supplier
ADD CONSTRAINT fk_supplier_entity FOREIGN KEY (entity_id) REFERENCES entity (entity_id);

CREATE TABLE subsupplier
(
  subsupplier_id BIGINT NOT NULL,
  supplier_id    BIGINT,
  entity_id      INTEGER

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

ALTER TABLE ONLY subsupplier
ADD CONSTRAINT fk_subsupplier_entity FOREIGN KEY (entity_id) REFERENCES entity (entity_id);


CREATE TABLE source
(
  source_id BIGINT NOT NULL,
  url       TEXT,
  ico       TEXT,
  name      TEXT,
  active    BOOLEAN
);

CREATE SEQUENCE source_source_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER SEQUENCE source_source_id_seq OWNED BY source.source_id;

ALTER TABLE ONLY source ALTER COLUMN source_id SET DEFAULT nextval(
    'source_source_id_seq' :: REGCLASS);

ALTER TABLE source ADD PRIMARY KEY (source_id);

ALTER TABLE retrieval
ADD year INT;

CREATE TABLE error
(
  error_id    BIGINT NOT NULL,
  ico         TEXT,
  name        TEXT,
  message     TEXT,
  url         TEXT,
  year        INT,
  error_class TEXT
);

CREATE SEQUENCE error_error_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER SEQUENCE error_error_id_seq OWNED BY error.error_id;

ALTER TABLE ONLY error ALTER COLUMN error_id SET DEFAULT nextval(
    'error_error_id_seq' :: REGCLASS);


ALTER TABLE error ADD PRIMARY KEY (error_id);


CREATE TABLE document
(
  document_id BIGINT NOT NULL,
  contract_id  BIGINT,
  url          TEXT,
  type         TEXT,
  version      INTEGER,
  date_upload  TIMESTAMP,
  data         TEXT
);

CREATE SEQUENCE document_document_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER SEQUENCE document_document_id_seq OWNED BY document.document_id;

ALTER TABLE ONLY document ALTER COLUMN document_id SET DEFAULT nextval(
    'document_document_id_seq' :: REGCLASS);

ALTER TABLE document ADD PRIMARY KEY (document_id);

ALTER TABLE ONLY document
ADD CONSTRAINT fk_document_contract FOREIGN KEY (contract_id) REFERENCES contract (contract_id);


CREATE TABLE download_rule (
  rule_id INTEGER NOT NULL,
  condition TEXT,
  downloader TEXT,
  PRIMARY KEY(rule_id)
)