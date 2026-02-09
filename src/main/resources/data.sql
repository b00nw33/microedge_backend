-- src/main/resources/data.sql

USE microedge;

-- Categories
INSERT INTO category (name) VALUES 
  ('Construction'),
  ('Healthcare'),
  ('Cloud Computing'),
  ('Mobile Development'),
  ('DevOps');

-- Trainers (6 total)
INSERT INTO users (role, first_name, last_name, email, password) VALUES 
  ('TRAINER', 'Alex', 'Johnson', 'alex.johnson@example.com', '$2a$10$cOtHgvUYiOmIKMQR86ZShugS.Qyr4nwhI78npjIQkVNDNQ2kB/yNW'),
  ('TRAINER', 'Mei Lin', 'Tan', 'meilin.tan@example.com', '$2a$10$cOtHgvUYiOmIKMQR86ZShugS.Qyr4nwhI78npjIQkVNDNQ2kB/yNW'),
  ('TRAINER', 'David', 'Chen', 'david.chen@example.com', 'trainerPass789'),
  ('TRAINER', 'Aisha', 'Rahman', 'aisha.rahman@example.com', 'trainerPass101'),
  ('TRAINER', 'Carlos', 'Mendez', 'carlos.mendez@example.com', 'trainerPass202'),
  ('TRAINER', 'Sophie', 'Dubois', 'sophie.dubois@example.com', 'trainerPass303');

-- Trainees (10 total)
INSERT INTO users (role, first_name, last_name, email, password) VALUES 
  ('TRAINEE', 'Sarah', 'Williams', 'sarah.williams@example.com', '$2a$10$cOtHgvUYiOmIKMQR86ZShugS.Qyr4nwhI78npjIQkVNDNQ2kB/yNW'),
  ('TRAINEE', 'Raj', 'Patel', 'raj.patel@example.com', '$2a$10$cOtHgvUYiOmIKMQR86ZShugS.Qyr4nwhI78npjIQkVNDNQ2kB/yNW'),
  ('TRAINEE', 'Emma', 'Liu', 'emma.liu@example.com', 'traineePass789'),
  ('TRAINEE', 'James', 'Wilson', 'james.wilson@example.com', 'traineePass101'),
  ('TRAINEE', 'Fatima', 'Al-Mansoori', 'fatima.almansoori@example.com', 'traineePass202'),
  ('TRAINEE', 'Liam', 'O’Connor', 'liam.oconnor@example.com', 'traineePass303'),
  ('TRAINEE', 'Yuki', 'Sato', 'yuki.sato@example.com', 'traineePass404'),
  ('TRAINEE', 'Noah', 'Kim', 'noah.kim@example.com', 'traineePass505'),
  ('TRAINEE', 'Olivia', 'Brown', 'olivia.brown@example.com', 'traineePass606'),
  ('TRAINEE', 'Mateo', 'Garcia', 'mateo.garcia@example.com', 'traineePass707');

-- Courses (8 total)
INSERT INTO course (trainer_id, category_id, title, description, duration, level, image_url) VALUES
  -- Construction
  (1, 1, 'Construction Site Basics', 'This course introduces learners to the fundamentals of working on a construction site. It covers common site roles, tools, and basic terminology used across projects. Learners will understand essential safety rules, site access requirements, and expected workplace behavior. The course is designed for individuals who are new to construction environments.', 120, 'BEGINNER', 'uploads/construction_site_basics.jpg'),
  (4, 1, 'Construction Workflow & Risk Awareness', 'This course explores how construction projects progress from planning through execution. Learners will gain insight into task sequencing, team coordination, and common on-site risks. The course emphasizes recognizing hazards early and communicating effectively to prevent delays or incidents. It is ideal for workers with hands-on site experience.', 150, 'ADVANCED', 'uploads/workflow_risk_awareness.jpg'),

  -- Healthcare
  (2, 2, 'Healthcare Environment Essentials', 'This course provides an overview of how healthcare facilities operate. Learners will be introduced to patient safety principles, hygiene practices, and professional conduct. The course explains basic roles, workflows, and communication within healthcare settings. It is suitable for new healthcare workers and support staff.', 180, 'BEGINNER', 'uploads/healthcare_essentials.jpg'),
  (2, 2, 'Patient Care & Clinical Safety', 'This course builds on foundational knowledge to strengthen patient care and safety practices. Learners will explore infection control, safe patient handling, and clinical risk awareness. The course also emphasizes teamwork and clear communication in care environments. It is designed for staff involved in patient-facing or clinical support roles.', 240, 'INTERMEDIATE', 'uploads/patientcare_clinicalsafety.png'),

