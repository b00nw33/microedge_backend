// src/main/java/com/microedge/services/EnrollmentService.java
package com.microedge.services;

import com.microedge.dto.CreateEnrollmentDto;
import com.microedge.dto.EnrollmentDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.models.TraineeCourse;
import com.microedge.models.User;
import com.microedge.models.Course;
import com.microedge.repositories.TraineeCourseRepository;
import com.microedge.repositories.UserRepository;
import com.microedge.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnrollmentService {

    @Autowired
    private TraineeCourseRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<EnrollmentDto> findEnrollmentsByTrainee(Integer traineeId) {

        // Obtain the user's identity from Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();

        // Fetch the managed user
        User existingUser = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return enrollmentRepository.findByTraineeId(existingUser.getId()).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<EnrollmentDto> findEnrollmentsByCourse(Integer courseId) {

        // Obtain the user's identity from Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();

        // Fetch the managed user
        User existingUser = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return enrollmentRepository.findByCourseId(courseId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EnrollmentDto createEnrollment(Integer id) {

        // Obtain the user's identity from Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();

        // Fetch the managed user
        User existingUser = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Validate trainee exists and is TRAINEE
        // User trainee = userRepository.findById(dto.getTraineeId())
        // .orElseThrow(() -> new ResourceNotFoundException("Trainee not found: " +
        // dto.getTraineeId()));
        if (existingUser.getRole() != User.Role.TRAINEE) {
            throw new IllegalArgumentException("User is not a trainee");
        }

        // Validate course exists
        validateCourseExists(id);

        // Check for duplicate enrollment
        if (enrollmentRepository.existsByTraineeIdAndCourseId(existingUser.getId(), id)) {
            throw new IllegalArgumentException("Trainee already enrolled in this course");
        }

        TraineeCourse enrollment = new TraineeCourse();
        enrollment.setTrainee(existingUser);

        // ✅ CORRECT WAY: Create course reference with ID only
        Course courseRef = new Course();
        courseRef.setId(id);
        enrollment.setCourse(courseRef);

        enrollment.setEnrollDate(LocalDateTime.now());
        enrollment.setCompleteDate(LocalDateTime.now());
        enrollment.setCompleteStatus(false);

        TraineeCourse saved = enrollmentRepository.save(enrollment);
        return toDto(saved);
    }

    public EnrollmentDto markAsCompleted(Integer enrollmentId) {
        TraineeCourse enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found: " + enrollmentId));

        enrollment.setCompleteStatus(true);
        enrollment.setCompleteDate(LocalDateTime.now());
        TraineeCourse updated = enrollmentRepository.save(enrollment);
        return toDto(updated);
    }

    public void deleteEnrollment(Integer id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment not found: " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    private EnrollmentDto toDto(TraineeCourse enrollment) {
        EnrollmentDto dto = new EnrollmentDto();
        dto.setId(enrollment.getId());
        dto.setTraineeId(enrollment.getTrainee().getId());
        dto.setTraineeName(enrollment.getTrainee().getFirstName() + " " + enrollment.getTrainee().getLastName());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseTitle(enrollment.getCourse().getTitle());
        dto.setEnrollDate(enrollment.getEnrollDate());
        dto.setCompleteDate(enrollment.getCompleteDate());
        dto.setCompleteStatus(enrollment.getCompleteStatus());
        return dto;
    }

    // private void validateUserExists(Integer userId) {
    // if (!userRepository.existsById(userId)) {
    // throw new ResourceNotFoundException("User not found: " + userId);
    // }
    // }

    private void validateCourseExists(Integer courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found: " + courseId);
        }
    }
}