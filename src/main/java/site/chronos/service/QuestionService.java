package site.chronos.service;

import site.chronos.entity.Question;
import site.chronos.utils.Result;

public interface QuestionService {
	
	public Result selectById(String id);
	
	public Result addQuestion(Question question);
	
	public Result sortQuestion(String id);

}
