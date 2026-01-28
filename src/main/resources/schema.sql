-- src/main/resources/schema.sql

DROP SCHEMA IF EXISTS microedge;
CREATE SCHEMA microedge;
USE microedge;

-- Users
CREATE TABLE users (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  role ENUM('TRAINER', 'TRAINEE') NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE,
  password VARCHAR(255)
);

-- Categories
CREATE TABLE category (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(45)
);

-- Courses
CREATE TABLE course (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  trainer_id INT NOT NULL,
  category_id INT NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  title VARCHAR(255) NOT NULL UNIQUE,
  description TEXT NOT NULL,
  duration INT NOT NULL,
  level ENUM('BEGINNER', 'INTERMEDIATE', 'ADVANCED') NOT NULL,
  image_url VARCHAR(255),
  FOREIGN KEY (trainer_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

-- Modules (✅ FIXED: title unique per course)
CREATE TABLE module (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  course_id INT NOT NULL,
  title VARCHAR(255) NOT NULL,
  content_text TEXT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  image_url VARCHAR(255),
  video_url VARCHAR(255),
  order_index INT DEFAULT 0,
  FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
  UNIQUE KEY uk_module_course_title (course_id, title) -- ✅ Per-course uniqueness
);

-- Trainee Enrollments
CREATE TABLE trainee_course (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  users_id INT NOT NULL,
  course_id INT NOT NULL,
  enroll_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  complete_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  complete_status TINYINT NOT NULL DEFAULT 0,
  UNIQUE(users_id, course_id),
  FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);

-- Trainer Assignments
CREATE TABLE trainer_course (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  users_id INT NOT NULL,
  course_id INT NOT NULL,
  publish_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  UNIQUE(users_id, course_id),
  FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);

-- Module Progress Tracking
CREATE TABLE module_progress (
  id INT PRIMARY KEY AUTO_INCREMENT,
  trainee_id INT NOT NULL,
  module_id INT NOT NULL,
  completed BOOLEAN DEFAULT FALSE,
  completed_at DATETIME,
  FOREIGN KEY (trainee_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (module_id) REFERENCES module(id) ON DELETE CASCADE,
  UNIQUE(trainee_id, module_id)
);