package site.chronos.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.github.pagehelper.PageInfo;

import site.chronos.constant.CommonConstants;
import site.chronos.constant.Result;
import site.chronos.constant.CommonConstants.ErrorCode;
import site.chronos.entity.Question;
import site.chronos.entity.Review;
import site.chronos.entity.page.ReviewPage;
import site.chronos.exception.BusinessException;
import site.chronos.mapper.ReviewMapper;
import site.chronos.service.QuestionService;
import site.chronos.service.ReviewService;
import site.chronos.service.UserService;


@Service
public class ReviewServiceImpl implements ReviewService {

	private Logger LOGGER=LoggerFactory.getLogger(ReviewServiceImpl.class);
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;

	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public Result addReview(Question question) {
		if(StringUtils.isEmpty(question.getUserId())){
			throw new BusinessException(ErrorCode.ERROR_PARAMS,"用户为空");//ID有误
		}
		
		Result selectQuestionNotReview = questionService.selectQuestionNotReview(question.getUserId());
		@SuppressWarnings("unchecked")
		List<Question> result = (List<Question>)selectQuestionNotReview.getResult();
		if(ObjectUtils.isEmpty(result)){
			throw new BusinessException(ErrorCode.ERROR_ADD_REVIEW);//还有未审核的问题不允许提交新的
		}
		Review review = new Review();
		Result addQuestion = questionService.addQuestion(question);
		Question reviewQuestion = (Question)addQuestion.getResult();
		review.setQuestionId(reviewQuestion.getId());
		review.setIsDel(0);
		review.setStatus(0);
		review.setUserId(question.getUserId());
		reviewMapper.insertSelective(review);
		return new Result();
	}

	@Override
	public Result selectReview(Integer status) {
		ReviewPage reviewPage = new ReviewPage();
		reviewPage.setStatus(status);
		List<Review> selectReview = reviewMapper.selectReview(reviewPage);
		PageInfo<Review> pageInfo = new PageInfo<>(selectReview);
		return new Result(pageInfo);
	}
	
	/**
	 * 查询已删除审核列表
	 */
	@Override
	public Result selectReviewDel() {
		long startTime = System.currentTimeMillis();
		LOGGER.info("查询已删除Review");
		ReviewPage reviewPage = new  ReviewPage();
		reviewPage.setIsDel(1);
		List<Review> selectReview = reviewMapper.selectReview(reviewPage);
		LOGGER.info("已删除审核查询完成，返回：{}，耗时：{}",CommonConstants.GSONIGNORENULL.toJson(selectReview),System.currentTimeMillis()-startTime);
		return new Result(selectReview);
	}
	@Override
	public Result reviewOver(Integer reviewId ,Integer status) {
		if(Objects.isNull(status)){
			throw new BusinessException(ErrorCode.ERROR_PARAMS,"状态为空");//ID有误
		}
		if(Objects.isNull(status)){
			throw new BusinessException(ErrorCode.ERROR_PARAMS,"审核对象为空");//ID有误
		}
		Review selectByPrimaryKey = reviewMapper.selectByPrimaryKey(reviewId);
		if(Objects.isNull(selectByPrimaryKey)){
			throw new BusinessException(ErrorCode.ERROR_REVIEW_NULL);//ID有误
		}
		if(status == 0){
			throw new BusinessException(ErrorCode.ERROR_PARAMS,"状态值不可改变");//ID有误
		}
		if(status == selectByPrimaryKey.getStatus()){
			throw new BusinessException(ErrorCode.ERROR_PARAMS,"状态不需要改变");//ID有误
		}
		if(status == -1){ //审核不通过
			selectByPrimaryKey.setStatus(-1);
			reviewMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
		}
		if(status == 10){ //审核通过
			selectByPrimaryKey.setStatus(10);
			reviewMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
			userService.userChange(selectByPrimaryKey.getUserId(), status); //用户成为提问者
		}
		return new Result();
	}

}
