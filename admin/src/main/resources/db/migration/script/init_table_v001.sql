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

CREATE TABLE t_role (
	id VARCHAR(36) NOT NULL,
	role_code VARCHAR(5) NOT NULL,
	role_name VARCHAR(15) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (role_code)
);

CREATE TABLE t_nationality (
	id varchar(36) NOT NULL,
	nationality_name varchar(36) NOT NULL,
	nationality_code varchar(8) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY (id)
);

CREATE TABLE t_age_vacancy (
	id VARCHAR(36) NOT NULL,
	age_code VARCHAR(8) NOT NULL,
	age_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (age_code)	
);

CREATE TABLE t_available_status (
	id VARCHAR(36) NOT NULL,
	status_code VARCHAR(8) NOT NULL,
	status_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (status_code)
);

CREATE TABLE t_job_type (
	id VARCHAR(36) NOT NULL,
	type_code VARCHAR(8) NOT NULL,
	type_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (type_code)
);

CREATE TABLE t_religion (
	id VARCHAR(36) NOT NULL,
	religion_code VARCHAR(8) NOT NULL,
	religion_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (religion_code)
);

CREATE TABLE t_marital (
	id VARCHAR(36) NOT NULL,
	marital_code VARCHAR(8) NOT NULL,
	marital_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (marital_code)
);


CREATE TABLE t_gender (
	id VARCHAR(36) NOT NULL,
	gender_code VARCHAR(8) NOT NULL,
	gender_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (gender_code)
);


CREATE TABLE t_major (
	id VARCHAR(36) NOT NULL,
	major_code VARCHAR(8) NOT NULL,
	major_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (major_code)
);

CREATE TABLE t_degree (
	id VARCHAR(36) NOT NULL,
	degree_code VARCHAR(8) NOT NULL,
	degree_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (degree_code)
);

CREATE TABLE t_city (
	id VARCHAR(36) NOT NULL,
	city_code VARCHAR(8) NOT NULL,
	city_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (city_code)
);

CREATE TABLE t_document_type (
	id VARCHAR(36) NOT NULL,
	type_code VARCHAR(8) NOT NULL,
	type_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (type_code)
);

CREATE TABLE t_experience_level (
	id VARCHAR(36) NOT NULL,
	level_code VARCHAR(8) NOT NULL,
	level_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (level_code)
);

CREATE TABLE t_skill (
	id VARCHAR(36) NOT NULL,
	skill_code VARCHAR(8) NOT NULL,
	skill_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (skill_code)
);

CREATE TABLE t_applied_progress (
	id VARCHAR(36) NOT NULL,
	progress_code VARCHAR(8) NOT NULL,
	progress_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (progress_code)
);

CREATE TABLE t_applied_status (
	id VARCHAR(36) NOT NULL,
	status_code VARCHAR(8) NOT NULL,
	status_name VARCHAR(30) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (status_code)
);

CREATE TABLE t_company (
	id VARCHAR(36) NOT NULL,
	company_code VARCHAR(8) NOT NULL,
	company_name VARCHAR(30) NOT NULL,
	photo_id VARCHAR NOT NULL,
	description TEXT NOT NULL,
	address TEXT NOT NULL,
	phone_number VARCHAR(14) NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (company_code),
	FOREIGN KEY (photo_id)
		REFERENCES t_file (id)
);

CREATE TABLE t_profile (
	id varchar(36) NOT NULL,
	profile_name varchar(50) NOT NULL,
	profile_phone varchar(14),
	profile_address TEXT,
	photo_id varchar,
	company_id varchar NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY(photo_id)
		REFERENCES t_file(id),
	FOREIGN KEY(company_id)
		REFERENCES t_company(id)
);

CREATE TABLE t_user (
	id varchar(36) NOT NULL,
	user_email varchar(40) NOT NULL,
	user_password varchar(8) NOT NULL,
	profile_id varchar NOT NULL,
	role_id varchar NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY(profile_id)
		REFERENCES t_profile(id),
	FOREIGN KEY(role_id)
		REFERENCES t_role(id)
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
	PRIMARY KEY (id),
	FOREIGN KEY (degree_id)
		REFERENCES t_degree (id),
	FOREIGN KEY (gender_id)
		REFERENCES t_gender (id),
	FOREIGN KEY (age_vacancy_id)
		REFERENCES t_age_vacancy (id),
	FOREIGN KEY (job_type_id)
		REFERENCES t_job_type (id),
	FOREIGN KEY (city_id)
		REFERENCES t_city (id)
);

CREATE TABLE t_job_vacancy (
	id VARCHAR(36) NOT NULL,
	vancacy_code VARCHAR(8) NOT NULL,
	vacancy_title VARCHAR(36) NOT NULL,
	pic_hr_id VARCHAR NOT NULL,
	pic_user_id VARCHAR NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	exp_level_id VARCHAR NOT NULL,
	available_status_id VARCHAR NOT NULL,
	candidate_total INT,
	company_id VARCHAR NOT NULL,
	vacancy_description_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (pic_hr_id)
		REFERENCES t_user (id),
	FOREIGN KEY (pic_user_id)
		REFERENCES t_user (id),
	FOREIGN KEY (exp_level_id)
		REFERENCES t_experience_level (id),
	FOREIGN KEY (available_status_id)
		REFERENCES t_available_status (id),
	FOREIGN KEY (company_id)
		REFERENCES t_company (id),
	FOREIGN KEY (vacancy_description_id)
		REFERENCES t_vacancy_description (id)
);

