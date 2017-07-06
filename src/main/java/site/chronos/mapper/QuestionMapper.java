package site.chronos.mapper;

import java.util.List;

import site.chronos.entity.Question;
import site.chronos.entity.page.QuestionPage;

public interface QuestionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);
    
    List<Question> selectQuestionAll(QuestionPage questionPage);
}