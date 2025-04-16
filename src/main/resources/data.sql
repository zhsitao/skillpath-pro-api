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

-- Learning Resources
INSERT INTO learning_resources (name, provider, duration, type, cost, description) VALUES
-- AWS Resources
('AWS Cloud Practitioner', 'AWS Training', '40h', 'Certificate', 299.99, 'Comprehensive certification preparation for AWS Cloud Practitioner'),
('AWS Essentials', 'Coursera', '20h', 'Video', 0, 'Learn AWS fundamentals for free'),
('AWS Deep Dive', 'Udemy', '30h', 'Course', 49.99, 'Advanced AWS concepts and practices'),
('AWS Solutions Architect', 'AWS Training', '60h', 'Certificate', 399.99, 'Professional AWS architecture certification'),

-- React Resources
('React.js Fundamentals', 'Udemy', '15h', 'Video', 0, 'Learn React.js from scratch with hands-on projects'),
('React Advanced Patterns', 'Frontend Masters', '12h', 'Course', 39.99, 'Advanced React patterns and best practices'),
('React Native Basics', 'React Training', '25h', 'Video', 0, 'Build mobile apps with React Native'),
('Advanced React Patterns', 'Frontend Masters', '20h', 'Course', 199.99, 'Advanced React development techniques'),

-- Docker Resources
('Docker Essentials', 'freeCodeCamp', '8h', 'Video', 0, 'Master Docker basics and containerization'),
('Docker in Practice', 'Pluralsight', '15h', 'Course', 29.99, 'Real-world Docker applications'),
('Docker Security', 'Docker Inc', '10h', 'Certificate', 149.99, 'Security best practices for Docker'),

-- Python Resources
('Python for Beginners', 'Coursera', '20h', 'Course', 49.99, 'Introduction to Python programming'),
('Python Data Science', 'DataCamp', '25h', 'Video', 0, 'Python for data analysis'),
('Advanced Python', 'Real Python', '30h', 'Certificate', 79.99, 'Advanced Python concepts'),
('Python Programming Basics', 'Coursera', '15h', 'Video', 0, 'Introduction to Python programming'),
('Python for Data Science', 'DataCamp', '30h', 'Course', 149.99, 'Python data analysis and visualization'),

-- Git Resources
('Git & GitHub Complete Guide', 'freeCodeCamp', '6h', 'Video', 0, 'Learn version control with Git'),
('Git Advanced Topics', 'Atlassian', '8h', 'Video', 0, 'Advanced Git workflows'),
('Git for Teams', 'GitHub', '10h', 'Certificate', 29.99, 'Git in team environments'),
('Git & GitHub Complete Guide', 'GitHub', '10h', 'Video', 0, 'Version control and collaboration'),
('Advanced Git Workflows', 'Atlassian', '15h', 'Course', 149.99, 'Advanced Git techniques'),

-- Java Resources
('Java Spring Boot in Action', 'Pluralsight', '25h', 'Course', 29.99, 'Build enterprise applications'),
('Java Core Concepts', 'Oracle', '20h', 'Video', 0, 'Java fundamentals'),
('Java Design Patterns', 'Manning', '15h', 'Certificate', 49.99, 'Common design patterns in Java'),
('Advanced Java Development', 'Pluralsight', '30h', 'Course', 299.99, 'Advanced Java programming concepts'),
('Spring Boot in Action', 'VMware', '25h', 'Certificate', 399.99, 'Enterprise application development with Spring Boot'),

-- Security Resources
('CompTIA Security+', 'CompTIA', '60h', 'Certificate', 349.00, 'Industry-standard security certification'),
('Cybersecurity Fundamentals', 'Cisco', '30h', 'Video', 0, 'Basic security concepts'),
('Ethical Hacking', 'Offensive Security', '40h', 'Certificate', 999.99, 'Practical security testing'),
('Network Security Fundamentals', 'Cisco', '30h', 'Certificate', 299.99, 'Network security principles'),
('Cryptography Basics', 'Coursera', '15h', 'Video', 0, 'Introduction to cryptography'),

-- Project Management Resources
('Agile Project Management', 'LinkedIn Learning', '10h', 'Video', 0, 'Learn Agile methodologies'),
('JIRA Fundamentals', 'Atlassian', '5h', 'Video', 0, 'Project tracking with JIRA'),
('Scrum Master Certification', 'Scrum.org', '20h', 'Certificate', 199.99, 'Professional Scrum Master certification'),
('Agile Scrum Master', 'Scrum.org', '20h', 'Certificate', 199.99, 'Professional Scrum Master certification'),
('Project Management Professional', 'PMI', '35h', 'Certificate', 499.99, 'PMP certification preparation'),
('Agile Development', 'Atlassian', '10h', 'Video', 0, 'Agile methodology and practices'),
('Risk Management', 'PMI', '15h', 'Course', 199.99, 'Project risk management principles'),

-- DevOps Resources
('Jenkins Pipeline Master', 'CloudBees', '15h', 'Course', 199.99, 'Advanced Jenkins pipeline development'),
('Kubernetes Fundamentals', 'CNCF', '25h', 'Certificate', 299.99, 'Core Kubernetes concepts and practices'),
('Linux System Administration', 'Red Hat', '40h', 'Course', 499.99, 'Complete Linux administration'),
('Terraform Infrastructure as Code', 'HashiCorp', '20h', 'Certificate', 249.99, 'Infrastructure automation with Terraform'),

