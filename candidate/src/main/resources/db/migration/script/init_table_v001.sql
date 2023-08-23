--DROP TABLE IF EXISTS t_saved_jobs;
--DROP TABLE IF EXISTS t_custom_candidate_skill;
--DROP TABLE IF EXISTS t_candidate_skill;
--DROP TABLE IF EXISTS t_candidate_experience;
--DROP TABLE IF EXISTS t_candidate_document;
--DROP TABLE IF EXISTS t_candidate_education;
--DROP TABLE IF EXISTS t_candidate;
--DROP TABLE IF EXISTS t_candidate_profile;
--DROP TABLE IF EXISTS t_job_vacancy;
--DROP TABLE IF EXISTS t_vacancy_description;
--DROP TABLE IF EXISTS t_file;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE t_file (
	id VARCHAR(36) NOT NULL,
	file_content TEXT NOT NULL,
	file_ext VARCHAR(5) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE t_vacancy_description (
	id VARCHAR(36) NOT NULL,
	degree_id VARCHAR,
	gender_id VARCHAR NOT NULL,
	age_vacancy_id VARCHAR NOT NULL,
	job_type_id VARCHAR NOT NULL,
	salary VARCHAR NOT NULL,
	city_id VARCHAR NOT NULL,
	address TEXT NOT NULL,
	description TEXT NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE t_job_vacancy (
	id VARCHAR(36) NOT NULL,
	vacancy_code VARCHAR(8) NOT NULL,
	vacancy_title VARCHAR(36) NOT NULL,
	start_date TIMESTAMP NOT NULL,
	end_date TIMESTAMP NOT NULL,
	exp_level_id VARCHAR NOT NULL,
	available_status_id VARCHAR NOT NULL,
	company_id VARCHAR NOT NULL,
	vacancy_description_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (vacancy_description_id)
		REFERENCES t_vacancy_description (id)
);


CREATE TABLE t_candidate_profile (
	id varchar(36) NOT NULL,
	profile_name varchar(50) NOT NULL,
	profile_address varchar(50),
	phone_number varchar(14),
	expected_salary varchar(12),
	gender_id varchar,
	nationality_id varchar,
	photo_id varchar,
	marital_id varchar,
	religion_id varchar,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY(photo_id)
		REFERENCES t_file(id)
);

CREATE TABLE t_candidate (
	id varchar(36) NOT NULL,
	candidate_email varchar(30) NOT NULL,
	candidate_password text NOT NULL,
	candidate_profile_id varchar NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY(id),
	UNIQUE (candidate_email),
	FOREIGN KEY(candidate_profile_id)
		REFERENCES t_candidate_profile(id)
);

CREATE TABLE t_candidate_education (
	id varchar(36) NOT NULL,
	candidate_id varchar NOT NULL,
	institution_name varchar(50) NOT NULL,
	degree_id varchar NOT NULL,
	major_id varchar NOT NULL,
	gpa numeric NOT NULL,
	start_year date NOT NULL,
	end_year date NOT NULL,
	institution_adress TEXT,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY(candidate_id)
		REFERENCES t_candidate(id)
);

CREATE TABLE t_candidate_document (
	id varchar(36) NOT NULL,
	candidate_id varchar NOT NULL,
	file_id varchar NOT NULL,
	document_type_id varchar NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id)
		REFERENCES t_candidate(id)
);

CREATE TABLE t_candidate_experience (
	id varchar(36) NOT NULL,
	candidate_id varchar NOT NULL,
	former_position TEXT NOT NULL,
	former_institution varchar NOT NULL,
	former_location varchar NOT NULL,
	former_jobdesk varchar NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id)
		REFERENCES t_candidate(id)
);

CREATE TABLE t_candidate_skill (
	id varchar(36) NOT NULL,
	candidate_id varchar NOT NULL,
	skill_id varchar,
	skill_name varchar(255),
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id)
		REFERENCES t_candidate(id)
);

CREATE TABLE t_applied_vacancy (
	id VARCHAR(36) NOT NULL,
	candidate_id VARCHAR NOT NULL,
	job_vacancy_id VARCHAR NOT NULL,
	applied_status_id VARCHAR NOT NULL,
	applied_progress_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id)
		REFERENCES t_candidate (id),
	FOREIGN KEY (job_vacancy_id)
		REFERENCES t_job_vacancy (id)
);

CREATE TABLE t_saved_jobs (
	id VARCHAR(36) NOT NULL,
	candidate_id VARCHAR NOT NULL,
	job_vacancy_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id)
		REFERENCES t_candidate (id),
	FOREIGN KEY (job_vacancy_id)
		REFERENCES t_job_vacancy (id)
);

CREATE TABLE t_question_answer (
	id VARCHAR(36) NOT NULL,
	applied_vacancy_id VARCHAR NOT NULL,
	question_option_id VARCHAR NOT NULL,
	question_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (applied_vacancy_id)
		REFERENCES t_applied_vacancy (id)
);