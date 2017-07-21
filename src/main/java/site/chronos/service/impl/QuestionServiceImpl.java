package site.chronos.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import site.chronos.constant.CommonConstants;
import site.chronos.constant.CommonConstants.ErrorCode;
import site.chronos.constant.Result;
import site.chronos.entity.Question;
import site.chronos.entity.QuestionRecording;
import site.chronos.entity.User;
import site.chronos.entity.page.QuestionPage;
import site.chronos.exception.BusinessException;
import site.chronos.mapper.QuestionMapper;
import site.chronos.service.QuestionService;
import site.chronos.service.UserService;
import site.chronos.utils.Utils;
import site.chronos.mapper.QuestionRecordingMapper;

@Service
public class QuestionServiceImpl implements QuestionService {

	
	private Logger LOGGER=LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private UserService userService;
	@Autowired
	QuestionRecordingMapper questionRecordingMapper;
	
	
	/**
	 * 根据ID查询问题
	 */
	@Override
	public Result selectById(String id) {
		LOGGER.info("查询question入参：{}",id);
		if(StringUtils.isEmpty(id)){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_PARAMTER);//ID为空
		}
		Question selectByPrimaryKey = questionMapper.selectByPrimaryKey(id);
		return new Result(selectByPrimaryKey);
	}
	
	/**
	 * 添加一条问题
	 */
	@Override
	public Result addQuestion(Question question) {
		long startTime = System.currentTimeMillis();
		LOGGER.info("添加question入参：{}",CommonConstants.GSONIGNORENULL.toJson(question));
		if(StringUtils.isEmpty(question.getUserId())){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_PARAMTER,"用户ID为空");//SORT有误
		}
		if(StringUtils.isEmpty(question.getTitle())){
			throw new BusinessException(ErrorCode.ERROR_TITLE,"标题为空");//标题为空
		}
		if(StringUtils.isEmpty(question.getQuestion())){
			throw new BusinessException(ErrorCode.ERROR_QUESTION);//内容为空
		}
		if(question.getQuestion().length() < 2){
			throw new BusinessException(ErrorCode.ERROR_QUESTION,"问题内容太短");//问题内容太短
		}
		Result selectUserById = userService.selectUserById(question.getUserId());
		if(Objects.isNull(selectUserById.getResult())){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_PARAMTER);//ID错了。
		}
		question.setId(Utils.getNewId());
		question.setCreateTime(Utils.getNewTime());
		if(question.getSort()!=null){
			if(question.getSort() > 10 || question.getSort() < 0){
				throw new BusinessException(ErrorCode.ERROR_PARAMS);//SORT有误
			}
		}
		questionMapper.insertSelective(question);
		LOGGER.info("添加完成,耗时：{}",System.currentTimeMillis()-startTime);
		return new Result(question);
	}
	
	/**
	 * 将该问题置顶
	 */
	@Override
	public Result sortQuestion(String id) {
		if(StringUtils.isEmpty(id)){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_PARAMTER);//ID有误
		}
		//检测问题是否存在
		Result selectById = this.selectById(id);
		if(Objects.isNull(selectById.getResult())){
			throw new BusinessException(ErrorCode.ERROR_QUESTION,"对应问题不存在");//ID有误
		}
		//判断问题是否已经置顶
		Question question = (Question)selectById.getResult();
		if(question.getSort()!=null && question.getSort() == 1){
			throw new BusinessException(ErrorCode.ERROR_QUESTION,"已经置顶");//ID有误
		}
		question.setId(id);
		question.setSort(1);
		int updateByPrimaryKeySelective = questionMapper.updateByPrimaryKeySelective(question); // 更新
		return new Result(updateByPrimaryKeySelective);
	}

	@Override
	public Result selectQuestionAll() {
		QuestionPage questionPage = new QuestionPage();
		List<Question> selectQuestionAll = questionMapper.selectQuestionAll(questionPage.enablePaging());
		PageInfo<Question> pageInfo = new PageInfo<>(selectQuestionAll);
		return new Result(pageInfo);
	}

	@Override
	public Result selectQuestionNotReview(QuestionPage questionPage) {
		if(StringUtils.isEmpty(questionPage.getUserId())){
			throw new BusinessException(ErrorCode.ERROR_PARAMS);//USER为空
		}
		List<Question> selectQuestionAll = questionMapper.selectQuestionAll(questionPage.enablePaging());
		return new Result(selectQuestionAll);
	}

	@Override
	public Result updateQuestion(Question question) {
		if(StringUtils.isEmpty(question.getId())){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_PARAMTER);//USER为空
		}
		question.setIsDel(null); //不允许删除
		questionMapper.updateByPrimaryKeySelective(question);
		return new Result();
	}

	/**
	 * 点赞问题
	 * @throws ParseException 
	 */
	@Override
	public Result supportQuestion(QuestionRecording questionRecording){
		if(StringUtils.isEmpty(questionRecording.getCreateBy())){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_PARAMTER);//USER为空
		}
		if(StringUtils.isEmpty(questionRecording.getQuestionId())){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_CONTANTS);//Question为空
		}
		Result selectUserById = userService.selectUserById(questionRecording.getCreateBy());
		User user = (User)selectUserById.getResult();
		if(user.getIsDel() != 0){
			LOGGER.info("账号状态异常，不允许该操作：{}",questionRecording.getCreateBy());
			throw new BusinessException(ErrorCode.ERROR_USER_STATUS);//账号状态异常，不允许该操作
		}
		//查询今天又没对该问题点赞，如果有，就提示当天只能给同一个问题点赞
		QuestionRecording selectByUserId = questionRecordingMapper.selectByUserId(questionRecording.getQuestionId(), questionRecording.getCreateBy());
		if(selectByUserId!=null){
			try {
				Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(selectByUserId.getCreateTime());
				Long time = (new Date().getTime() - parse.getTime())/1000;
				if(time > (60 * 60 * 24)){ //对该问题最后一次点赞大于一天 ，不允许继续对该问题点赞
					LOGGER.info("当用户已点赞，不允许再点赞:{},question:{}",questionRecording.getCreateBy(),questionRecording.getQuestionId());
					throw new BusinessException(ErrorCode.ERROR_SUPPORT_ERROR);//不允许点赞了。
				}
			} catch (ParseException e) {
				LOGGER.info("时间转换异常，不允许再点赞:{},question:{}",questionRecording.getCreateBy(),questionRecording.getQuestionId());
				throw new BusinessException(ErrorCode.ERROR_SUPPORT_ERROR);//不允许点赞了。
			}
		}
		questionRecording.setCreateTime(Utils.getNewTime());
		questionRecording.setQuestionFunc("support");
		questionRecording.setQuestionFuncAbout("1");
		questionRecordingMapper.insertSelective(questionRecording); //存入记录表
		questionMapper.supportQuestion(questionRecording.getQuestionId(),Integer.parseInt(questionRecording.getQuestionFuncAbout()));//对问题相关信息进行更新
		return new Result();
	}

	@Override
	public Result oppositionQuestion(QuestionRecording questionRecording) {
		if(StringUtils.isEmpty(questionRecording.getCreateBy())){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_PARAMTER);//USER为空
		}
		if(StringUtils.isEmpty(questionRecording.getQuestionId())){
			throw new BusinessException(ErrorCode.ERROR_ILLEGAL_CONTANTS);//Question为空
		}
		
		Result selectUserById = userService.selectUserById(questionRecording.getCreateBy());
		User user = (User)selectUserById.getResult();
		if(user.getIsDel() != 0){
			LOGGER.info("账号状态异常，不允许该操作：{}",questionRecording.getCreateBy());
			throw new BusinessException(ErrorCode.ERROR_USER_STATUS);//账号状态异常，不允许该操作
		}
		//查询今天又没对该问题点赞，如果有，就提示当天只能给同一个问题点赞
		QuestionRecording selectByUserId = questionRecordingMapper.selectByUserId(questionRecording.getQuestionId(), questionRecording.getCreateBy());
		if(selectByUserId!=null){
			try {
				Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(selectByUserId.getCreateTime());
				Long time = (new Date().getTime() - parse.getTime())/1000;
				if(time > (60 * 60 * 24)){ //对该问题最后一次点赞大于一天 ，不允许继续对该问题点赞
					LOGGER.info("当用户已点赞，不允许再点赞:{},question:{}",questionRecording.getCreateBy(),questionRecording.getQuestionId());
					throw new BusinessException(ErrorCode.ERROR_SUPPORT_ERROR);//不允许反对了。
				}
			} catch (ParseException e) {
				LOGGER.info("时间转换异常，不允许再点赞:{},question:{}",questionRecording.getCreateBy(),questionRecording.getQuestionId());
				throw new BusinessException(ErrorCode.ERROR_SUPPORT_ERROR);//不允许反对了。
			}
		}
		questionRecording.setCreateTime(Utils.getNewTime());
		questionRecording.setQuestionFunc("opposition");
		questionRecording.setQuestionFuncAbout("1");
		questionRecordingMapper.insertSelective(questionRecording); //存入记录表
		questionMapper.oppositionQuestion(questionRecording.getQuestionId(),Integer.parseInt(questionRecording.getQuestionFuncAbout()));//对问题相关信息进行更新
		return new Result();
	}
}
