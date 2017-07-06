package site.chronos.service;

import site.chronos.entity.Question;
import site.chronos.utils.Result;

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

}
