package site.chronos.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import site.chronos.entity.Question;
import site.chronos.service.QuestionService;
import site.chronos.utils.Result;


@Controller
@RequestMapping("/q")
@ResponseBody
@Api(value = "问题模块", tags = "QuestionController", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,authorizations={})
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@ApiOperation(value="/",notes="查询Question")
	@GetMapping("/{id}")
	public ResponseEntity<Object> TestAction(@PathVariable("id") String id) throws Exception {
		Result selectById = questionService.selectById(id);
	      return ResponseEntity.ok(selectById);
	  }
	
	@ApiOperation(value="addQuestion",notes="添加Question")
	@PutMapping("/addQuestion")
	public ResponseEntity<Object> regiestAction(@RequestBody Question question,HttpSession session) throws Exception {
			Result userRegiest = questionService.addQuestion(question);
	      return ResponseEntity.ok(userRegiest);
	  }
	
	@ApiOperation(value="sortQuestion",notes="置顶Question")
	@PutMapping("/{id}")
	public ResponseEntity<Object> sortQuestion(@PathVariable("id") String id,HttpSession session) throws Exception {
			Result userRegiest = questionService.sortQuestion(id);
	      return ResponseEntity.ok(userRegiest);
	  }

	@ApiOperation(value="selectQuestion",notes="置顶Question")
	@PutMapping("/selectQuestion")
	public ResponseEntity<Object> selectQuestion() throws Exception {
			Result questionAll = questionService.selectQuestionAll();
	      return ResponseEntity.ok(questionAll);
	  }
}
