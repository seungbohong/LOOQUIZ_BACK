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
import kr.co.hi_story.service.QuizService;
import kr.co.hi_story.service.UtilService;

import static kr.co.hi_story.dto.MessageDTO.FAIL_DEFAULT_RES;

@Controller
@RequestMapping
public class UtilController {

	@Inject
	private UtilService utilService;

	// 제휴 리스트 조회
	@GetMapping("corpList")
	public ResponseEntity createQ(@RequestParam int bid) {
		try {
			return new ResponseEntity<>(utilService.corpList(bid), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 뱃지 도감
	@GetMapping("badge")
	public ResponseEntity badge() {
		try {
			return new ResponseEntity<>(utilService.badge(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
}