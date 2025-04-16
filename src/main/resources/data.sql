-- Insert test user
INSERT INTO users (email, password, active, locked, failed_login_attempts) VALUES 
('test@abc.com', '$2a$10$7hZlKu0H7CuHFIe7JC3PtOY4Z8Fv9eW1A15wiOSJFvQfhc4kWoA7a', true, false, 0);

-- Insert roles with descriptions
INSERT INTO roles (title, category, description) VALUES 
('Frontend Developer', 'Development', 'Specializes in creating user interfaces and interactive web applications'),
('Backend Developer', 'Development', 'Develops server-side logic and APIs'),
('Full Stack Developer', 'Development', 'Handles both frontend and backend development'),
('DevOps Engineer', 'Operations', 'Manages deployment, scaling, and maintenance of software systems'),
('Data Scientist', 'Analytics', 'Analyzes complex data to help organizations make better decisions'),
('Data Engineer', 'Analytics', 'Builds and maintains data pipelines and infrastructure'),
('Cybersecurity Specialist', 'Security', 'Protects systems and networks from security threats');

-- Insert required skills with proficiency levels
INSERT INTO role_required_skills (role_id, skill_name, required_level) VALUES 
-- Frontend Developer skills
(1, 'HTML', 'INTERMEDIATE'),
(1, 'CSS', 'INTERMEDIATE'),
(1, 'JavaScript', 'ADVANCED'),
(1, 'React', 'INTERMEDIATE'),
(1, 'Git', 'INTERMEDIATE'),

-- Backend Developer skills
(2, 'Java', 'ADVANCED'),
(2, 'Spring Boot', 'INTERMEDIATE'),
(2, 'SQL', 'INTERMEDIATE'),
(2, 'Git', 'INTERMEDIATE'),
(2, 'REST API', 'ADVANCED'),

-- Full Stack Developer skills
(3, 'JavaScript', 'ADVANCED'),
(3, 'React', 'INTERMEDIATE'),
(3, 'Java', 'INTERMEDIATE'),
(3, 'SQL', 'INTERMEDIATE'),
(3, 'Git', 'INTERMEDIATE'),

-- DevOps Engineer skills
(4, 'Docker', 'ADVANCED'),
(4, 'Jenkins', 'ADVANCED'),
(4, 'AWS', 'INTERMEDIATE'),
(4, 'Linux', 'ADVANCED'),
(4, 'Git', 'ADVANCED'),

-- Data Scientist skills
(5, 'Python', 'ADVANCED'),
(5, 'SQL', 'INTERMEDIATE'),
(5, 'Machine Learning', 'INTERMEDIATE'),
(5, 'Statistics', 'ADVANCED'),
(5, 'Git', 'BEGINNER'),

-- Data Engineer skills
(6, 'Python', 'INTERMEDIATE'),
(6, 'SQL', 'ADVANCED'),
(6, 'Spark', 'INTERMEDIATE'),
(6, 'AWS', 'INTERMEDIATE'),
(6, 'Git', 'INTERMEDIATE'),

-- Cybersecurity Specialist skills
(7, 'Network Security', 'ADVANCED'),
(7, 'Python', 'INTERMEDIATE'),
(7, 'Linux', 'ADVANCED'),
(7, 'Security Tools', 'ADVANCED'),
(7, 'AWS', 'INTERMEDIATE');

-- Insert optional skills with recommended levels
INSERT INTO role_optional_skills (role_id, skill_name, recommended_level) VALUES 
-- Frontend Developer optional skills
(1, 'TypeScript', 'INTERMEDIATE'),
(1, 'Vue.js', 'INTERMEDIATE'),
(1, 'UI/UX Design', 'BEGINNER'),

-- Backend Developer optional skills
(2, 'Python', 'INTERMEDIATE'),
(2, 'Node.js', 'INTERMEDIATE'),
(2, 'MongoDB', 'INTERMEDIATE'),

-- Full Stack Developer optional skills
(3, 'TypeScript', 'INTERMEDIATE'),
(3, 'Docker', 'INTERMEDIATE'),
(3, 'AWS', 'INTERMEDIATE'),

-- DevOps Engineer optional skills
(4, 'Kubernetes', 'INTERMEDIATE'),
(4, 'Python', 'INTERMEDIATE'),
(4, 'Terraform', 'INTERMEDIATE'),

