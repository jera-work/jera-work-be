INSERT INTO t_gender (id, gender_code, gender_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'MAL', 'Male', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'FEM', 'Female', '', now(), true, 0);

INSERT INTO t_nationality(id, nationality_code, nationality_name, created_by, created_at, is_active, ver) VALUES 
	((SELECT uuid_generate_v4()), 'IDN', 'Indonesia', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'MY', 'Malaysia', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'PH', 'Philippines', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'SG', 'Singapore', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'KBJ', 'Kamboja', '', now(), true, 0);

INSERT INTO t_document_type(id, type_code, type_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'CV', 'CV', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'KK', 'KK', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'IDC', 'ID Card', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'CRT', 'Certificate', '', now(), true, 0);

INSERT INTO t_degree(id, degree_code, degree_name, created_by, created_at, is_active, ver) VALUES 
	((SELECT uuid_generate_v4()), 'BCH', 'S1 - Bachelor', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'MSR', 'S2 - Master', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'DCT', 'S3 - Doctor', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'HGH', 'Senior High School', '', now(), true, 0);

INSERT INTO t_major(id, major_code, major_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'CSC', 'Computer Science', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'ACC', 'Accountant', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'IFT', 'Information Technology', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'MGM', 'Management', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'DKV', 'DKV', '', now(), true, 0);

INSERT INTO t_religion(id, religion_code, religion_name, created_by, created_at, is_active, ver) VALUES 
	((SELECT uuid_generate_v4()), 'CHP', 'Christian Protestant', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'CHC', 'Christian Catholic', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'MSL', 'Moslem', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'HND', 'Hinduism', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'BDH', 'Buddhism', '', now(), true, 0);

INSERT INTO t_marital(id, marital_code , marital_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'MAR', 'Married', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'SNG', 'Single', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'DVC', 'Divorced', '', now(), true, 0);

INSERT INTO t_role(id, role_code, role_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'SYS', 'System', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'SA', 'Super Admin', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'ADM', 'Admin', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'HR', 'HR', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'USR', 'User', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'CND', 'Candidate', '', now(), true, 0);

INSERT INTO t_city(id, city_code, city_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'JKT', 'Jakarta', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'BDG', 'Bandung', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'BGR', 'Bogor', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'SMD', 'Sumedang', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'GRT', 'Garut', '', now(), true, 0);

INSERT INTO t_skill(id, skill_code, skill_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'JAV', 'Java', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'ANG', 'Angular', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'TYP', 'Typescript', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'GPD', 'Graphics Design', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'PHP', 'PHP', '', now(), true, 0);

INSERT INTO t_experience_level(id, level_code, level_name, created_by, created_at, is_active, ver) VALUES 
	((SELECT uuid_generate_v4()), 'JNR', 'Junior / Entry Level', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'MID', 'Mid - Senior Level', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'DIR', 'Director', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'EXE', 'Executive', '', now(), true, 0);

INSERT INTO t_available_status(id, status_code, status_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'OPN', 'Open', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'CLS', 'Closed', '', now(), true, 0);

INSERT INTO t_age_vacancy(id, age_code, age_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'AG1', 'Below 20', '', now(), true, 0);

INSERT INTO t_job_type(id, type_code, type_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'FT', 'Fulltime', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'PT', 'Part Time', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'IT', 'Intern', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'FL', 'Freelance', '', now(), true, 0);

INSERT INTO t_applied_status(id, status_code, status_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'ACT', 'Active', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'CLS', 'Closed', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'RJC', 'Rejected', '', now(), true, 0);

INSERT INTO t_applied_progress(id, progress_code, progress_name, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'APP', 'Application', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'ASS', 'Assesment', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'INT', 'Interview User', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'MCU', 'Medical Checkup', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'OFL', 'Offering Letter', '', now(), true, 0),
	((SELECT uuid_generate_v4()), 'HIR', 'Hired', '', now(), true, 0);
	
-- buat bikin akun super admin & system & company

DO $$
DECLARE companyFileId varchar;
DECLARE companyId varchar;
DECLARE profileSystemId varchar;
DECLARE profileSuperAdminId varchar;
BEGIN
	INSERT INTO t_file(id, file_content, file_ext, created_by, created_at, is_active, ver) VALUES
		((SELECT uuid_generate_v4()), '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',
				'png', '', now(), true, 0) RETURNING id INTO companyFileId;
	INSERT INTO t_company(id, company_code, company_name, photo_id, description, address, phone_number, created_by, created_at, is_active, ver) VALUES
		((SELECT uuid_generate_v4()), 'JRW', 'Jera Works', (companyFileId), 'Bismillah', 'Jl Raya', '08123123', '', now(), true, 0) RETURNING id INTO companyId;
	INSERT INTO t_profile(id, profile_name, profile_phone, profile_address, company_id, created_by, created_at, is_active, ver) VALUES 
		((SELECT uuid_generate_v4()), 'SYSTEM-ADMIN', '', '', (companyId), '',now(), true, 0) RETURNING id	INTO profileSystemId;
	INSERT INTO t_profile(id, profile_name, profile_phone, profile_address, company_id, created_by, created_at, is_active, ver) VALUES 
		((SELECT uuid_generate_v4()), 'Super Admin', '', '', (companyId), '',now(), true, 0) RETURNING id INTO profileSuperAdminId;
	INSERT INTO t_user(id, user_email, user_password, profile_id, role_id, created_by, created_at, is_active, ver) VALUES
		((SELECT uuid_generate_v4()), 'system-adm@email.com', '$2a$12$BBpQCUCMCieR6SmAiGeY8O7o1ckl2hn4ibAWEcTfq2vO/rANjDIdu',  (profileSystemId), 
			(
				SELECT 
					tr.id 
				FROM
					t_role tr 
				WHERE
					tr.role_code = 'SYS'
			), '', now(), true, 0),
		((SELECT uuid_generate_v4()), 'thejosepvictorr@gmail.com', '$2a$12$BBpQCUCMCieR6SmAiGeY8O7o1ckl2hn4ibAWEcTfq2vO/rANjDIdu',  (profileSuperAdminId),
			(
				SELECT 
					tr.id 
				FROM
					t_role tr 
				WHERE
					tr.role_code = 'SA'
			), '', now(), true, 0);
END $$;