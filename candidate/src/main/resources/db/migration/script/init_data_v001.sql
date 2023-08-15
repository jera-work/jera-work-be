-- buat bikin akun system
DO $$
DECLARE profileId varchar;
BEGIN
	INSERT INTO t_candidate_profile(id, profile_name, created_by, created_at, is_active, ver) VALUES 
		((SELECT uuid_generate_v4()), 'SYSTEM-CANDIDATE','',now(), true, 0) RETURNING id INTO profileId;
	INSERT INTO t_candidate(id, candidate_email, candidate_password, candidate_profile_id, created_by, created_at, is_active, ver) VALUES
	((SELECT uuid_generate_v4()), 'system-cdt@email.com', '$2a$12$BBpQCUCMCieR6SmAiGeY8O7o1ckl2hn4ibAWEcTfq2vO/rANjDIdu',  (profileId), '', now(), true, 0);
END $$;