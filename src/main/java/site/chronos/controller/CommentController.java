package site.chronos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import site.chronos.service.CommentService;
import site.chronos.utils.Result;

@Controller
@RequestMapping("/c")
@ResponseBody
@Api(value = "评论模块", tags = "CommentController", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {})
public class CommentController {

	@Autowired
	private CommentService commentService;

	@ApiOperation(value = "/", notes = "根据问题查询Comment")
	@GetMapping("/question/{questionId}")
	public ResponseEntity<Object> TestAction(@PathVariable("questionId") String questionId) throws Exception {
		Result selectById = commentService.selectByQuestion(questionId);
		return ResponseEntity.ok(selectById);
	}

	@ApiOperation(value = "/", notes = "根据评论查询Comment")
	@GetMapping("/{commentId}")
	public ResponseEntity<Object> regiestAction(@PathVariable("commentId") String commentId) throws Exception {
		Result selectById = commentService.selectByComment(commentId);
		return ResponseEntity.ok(selectById);
	}

}
