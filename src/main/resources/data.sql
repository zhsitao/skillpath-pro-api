-- 插入示例角色
INSERT INTO roles (title, category) VALUES 
('Frontend Developer', 'Development'),
('DevOps Engineer', 'Operations'),
('Data Scientist', 'Analytics'),
('Cybersecurity Specialist', 'Security');

-- 插入必需技能
INSERT INTO role_required_skills (role_id, required_skills) VALUES 
(1, 'HTML'), (1, 'CSS'), (1, 'JavaScript'), (1, 'React'),
(2, 'Docker'), (2, 'Jenkins'), (2, 'AWS'), (2, 'Linux'),
(3, 'Python'), (3, 'SQL'), (3, 'Pandas'), (3, 'Machine Learning'),
(4, 'Network Security'), (4, 'Cryptography'), (4, 'Security Tools'), (4, 'Risk Assessment');

-- 插入可选技能
INSERT INTO role_optional_skills (role_id, optional_skills) VALUES 
(1, 'TypeScript'), (1, 'Angular'), (1, 'Vue.js'),
(2, 'Kubernetes'), (2, 'Terraform'), (2, 'Ansible'),
(3, 'R'), (3, 'TensorFlow'), (3, 'Scala'),
(4, 'Ethical Hacking'), (4, 'Forensics'), (4, 'Cloud Security');