-- Web Development Resources
('Vue.js Complete Guide', 'Vue School', '25h', 'Course', 149.99, 'Comprehensive Vue.js development'),
('Modern JavaScript', 'freeCodeCamp', '12h', 'Video', 0, 'ES6+ JavaScript features and patterns'),
('Responsive Web Design', 'Udacity', '15h', 'Certificate', 199.99, 'Modern CSS and responsive design'),
('UI/UX Design Principles', 'Design+Code', '20h', 'Course', 299.99, 'User interface and experience design'),

-- Database & Data Resources
('SQL Database Design', 'Stanford Online', '20h', 'Course', 149.99, 'Database modeling and SQL development'),
('MongoDB Complete Guide', 'MongoDB University', '25h', 'Certificate', 199.99, 'NoSQL database development'),
('Data Science Fundamentals', 'Dataquest', '40h', 'Course', 299.99, 'Core concepts of data science'),
('Machine Learning Basics', 'Google', '30h', 'Certificate', 399.99, 'Introduction to machine learning'),
('Statistics for Data Science', 'Khan Academy', '15h', 'Video', 0, 'Statistical analysis fundamentals'),
('R Programming', 'RStudio', '20h', 'Video', 0, 'R language for statistical computing'),

-- Programming Languages Resources
('Node.js Backend Development', 'NodeSchool', '20h', 'Video', 0, 'Server-side JavaScript development'),
('TypeScript Essentials', 'Microsoft', '10h', 'Video', 0, 'TypeScript fundamentals');

-- Resource Skills Mapping
INSERT INTO resource_skills (resource_id, skill_name) VALUES
-- AWS Resources
(1, 'AWS'), (1, 'Cloud Computing'),
(2, 'AWS'), (2, 'Cloud Computing'),
(3, 'AWS'), (3, 'Cloud Computing'),
(4, 'AWS'), (4, 'Cloud Computing'),

-- React Resources
(5, 'React'), (5, 'JavaScript'),
(6, 'React'), (6, 'JavaScript'),
(7, 'React'), (7, 'JavaScript'), (7, 'Mobile Development'),
(8, 'React'), (8, 'JavaScript'),

-- Docker Resources
(9, 'Docker'), (9, 'DevOps'),
(10, 'Docker'), (10, 'DevOps'),
(11, 'Docker'), (11, 'Security'),

-- Python Resources
(12, 'Python'), (12, 'Programming'),
(13, 'Python'), (13, 'Data Science'),
(14, 'Python'), (14, 'Programming'),
(15, 'Python'), (15, 'Programming'),
(16, 'Python'), (16, 'Data Science'),

-- Git Resources
(17, 'Git'), (17, 'Version Control'),
(18, 'Git'), (18, 'Version Control'),
(19, 'Git'), (19, 'Version Control'),
(20, 'Git'), (20, 'Version Control'),
(21, 'Git'), (21, 'Version Control'),

-- Java Resources
(22, 'Java'), (22, 'Spring Boot'),
(23, 'Java'), (23, 'Programming'),
(24, 'Java'), (24, 'Programming'),
(25, 'Java'), (25, 'Programming'),
(26, 'Spring Boot'), (26, 'Java'),

-- Security Resources
(27, 'Security'), (27, 'Network Security'),
(28, 'Security'), (28, 'Network Security'),
(29, 'Security'), (29, 'Network Security'), (29, 'Ethical Hacking'),
(30, 'Network Security'), (30, 'Security'),
(31, 'Cryptography'), (31, 'Security'),

-- Project Management Resources
(32, 'Agile'), (32, 'Project Management'),
(33, 'JIRA'), (33, 'Project Management'),
(34, 'Agile'), (34, 'Project Management'),
(35, 'Agile'), (35, 'Project Management'),
(36, 'Project Management'), (36, 'Leadership'),
(37, 'Agile'), (37, 'Project Management'),
(38, 'Project Management'), (38, 'Risk Management'),

-- DevOps Resources
(39, 'Jenkins'), (39, 'DevOps'),
(40, 'Kubernetes'), (40, 'DevOps'),
(41, 'Linux'), (41, 'System Administration'),
(42, 'Terraform'), (42, 'DevOps'), (42, 'Cloud Computing'),

-- Web Development Resources
(43, 'Vue.js'), (43, 'JavaScript'),
(44, 'JavaScript'), (44, 'Programming'),
(45, 'CSS'), (45, 'HTML'),
(46, 'UI/UX Design'), (46, 'Design'),

-- Database & Data Resources
(47, 'SQL'), (47, 'Database'),
(48, 'MongoDB'), (48, 'Database'),
(49, 'Data Science'), (49, 'Statistics'),
(50, 'Machine Learning'), (50, 'Data Science'),
(51, 'Statistics'), (51, 'Data Science'),
(52, 'R'), (52, 'Data Science'),

-- Programming Languages Resources
(53, 'Node.js'), (53, 'JavaScript'),
(54, 'TypeScript'), (54, 'JavaScript');
