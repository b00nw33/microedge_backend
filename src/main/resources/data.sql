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

  -- Cloud
  (3, 3, 'AWS Cloud Practitioner', 'Get certified in AWS fundamentals', 200, 'BEGINNER', 'uploads/comingsoon.png'),
  (3, 3, 'Kubernetes for Developers', 'Deploy and scale containerized apps', 180, 'ADVANCED', 'uploads/comingsoon.png'),

  -- Mobile
  (5, 4, 'Flutter & Firebase', 'Build cross-platform mobile apps', 220, 'INTERMEDIATE', 'uploads/comingsoon.png'),

  -- DevOps
  (6, 5, 'CI/CD with GitHub Actions', 'Automate your deployment pipeline', 90, 'INTERMEDIATE', 'uploads/comingsoon.png');

-- Modules (with order_index and unique titles per course)
INSERT INTO module (course_id, title, content_text, video_url, order_index) VALUES 
  -- Course 1: Construction Safety Basics
  (1, 'Safety & PPE', 'Focuses on identifying "The Big Four" site hazards and mastering the correct use of protective gear to ensure regulatory compliance and worker safety.', 'uploads/construction_site_basics/video_00.mp4', 1),
  (1, 'Communication', 'Teaches the essential "language" of the site, including interpreting safety signage, using universal hand signals for machinery, and maintaining professional radio etiquette.', 'uploads/construction_site_basics/video_00.mp4', 2),
  (1, 'Tools & Housekeeping', 'Covers the safe operation of hand and power tools alongside "clean as you go" practices to prevent workplace accidents and organize materials efficiently.', 'uploads/construction_site_basics/video_00.mp4', 3),
  (1, 'Blueprints & Documentation', 'Provides a foundation in reading technical plans and measurements while emphasizing the importance of accurate daily reporting for project tracking.', 'uploads/construction_site_basics/video_00.mp4', 4),

  -- Course 2: Construction Workflow & Risk Awareness (TODO)
  (2, 'Custom Hooks', 'Reusable logic with hooks', 'https://example.com/react-hooks.mp4', 1),
  (2, 'Performance Optimization', 'Memo, useCallback, lazy loading', 'https://example.com/react-perf.mp4', 2),
  (2, 'Testing', 'Jest and React Testing Library', 'https://example.com/react-testing.mp4', 3),

  -- Course 3: Healthcare Environment Essentials (TODO)
  (3, 'Pandas Basics', 'DataFrames and Series', 'https://example.com/pandas-basics.mp4', 1),
  (3, 'Data Cleaning', 'Handle missing values and outliers', 'https://example.com/pandas-clean.mp4', 2),
  (3, 'Data Visualization', 'Plotting with Matplotlib', 'https://example.com/matplotlib.mp4', 3),

  -- Course 4: Patient Care & Clinical Safety (TODO)
  (4, 'What is ML?', 'Supervised vs unsupervised', 'https://example.com/ml-intro.mp4', 1),
  (4, 'Train Your First Model', 'Linear regression with scikit-learn', 'https://example.com/ml-model.mp4', 2),
  (4, 'Model Evaluation', 'Accuracy, precision, recall', 'https://example.com/ml-eval.mp4', 3),

  -- Course 5: AWS
  (5, 'What is the Cloud?', 'Cloud vs on-premise', 'https://example.com/aws-cloud.mp4', 1),
  (5, 'IAM & Security', 'Users, roles, and policies', 'https://example.com/aws-iam.mp4', 2),
  (5, 'S3 & EC2', 'Storage and compute basics', 'https://example.com/aws-s3-ec2.mp4', 3),

  -- Course 6: Kubernetes
  (6, 'Pods & Deployments', 'Run containers at scale', 'https://example.com/k8s-pods.mp4', 1),
  (6, 'Services & Ingress', 'Expose your app securely', 'https://example.com/k8s-networking.mp4', 2),
  (6, 'Helm Charts', 'Package your applications', 'https://example.com/k8s-helm.mp4', 3),

  -- Course 7: Flutter
  (7, 'Getting Started', 'Set up Flutter and Firebase', 'https://example.com/flutter-setup.mp4', 1),
  (7, 'Firestore Integration', 'Read and write data', 'https://example.com/flutter-firestore.mp4', 2),
  (7, 'Authentication', 'Sign in with Google', 'https://example.com/flutter-auth.mp4', 3),

  -- Course 8: CI/CD
  (8, 'GitHub Actions Basics', 'Workflows and runners', 'https://example.com/gh-actions.mp4', 1),
  (8, 'Deploy to AWS', 'Auto-deploy on push', 'https://example.com/gh-aws-deploy.mp4', 2),
  (8, 'Testing Pipeline', 'Run tests on every PR', 'https://example.com/gh-tests.mp4', 3);

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