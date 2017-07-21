package site.chronos.service;

import site.chronos.constant.Result;
import site.chronos.entity.Question;
import site.chronos.entity.QuestionRecording;
import site.chronos.entity.page.QuestionPage;

public interface QuestionService {
	
	/**
	 * 根据ID查询问题
	 * @param id
	 * @return
	 */
	public Result selectById(String id);
	
	/**
	 * 添加问题
	 * @param question
	 * @return
	 */
	public Result addQuestion(Question question);
	
	
	/**
	 * 置顶
	 * @param id
	 * @return
	 */
	public Result sortQuestion(String id);
	
	/**
	 * 查询所有有效的Qeation
	 * @param id
	 * @return
	 */
	public Result selectQuestionAll();

	
	/**
	 * 查询用户未审核的问题
	 * @param id
	 * @return
	 */
	public Result selectQuestionNotReview(QuestionPage questionPage);
	
	/**
	 * 更新问题
	 * @param question
	 * @return
	 */
	public Result updateQuestion(Question question);
	
	/**
	 * 支持问题
	 * @param question
	 * @return
	 */
	public Result supportQuestion(QuestionRecording questionRecording);
	
	/**
	 * 反对问题
	 * @param question
	 * @return
	 */
	public Result oppositionQuestion(QuestionRecording questionRecording);
	
}
