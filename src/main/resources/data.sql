DROP TABLE IF EXISTS collaborator;
DROP TABLE IF EXISTS certification;
DROP TABLE IF EXISTS collaborator_certification;

CREATE TABLE collaborator (
  mail_adresse VARCHAR(250) PRIMARY KEY
);

INSERT INTO collaborator (mail_adresse) VALUES
  ('adangote@sqli.com'),
  ('aelouardi@sqli.com'),
  ('ielouardi@sqli.com'),
  ('yelouardi@sqli.com');


CREATE TABLE certification (
  certification_id INT AUTO_INCREMENT  PRIMARY KEY,
  certification_title VARCHAR(250) NOT NULL,
  certification_description VARCHAR(250) NOT NULL
);

INSERT INTO certification (certification_title, certification_description) VALUES
  ('AEM','AEM Certification'),
  ('Java Developer','Java Developer Certification'),
  ('.Net Developer','.Net Developer Certification'),
  ('Sql server','Sql server Certification'),
  ('Scrum Master','Scrum Master Certification');

CREATE TABLE collaborator_certification (
 mail_adresse VARCHAR(250) NOT NULL,
 certification_id INT NOT NULL
);

INSERT INTO collaborator_certification (mail_adresse, certification_id) VALUES
  ('adangote@sqli.com',1),
  ('adangote@sqli.com',2),
  ('yelouardi@sqli.com',3),
  ('yelouardi@sqli.com',4);
