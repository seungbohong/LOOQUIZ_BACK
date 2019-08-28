package kr.co.hi_story.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;
import kr.co.hi_story.dto.QuizRoomDTO;
import kr.co.hi_story.service.QuizRoomService;
import kr.co.hi_story.service.QuizService;

import static kr.co.hi_story.dto.MessageDTO.FAIL_DEFAULT_RES;

@Controller
@RequestMapping
public class QuizRoomController {

	@Inject
	private QuizRoomService quizroomService;

	// ���� ����
	@PostMapping("makeRoom")
	public ResponseEntity makeRoom(@RequestBody QuizRoomDTO dto) {
		try {
			return new ResponseEntity<>(quizroomService.makeRoom(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}