-- Data Scientist optional skills
(5, 'Deep Learning', 'INTERMEDIATE'),
(5, 'R', 'INTERMEDIATE'),
(5, 'AWS', 'BEGINNER'),

-- Data Engineer optional skills
(6, 'Kafka', 'INTERMEDIATE'),
(6, 'Docker', 'INTERMEDIATE'),
(6, 'Machine Learning', 'BEGINNER'),

-- Cybersecurity Specialist optional skills
(7, 'Cryptography', 'INTERMEDIATE'),
(7, 'Docker', 'INTERMEDIATE'),
(7, 'SQL', 'BEGINNER');

-- Insert skill descriptions
INSERT INTO role_skill_descriptions (role_id, skill_name, description) VALUES 
-- Common skills across roles
(1, 'Git', 'Version control system for tracking code changes'),
(2, 'Git', 'Version control system for tracking code changes'),
(3, 'Git', 'Version control system for tracking code changes'),
(4, 'Git', 'Version control system for tracking code changes'),
(5, 'Git', 'Version control system for tracking code changes'),
(6, 'Git', 'Version control system for tracking code changes'),

-- Frontend Developer skill descriptions
(1, 'HTML', 'Core markup language for web pages'),
(1, 'CSS', 'Styling and layout for web applications'),
(1, 'JavaScript', 'Programming language for interactive web features'),
(1, 'React', 'Popular library for building user interfaces'),
(1, 'TypeScript', 'Typed superset of JavaScript'),
(1, 'Vue.js', 'Progressive framework for web interfaces'),
(1, 'UI/UX Design', 'User interface and experience design principles'),

-- Backend Developer skill descriptions
(2, 'Java', 'Enterprise-level programming language'),
(2, 'Spring Boot', 'Java-based framework for building web applications'),
(2, 'SQL', 'Database querying language'),
(2, 'REST API', 'Web service architectural style'),
(2, 'Python', 'Versatile programming language'),
(2, 'Node.js', 'JavaScript runtime for server-side development'),
(2, 'MongoDB', 'NoSQL database system'),

-- Full Stack Developer skill descriptions
(3, 'JavaScript', 'Programming language for web development'),
(3, 'React', 'UI library for web applications'),
(3, 'Java', 'Backend programming language'),
(3, 'SQL', 'Database management'),
(3, 'TypeScript', 'Typed JavaScript development'),
(3, 'Docker', 'Container platform'),
(3, 'AWS', 'Cloud platform services'),

-- DevOps Engineer skill descriptions
(4, 'Docker', 'Container platform for deployment'),
(4, 'Jenkins', 'Automation server for CI/CD'),
(4, 'AWS', 'Cloud computing services'),
(4, 'Linux', 'Operating system for servers'),
(4, 'Kubernetes', 'Container orchestration'),
(4, 'Python', 'Automation scripting'),
(4, 'Terraform', 'Infrastructure as code tool'),

-- Data Scientist skill descriptions
(5, 'Python', 'Primary language for data analysis'),
(5, 'SQL', 'Data querying and analysis'),
(5, 'Machine Learning', 'Building predictive models'),
(5, 'Statistics', 'Statistical analysis and inference'),
(5, 'Deep Learning', 'Neural network architectures'),
(5, 'R', 'Statistical programming'),
(5, 'AWS', 'Cloud computing for data science'),

-- Data Engineer skill descriptions
(6, 'Python', 'Data processing and ETL'),
(6, 'SQL', 'Database management and optimization'),
(6, 'Spark', 'Big data processing'),
(6, 'AWS', 'Cloud infrastructure for data'),
(6, 'Kafka', 'Stream processing platform'),
(6, 'Docker', 'Containerization for data pipelines'),
(6, 'Machine Learning', 'ML pipeline development'),

-- Cybersecurity Specialist skill descriptions
(7, 'Network Security', 'Network protection and monitoring'),
(7, 'Python', 'Security automation and scripting'),
(7, 'Linux', 'Server security and hardening'),
(7, 'Security Tools', 'Security testing and monitoring tools'),
(7, 'AWS', 'Cloud security practices'),
(7, 'Cryptography', 'Encryption and security protocols'),
(7, 'Docker', 'Container security'),
(7, 'SQL', 'Database security');