-- Construction
  (2, 1, 'Construction Quality & Productivity', 'This course focuses on improving work quality and efficiency on construction sites. Learners will understand quality standards, proper material handling, and practical time management techniques. The course emphasizes reducing rework, identifying defects early, and maintaining productivity under real site conditions. It is suitable for workers seeking to improve performance and reliability on projects.', 140, 'INTERMEDIATE', 'uploads/5-1-construction-quality-and-productivity.jpg'),

  (3, 1, 'Construction Environmental & Site Responsibility', 'This course introduces environmental responsibility and public safety practices in construction environments. Learners will explore waste management, resource efficiency, and methods to reduce environmental impact on site. The course also highlights the importance of protecting surrounding communities and maintaining a responsible site presence. It is designed for workers operating on active or urban construction sites.', 120, 'BEGINNER', 'uploads/6-1-construction-environmental-and-site-responsibility.jpg'),

  -- Healthcare
  (5, 2, 'Healthcare Infection Prevention', 'This course provides essential knowledge on preventing infection in healthcare settings. Learners will understand how infections spread, proper hand hygiene, correct use of PPE, and effective cleaning practices. The course emphasizes protecting patients, colleagues, and oneself in daily clinical work. It is suitable for healthcare workers in both clinical and support roles.', 130, 'BEGINNER', 'uploads/7-1-healthcare-infection-prevention.jpg'),

  (6, 2, 'Healthcare Communication & Professionalism', 'This course develops core communication and professionalism skills for healthcare environments. Learners will practice patient interaction, teamwork, ethical behavior, and stress management. The course focuses on maintaining clear communication and professional conduct in fast-paced and high-pressure settings. It is ideal for healthcare staff aiming to improve service quality and teamwork.', 110, 'INTERMEDIATE', 'uploads/8-1-healthcare-communication-and-professionalism.jpg');

-- Modules (with order_index and unique titles per course)
INSERT INTO module (course_id, title, content_text, video_url, order_index) VALUES 
  -- Course 1: Construction Safety Basics
  (1, 'Safety & PPE', 'Focuses on identifying "The Big Four" site hazards and mastering the correct use of protective gear to ensure regulatory compliance and worker safety.', 'uploads/construction_site_basics/video_00.mp4', 1),
  (1, 'Communication', 'Teaches the essential "language" of the site, including interpreting safety signage, using universal hand signals for machinery, and maintaining professional radio etiquette.', 'uploads/construction_site_basics/video_01.mp4', 2),
  (1, 'Tools & Housekeeping', 'Covers the safe operation of hand and power tools alongside "clean as you go" practices to prevent workplace accidents and organize materials efficiently.', 'uploads/construction_site_basics/video_02.mp4', 3),
  (1, 'Blueprints & Documentation', 'Provides a foundation in reading technical plans and measurements while emphasizing the importance of accurate daily reporting for project tracking.', 'uploads/construction_site_basics/video_03.mp4', 4),

  -- Course 2: Construction Workflow & Risk Awareness
  (2, 'Construction Project Phases', 'Planning, execution, and close-out. Role of each phase.', 'https://example.com/react-hooks.mp4', 1),
  (2, 'Task Sequencing & Coordination', 'Trade dependencies and avoiding workflow conflicts.', 'https://example.com/react-perf.mp4', 2),
  (2, 'On-Site Risk Identification', 'Common construction hazards and high-risk work activities.', 'https://example.com/react-testing.mp4', 3),

  -- Course 3: Healthcare Environment Essentials
  (3, 'Healthcare Facility Overview', 'Types of healthcare settings and department functions.', 'https://example.com/pandas-basics.mp4', 1),
  (3, 'Patient Safety Basics', 'Safety principles and common patient risks.', 'https://example.com/pandas-clean.mp4', 2),
  (3, 'Hygiene & Cleanliness', 'Hand hygiene and clean environment standards.', 'https://example.com/matplotlib.mp4', 3),

  -- Course 4: Patient Care & Clinical Safety
  (4, 'Infection Control', 'Standard precautions and preventing cross-contamination.', 'https://example.com/ml-intro.mp4', 1),
  (4, 'Safe Patient Handling', 'Lifting and transfer methods and assistive devices.', 'https://example.com/ml-model.mp4', 2),
  (4, 'Clinical Risk Awareness', 'Identifying patient hazards and error prevention.', 'https://example.com/ml-eval.mp4', 3),

