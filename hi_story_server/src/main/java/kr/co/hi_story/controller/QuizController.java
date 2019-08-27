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

import static kr.co.hi_story.dto.MessageDTO.FAIL_DEFAULT_RES;

@Controller
@RequestMapping
public class QuizController {

	@Inject
	private QuizService quizService;

	// 퀴즈 생성
	@PostMapping("createQ")
	public ResponseEntity createQ(@RequestBody QuizDTO dto) {
		try {
			return new ResponseEntity<>(quizService.createQ(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 퀴즈 삭제
	@DeleteMapping("deleteQ")
	public ResponseEntity deleteQ(@RequestBody QuizDTO dto) {
		try {
			return new ResponseEntity<>(quizService.deleteQ(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 퀴즈 달성률
	@GetMapping("successRate")
	public ResponseEntity successRate(@RequestBody QuizDTO dto) {
		try {
			return new ResponseEntity<>(quizService.successRate(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 퀴즈 수정
	@PutMapping("editingQuiz")
	public ResponseEntity editingQuiz(@RequestBody QuizDTO dto) {
		try {
			return new ResponseEntity<>(quizService.editingQuiz(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 퀴즈 풀기 전
	@PostMapping("quizQuestion")
	public ResponseEntity quizQuestion(@RequestBody QuizDTO dto) {
		try {
			return new ResponseEntity<>(quizService.quizQuestion(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 퀴즈 풀고 난 후
	@PostMapping("takeQuiz")
	public ResponseEntity takeQuiz(@RequestBody ParticipationDTO pdto) {
		try {
			return new ResponseEntity<>(quizService.takeQuiz(pdto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 전체 도시리스트
	@GetMapping("rnameList")
	public ResponseEntity rnameList(@RequestParam("cityname") String cityname) {
		try {
			return new ResponseEntity<>(quizService.rnameList(cityname), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 전체 지도 도시 클릭 세부 유적지 리스트
	@GetMapping("cityList")
	public ResponseEntity cityList() {
		try {
			return new ResponseEntity<>(quizService.cityList(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}