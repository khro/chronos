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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import site.chronos.constant.Result;
import site.chronos.controller.core.BaseController;
import site.chronos.entity.Question;
import site.chronos.entity.page.QuestionPage;
import site.chronos.entity.page.ReviewPage;
import site.chronos.service.QuestionService;
import site.chronos.service.ReviewService;

@Controller
@RequestMapping("/r")
@ResponseBody
@Api(value = "审核模块", tags = "ReviewController", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {})
public class ReviewController extends BaseController{

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private QuestionService questionService;
	

	@ApiOperation(value = "/question", notes = "提交问题审核")
	@PutMapping("/question")
	public ResponseEntity<Object> question(@RequestBody Question question,HttpSession session) throws Exception {
//		String userId = getId(session);
//		question.setUserId(userId);
		Result selectById = reviewService.addReview(question);
		return ResponseEntity.ok(selectById);
	}

	@ApiOperation(value = "/selectReview", notes = "查询所有待审核")
	@GetMapping("/selectReview")
	public ResponseEntity<Object> selectReview(ReviewPage reviewPage) throws Exception {
		reviewPage.setStatus(0);
		reviewPage.setIsDel(0);
		Result selectById = reviewService.selectReview(reviewPage);
		return ResponseEntity.ok(selectById);
	}
	
	@ApiOperation(value = "/user/selectReview", notes = "用户查询自己待审核问题")
	@GetMapping("/user/selectReview/{userId}")
	public ResponseEntity<Object> selectReview(@PathVariable("userId") String userId,HttpSession session) throws Exception {
//		String userId = getId(session);
		QuestionPage questionPage = new QuestionPage();
		questionPage.setUserId(userId);
		questionPage.setIsReview("0");
		Result selectById = questionService.selectQuestionNotReview(questionPage);
		return ResponseEntity.ok(selectById);
	}
	
	@ApiOperation(value = "/reviewOver", notes = "用户通过/不通过审核")
	@PutMapping("/reviewOver")
	public ResponseEntity<Object> reviewOver(@RequestParam("reviewId") Integer reviewId,@RequestParam("status") Integer status,HttpSession session) throws Exception {
		Result selectById = reviewService.reviewOver(reviewId, status);
		return ResponseEntity.ok(selectById);
	}

}