-- Course 5: Construction Quality & Productivity
  (5, 'Quality Standards', 'Introduces common construction quality benchmarks, tolerances, and inspection points to reduce rework and meet project specifications.', 'uploads/construction_quality/video_01.mp4', 1),
  (5, 'Material Handling', 'Covers proper storage, handling, and protection of materials to prevent damage, waste, and costly delays on site.', 'uploads/construction_quality/video_02.mp4', 2),
  (5, 'Time & Task Management', 'Teaches micro-planning techniques, task sequencing, and daily goal setting to improve productivity and workflow efficiency.', 'uploads/construction_quality/video_03.mp4', 3),
  (5, 'Defect Reporting', 'Explains how to identify, document, and communicate defects clearly to supervisors and quality teams for fast resolution.', 'uploads/construction_quality/video_04.mp4', 4),

  -- Course 6: Construction Environmental & Site Responsibility
  (6, 'Environmental Awareness', 'Builds awareness of environmental risks such as dust, noise, and runoff, and how workers can minimize impact on surrounding areas.', 'uploads/construction_environment/video_01.mp4', 1),
  (6, 'Waste Management', 'Demonstrates proper segregation, recycling, and disposal of construction waste in compliance with site policies.', 'uploads/construction_environment/video_02.mp4', 2),
  (6, 'Energy & Resource Use', 'Highlights efficient use of water, fuel, and electricity on site to reduce costs and environmental footprint.', 'uploads/construction_environment/video_03.mp4', 3),
  (6, 'Community & Public Safety', 'Focuses on protecting pedestrians, nearby residents, and public infrastructure through safe site boundaries and practices.', 'uploads/construction_environment/video_04.mp4', 4),

  -- Course 7: Healthcare Infection Prevention
  (7, 'Chain of Infection', 'Explains how infections spread in healthcare settings and identifies key breakpoints to prevent transmission.', 'uploads/healthcare_infection/video_01.mp4', 1),
  (7, 'Hand Hygiene', 'Reinforces correct handwashing and sanitizing techniques using real-world clinical scenarios.', 'uploads/healthcare_infection/video_02.mp4', 2),
  (7, 'PPE in Healthcare', 'Covers correct selection, donning, and removal of PPE to protect both patients and healthcare workers.', 'uploads/healthcare_infection/video_03.mp4', 3),
  (7, 'Cleaning & Disinfection', 'Teaches safe cleaning routines for equipment and surfaces to maintain a hygienic clinical environment.', 'uploads/healthcare_infection/video_04.mp4', 4),

  -- Course 8: Healthcare Communication & Professionalism
  (8, 'Patient Communication', 'Develops clear, respectful communication skills for interacting with patients of diverse backgrounds and needs.', 'uploads/healthcare_communication/video_01.mp4', 1),
  (8, 'Team Coordination', 'Emphasizes collaboration, role clarity, and handover practices within multidisciplinary healthcare teams.', 'uploads/healthcare_communication/video_02.mp4', 2),
  (8, 'Ethics & Confidentiality', 'Introduces ethical responsibilities and patient data protection principles in everyday clinical work.', 'uploads/healthcare_communication/video_03.mp4', 3),
  (8, 'Stress & Professional Conduct', 'Provides techniques for managing stress, maintaining professionalism, and responding calmly under pressure.', 'uploads/healthcare_communication/video_04.mp4', 4);

