package kr.co.hi_story.controller;

@Controller
@RequestMapping
public class QuizRoomController {

	@Inject
	private QuizRoomService quizRoomService;
	
	@GetMapping("searchMem")
	public ResponseEntity searchMem(@RequestParam("codenum") String codenum) {
		try {
			return new ResponseEntity<>(memberService.searchMem(codenum), HttpStatus.OK);	
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("enterRoom")
	public ResponseEntity enterRoom(@RequestBody QuizRoomDTO dto, HttpServletResponse response) {
		try {
			return new ResponseEntity<>(quizRoomService.enterRoom(dto, response), HttpStatus.OK);	
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
