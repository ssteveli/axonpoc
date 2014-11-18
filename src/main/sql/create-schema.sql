	
CREATE TABLE fitpay.domainevententry (
  aggregateIdentifier VARCHAR(255) NOT NULL,
  sequenceNumber      INT8         NOT NULL,
  type                VARCHAR(255) NOT NULL,
  eventIdentifier     VARCHAR(255) NOT NULL,
  metaData            OID,
  payload             OID          NOT NULL,
  payloadRevision     VARCHAR(255),
  payloadType         VARCHAR(255) NOT NULL,
  timeStamp           VARCHAR(255) NOT NULL,
  CONSTRAINT PK_DomainEventEntry PRIMARY KEY (aggregateIdentifier, sequenceNumber, type)
);

ALTER TABLE fitpay.domainevententry ADD CONSTRAINT UC_DomainEventEntry_eventId UNIQUE (eventIdentifier);

CREATE TABLE fitpay.sagaentry (
  sagaId         VARCHAR(255) NOT NULL,
  revision       VARCHAR(255),
  sagaType       VARCHAR(255),
  serializedSaga OID,
  CONSTRAINT PK_SagaEntry PRIMARY KEY (sagaId)
);

CREATE TABLE fitpay.snapshotevententry (
  aggregateIdentifier VARCHAR(255) NOT NULL,
  sequenceNumber      INT8         NOT NULL,
  type                VARCHAR(255) NOT NULL,
  eventIdentifier     VARCHAR(255) NOT NULL,
  metaData            OID,
  payload             OID          NOT NULL,
  payloadRevision     VARCHAR(255),
  payloadType         VARCHAR(255) NOT NULL,
  timeStamp           VARCHAR(255) NOT NULL,
  CONSTRAINT PK_SnapshotEventEntry PRIMARY KEY (aggregateIdentifier, sequenceNumber, type)
);
ALTER TABLE fitpay.snapshotevententry ADD CONSTRAINT UC_SnapshotEventEntry_eventId UNIQUE (eventIdentifier);