-- Enrollments (mix of completed/in-progress)
INSERT INTO trainee_course (users_id, course_id, complete_status, complete_date) VALUES 
  -- Sarah: Completed Spring Boot
  (1, 1, 1, '2026-01-20 10:30:00'),
  
  -- Raj: In-progress Python
  (2, 3, 0, '2026-01-24 09:00:00'),
  
  -- Emma: Completed Python, in-progress Spring Boot
  (3, 3, 1, '2026-01-22 14:15:00'),
  (3, 1, 0, '2026-01-24 11:00:00'),
  
  -- James: Enrolled in React
  (4, 2, 0, '2026-01-24 13:00:00'),
  
  -- Fatima: Completed AWS
  (5, 5, 1, '2026-01-23 16:00:00'),
  
  -- Liam: Enrolled in Python + Spring Boot
  (6, 3, 0, '2026-01-24 10:00:00'),
  (6, 1, 0, '2026-01-24 10:30:00'),
  
  -- Yuki: Completed Flutter
  (7, 7, 1, '2026-01-21 12:00:00'),
  
  -- Noah: Enrolled in AWS + CI/CD
  (8, 5, 0, '2026-01-24 14:00:00'),
  (8, 8, 0, '2026-01-24 14:30:00'),
  
  -- Olivia: Completed ML
  (9, 4, 1, '2026-01-22 11:00:00'),
  
  -- Mateo: Enrolled in Kubernetes
  (10, 6, 0, '2026-01-24 15:00:00');

-- Trainer-course assignments
INSERT INTO trainer_course (users_id, course_id) VALUES 
  (1, 1), (4, 2), (2, 3), (2, 4), (3, 5), (3, 6), (5, 7), (6, 8);

-- Module Progress Tracking
INSERT INTO module_progress (trainee_id, module_id, completed, completed_at) VALUES
  -- Sarah (user 1): Completed all Spring Boot modules
  (1, 1, TRUE, '2026-01-18 09:00:00'),
  (1, 2, TRUE, '2026-01-19 10:30:00'),
  (1, 3, TRUE, '2026-01-20 10:00:00'),
  (1, 4, TRUE, '2026-01-20 11:00:00'),

  -- Raj (user 2): Partial Python
  (2, 5, TRUE, '2026-01-23 15:00:00'),  -- Pandas Basics
  (2, 6, FALSE, NULL),                   -- Data Cleaning

  -- Emma (user 3): Completed Python, partial Spring Boot
  (3, 5, TRUE, '2026-01-21 11:00:00'),
  (3, 6, TRUE, '2026-01-21 14:00:00'),
  (3, 7, TRUE, '2026-01-22 13:45:00'),
  (3, 1, TRUE, '2026-01-24 11:30:00'),  -- Intro to Spring Boot
  (3, 2, FALSE, NULL),                   -- REST Controllers

  -- Fatima (user 5): Completed AWS
  (5, 14, TRUE, '2026-01-21 10:00:00'),
  (5, 15, TRUE, '2026-01-22 11:00:00'),
  (5, 16, TRUE, '2026-01-23 15:30:00'),

  -- Yuki (user 7): Completed Flutter
  (7, 17, TRUE, '2026-01-19 09:00:00'),
  (7, 18, TRUE, '2026-01-20 10:00:00'),
  (7, 19, TRUE, '2026-01-21 11:30:00'),

  -- Olivia (user 9): Completed ML
  (9, 8, TRUE, '2026-01-20 09:00:00'),
  (9, 9, TRUE, '2026-01-21 10:00:00'),
  (9, 10, TRUE, '2026-01-22 10:45:00');