CREATE TABLE t_question (
	id VARCHAR(36) NOT NULL,
	question_body TEXT NOT NULL,
	question_code VARCHAR(8) NOT NULL,
	job_vacancy_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (job_vacancy_id)
		REFERENCES t_job_vacancy (id)
);

CREATE TABLE t_question_option(
	id VARCHAR(36) NOT NULL,
	option_label TEXT NOT NULL,
	is_correct BOOLEAN NOT NULL,
	question_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (question_id)
		REFERENCES t_question (id)
);

CREATE TABLE t_candidate_profile (
	id varchar(36) NOT NULL,
	profile_name varchar(50) NOT NULL,
	profile_address varchar(50) NOT NULL,
	phone_number varchar(14) NOT NULL,
	expected_salary varchar,
	gender_id varchar NOT NULL,
	nationality_id varchar NOT NULL,
	photo_id varchar,
	marital_id varchar NOT NULL,
	religion_id varchar NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY(gender_id)
		REFERENCES t_gender(id),
	FOREIGN KEY(nationality_id)
		REFERENCES t_nationality(id),
	FOREIGN KEY(photo_id)
		REFERENCES t_file(id),
	FOREIGN KEY(marital_id)
		REFERENCES t_marital(id),
	FOREIGN KEY(religion_id)
		REFERENCES t_religion(id)
);

CREATE TABLE t_candidate (
	id varchar(36) NOT NULL,
	candidate_email varchar(30) NOT NULL,
	candidate_profile_id varchar NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY(id),
	FOREIGN KEY(candidate_profile_id)
		REFERENCES t_candidate_profile(id)
);

CREATE TABLE t_candidate_education (
	id varchar(36) NOT NULL,
	candidate_id varchar NOT NULL,
	institution_name varchar(50) NOT NULL,
	degree_id varchar NOT NULL,
	majors_id varchar NOT NULL,
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
		REFERENCES t_candidate(id),
	FOREIGN KEY (file_id)
		REFERENCES t_file(id),
	FOREIGN KEY (document_type_id)
		REFERENCES t_document_type(id)
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
	skill_id varchar NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id)
		REFERENCES t_candidate(id),
	FOREIGN KEY (skill_id)
		REFERENCES t_skill(id)
);

CREATE TABLE t_hired_employee (
	id VARCHAR(36) NOT NULL,
	candidate_id VARCHAR NOT NULL,
	company_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (candidate_id)
		REFERENCES t_candidate (id),
	FOREIGN KEY (company_id)
		REFERENCES t_company (id)
);

CREATE TABLE t_blacklist_employee (
	id VARCHAR(36) NOT NULL,
	employee_id VARCHAR NOT NULL,
	company_id VARCHAR NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (employee_id)
		REFERENCES t_hired_employee (id),
	FOREIGN KEY (company_id)
		REFERENCES t_company (id)
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
		REFERENCES t_job_vacancy (id),
	FOREIGN KEY (applied_status_id)
		REFERENCES t_applied_status (id),
	FOREIGN KEY (applied_progress_id)
		REFERENCES t_applied_progress (id)
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
		REFERENCES t_applied_vacancy (id),
	FOREIGN KEY (question_option_id) 
		REFERENCES t_question_option (id),
	FOREIGN KEY (question_id)
		REFERENCES t_question (id)
);


CREATE TABLE t_assessment_vacancy (
	id VARCHAR(36) NOT NULL,
	applied_vacancy_id VARCHAR NOT NULL,
	is_question BOOLEAN,
	score FLOAT,
	notes TEXT,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	assessment_location TEXT NOT NULL,
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

CREATE TABLE t_interview_vacancy (
	id VARCHAR(36) NOT NULL,
	applied_vacancy_id VARCHAR NOT NULL,
	notes TEXT,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	interview_location TEXT NOT NULL,
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

CREATE TABLE t_mcu_vacancy (
	id VARCHAR(36) NOT NULL,
	applied_vacancy_id VARCHAR NOT NULL,
	file_id VARCHAR,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	created_by varchar NOT NULL,
	created_at timestamp NOT NULL,
	updated_by varchar,
	updated_at timestamp,
	is_active boolean NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (applied_vacancy_id)
		REFERENCES t_applied_vacancy (id),
	FOREIGN KEY (file_id)
		REFERENCES t_file (id)
);

CREATE TABLE t_offering(
	id VARCHAR(36) NOT NULL,
	applied_vacancy_id VARCHAR NOT NULL,
	is_approve BOOLEAN,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	description TEXT NOT NULL,
	offering_location TEXT NOT NULL,
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
