-- Get Sarah's completed courses
SELECT * FROM trainee_course 
WHERE users_id = 1 AND complete_status = 1;

-- Get Raj's module progress for Python course
SELECT m.title, mp.completed, mp.completed_at
FROM module m
JOIN module_progress mp ON m.id = mp.module_id
WHERE mp.trainee_id = 2 AND m.course_id = 2;

-- Calculate course completion %
-- For Emma's Spring Boot course (course_id=1, trainee_id=3)
SELECT 
  COUNT(CASE WHEN mp.completed = TRUE THEN 1 END) * 100.0 / COUNT(*) AS completion_percentage
FROM module m
LEFT JOIN module_progress mp ON m.id = mp.module_id AND mp.trainee_id = 3
WHERE m.course_id = 1;