package site.chronos.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import site.chronos.constant.CacheKeys;
import site.chronos.constant.CommonConstants;
import site.chronos.constant.Result;
import site.chronos.controller.core.BaseController;
import site.chronos.entity.Question;
import site.chronos.service.QuestionService;


@Controller
@RequestMapping("/q")
@ResponseBody
@Api(value = "问题模块", tags = "QuestionController", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,authorizations={})
public class QuestionController extends BaseController{
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@ApiOperation(value="/",notes="查询Question")
	@GetMapping("/{id}")
	public ResponseEntity<Object> question(@PathVariable("id") String id) throws Exception {
		Result selectById = questionService.selectById(id);
	      return ResponseEntity.ok(selectById);
	  }
	
	@ApiOperation(value="addQuestion",notes="添加Question")
	@PutMapping("/addQuestion")
	public ResponseEntity<Object> addQuestion(@RequestBody Question question,HttpSession session) throws Exception {
			String userId = getId(session);
			question.setUserId(userId);
			Result userRegiest = questionService.addQuestion(question);
	      return ResponseEntity.ok(userRegiest);
	  }
	
	@ApiOperation(value="sortQuestion",notes="置顶Question")
	@PutMapping("/{id}")
	public ResponseEntity<Object> sortQuestion(@PathVariable("id") String id,HttpSession session) throws Exception {
			Result userRegiest = questionService.sortQuestion(id);
	      return ResponseEntity.ok(userRegiest);
	  }

	@ApiOperation(value="selectQuestion",notes="查询自己所有的Question")
	@PutMapping("/selectQuestion")
	public ResponseEntity<Object> selectQuestion() throws Exception {
			String string = redisTemplate.opsForValue().get(CacheKeys.SELECT_QUESTION_KEYS);
			if(StringUtils.isEmpty(string)){
				Result questionAll = questionService.selectQuestionAll();
				redisTemplate.opsForValue().set(CacheKeys.SELECT_QUESTION_KEYS, CommonConstants.GSONIGNORENULL.toJson(questionAll), 30, TimeUnit.MINUTES); //缓存30分钟
				return ResponseEntity.ok(questionAll);
			}else{
				return ResponseEntity.ok(new Gson().fromJson(string, Result.class));
			}
	  }
	
	@ApiOperation(value="supportQuestion",notes="支持该Question")
	@PutMapping("/supportQuestion")
	public ResponseEntity<Object> supportQuestion() throws Exception {
//		RedissonClient
			Result questionAll = questionService.selectQuestionAll();
	      return ResponseEntity.ok(questionAll);
	  }
	
	
}
