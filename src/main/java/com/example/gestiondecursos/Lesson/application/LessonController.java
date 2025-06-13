package com.example.gestiondecursos.Lesson.application;

import com.example.gestiondecursos.Lesson.domain.Lesson;
import com.example.gestiondecursos.Lesson.domain.LessonService;
import com.example.gestiondecursos.Lesson.dto.LessonRequestDTO;
import com.example.gestiondecursos.Lesson.dto.LessonResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/courseId/{id}")
    public ResponseEntity<Void> createdLesson(@PathVariable Long id,@RequestBody @Valid LessonRequestDTO lesson){
        lessonService.createLesson(id, lesson);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/courseId/{courseId}/title/{title}")
    public ResponseEntity<LessonResponseDTO> getLessonByTitle(@PathVariable Long courseId,@PathVariable String title){
        LessonResponseDTO lesson = lessonService.getLessonByTitle(courseId, title);
        return ResponseEntity.status(HttpStatus.OK).body(lesson);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/courseId/{courseId}/getByWeek/{week}")
    public ResponseEntity<LessonResponseDTO> getLessonByWeek(@PathVariable Long courseId,@PathVariable Integer week){
        LessonResponseDTO lesson = lessonService.getLessonByWeek(courseId, week);
        return ResponseEntity.status(HttpStatus.OK).body(lesson);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id){
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PatchMapping("/lessonId/{id}")
    public ResponseEntity<Void> updateLesson(@PathVariable Long id, @RequestBody @Valid LessonRequestDTO lesson){
        lessonService.updateLesson(id, lesson);
        return ResponseEntity.noContent().build();
    }
}
