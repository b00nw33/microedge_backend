-- src/main/resources/data.sql

USE microedge;

-- Categories
INSERT INTO category (name) VALUES 
  ('Web Development'),
  ('Data Science'),
  ('Cloud Computing'),
  ('Mobile Development'),
  ('DevOps');

-- Trainers (6 total)
INSERT INTO users (role, first_name, last_name, email, password) VALUES 
  ('TRAINER', 'Alex', 'Johnson', 'alex.johnson@example.com', 'trainerPass123'),
  ('TRAINER', 'Mei Lin', 'Tan', 'meilin.tan@example.com', 'trainerPass456'),
  ('TRAINER', 'David', 'Chen', 'david.chen@example.com', 'trainerPass789'),
  ('TRAINER', 'Aisha', 'Rahman', 'aisha.rahman@example.com', 'trainerPass101'),
  ('TRAINER', 'Carlos', 'Mendez', 'carlos.mendez@example.com', 'trainerPass202'),
  ('TRAINER', 'Sophie', 'Dubois', 'sophie.dubois@example.com', 'trainerPass303');

-- Trainees (10 total)
INSERT INTO users (role, first_name, last_name, email, password) VALUES 
  ('TRAINEE', 'Sarah', 'Williams', 'sarah.williams@example.com', 'traineePass123'),
  ('TRAINEE', 'Raj', 'Patel', 'raj.patel@example.com', 'traineePass456'),
  ('TRAINEE', 'Emma', 'Liu', 'emma.liu@example.com', 'traineePass789'),
  ('TRAINEE', 'James', 'Wilson', 'james.wilson@example.com', 'traineePass101'),
  ('TRAINEE', 'Fatima', 'Al-Mansoori', 'fatima.almansoori@example.com', 'traineePass202'),
  ('TRAINEE', 'Liam', 'O’Connor', 'liam.oconnor@example.com', 'traineePass303'),
  ('TRAINEE', 'Yuki', 'Sato', 'yuki.sato@example.com', 'traineePass404'),
  ('TRAINEE', 'Noah', 'Kim', 'noah.kim@example.com', 'traineePass505'),
  ('TRAINEE', 'Olivia', 'Brown', 'olivia.brown@example.com', 'traineePass606'),
  ('TRAINEE', 'Mateo', 'Garcia', 'mateo.garcia@example.com', 'traineePass707');

-- Courses (8 total)
INSERT INTO course (trainer_id, category_id, title, description, duration, level) VALUES 
  -- Web Dev
  (1, 1, 'Spring Boot Fundamentals', 'Learn Spring Boot from scratch', 120, 'BEGINNER'),
  (4, 1, 'Advanced React Patterns', 'Master hooks, context, and performance', 150, 'ADVANCED'),
  
  -- Data Science
  (2, 2, 'Python for Data Analysis', 'Pandas, NumPy, and Matplotlib', 180, 'INTERMEDIATE'),
  (2, 2, 'Machine Learning Basics', 'Intro to scikit-learn and model evaluation', 240, 'BEGINNER'),
  
  -- Cloud
  (3, 3, 'AWS Cloud Practitioner', 'Get certified in AWS fundamentals', 200, 'BEGINNER'),
  (3, 3, 'Kubernetes for Developers', 'Deploy and scale containerized apps', 180, 'ADVANCED'),
  
  -- Mobile
  (5, 4, 'Flutter & Firebase', 'Build cross-platform mobile apps', 220, 'INTERMEDIATE'),
  
  -- DevOps
  (6, 5, 'CI/CD with GitHub Actions', 'Automate your deployment pipeline', 90, 'INTERMEDIATE');

-- Modules (with order_index and unique titles per course)
INSERT INTO module (course_id, title, content_text, video_url, order_index) VALUES 
  -- Course 1: Spring Boot
  (1, 'Introduction', 'Welcome to Spring Boot!', 'https://example.com/spring-intro.mp4', 1),
  (1, 'REST Controllers', 'Build your first API endpoint', 'https://example.com/spring-rest.mp4', 2),
  (1, 'JPA & MySQL', 'Connect to a database', 'https://example.com/spring-jpa.mp4', 3),
  (1, 'Security', 'Add JWT authentication', 'https://example.com/spring-security.mp4', 4),

  -- Course 2: Advanced React
  (2, 'Custom Hooks', 'Reusable logic with hooks', 'https://example.com/react-hooks.mp4', 1),
  (2, 'Performance Optimization', 'Memo, useCallback, lazy loading', 'https://example.com/react-perf.mp4', 2),
  (2, 'Testing', 'Jest and React Testing Library', 'https://example.com/react-testing.mp4', 3),

  -- Course 3: Python Data Analysis
  (3, 'Pandas Basics', 'DataFrames and Series', 'https://example.com/pandas-basics.mp4', 1),
  (3, 'Data Cleaning', 'Handle missing values and outliers', 'https://example.com/pandas-clean.mp4', 2),
  (3, 'Data Visualization', 'Plotting with Matplotlib', 'https://example.com/matplotlib.mp4', 3),

  -- Course 4: Machine Learning
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