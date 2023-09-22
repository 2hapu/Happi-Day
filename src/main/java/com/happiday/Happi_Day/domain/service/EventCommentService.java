package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.event.Event;
import com.happiday.Happi_Day.domain.entity.event.EventComment;
import com.happiday.Happi_Day.domain.entity.event.dto.comment.EventCommentCreateDto;
import com.happiday.Happi_Day.domain.entity.event.dto.comment.EventCommentResponseDto;
import com.happiday.Happi_Day.domain.entity.event.dto.comment.EventCommentUpdateDto;
import com.happiday.Happi_Day.domain.entity.user.User;
import com.happiday.Happi_Day.domain.repository.EventCommentRepository;
import com.happiday.Happi_Day.domain.repository.EventRepository;
import com.happiday.Happi_Day.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class EventCommentService {
    private final EventCommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Transactional
    public EventCommentResponseDto createComment(Long eventId, EventCommentCreateDto request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        EventComment comment = EventComment.builder()
                .user(user)
                .content(request.getContent())
                .event(event)
                .build();

        commentRepository.save(comment);

        return EventCommentResponseDto.fromEntity(comment);
    }

    public List<EventCommentResponseDto> readComments(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<EventComment> commentList = commentRepository.findAllByEvent(event);

        return commentList.stream().map(EventCommentResponseDto::fromEntity).toList();
    }

    @Transactional
    public EventCommentResponseDto updateComment(Long eventId, Long commentId, EventCommentUpdateDto request, String username) {
        log.info("서비스단 수정 테스트");

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        EventComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(!user.getUsername().equals(username)) throw new IllegalArgumentException("사용자 정보가 일치하지 않습니다.");

        comment.update(request.toEntity());
        commentRepository.save(comment);

        return EventCommentResponseDto.fromEntity(comment);
    }

    @Transactional
    public void deleteComment(Long eventId, Long commentId, String username) {
        log.info("서비스단 삭제 테스트");

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        EventComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!user.getUsername().equals(username)) throw new IllegalArgumentException("사용자 정보가 일치하지 않습니다.");

        commentRepository.delete(comment);

    }
}
