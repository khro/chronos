package site.chronos.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
    
    /**
     * 支持问题
     * @param id
     * @param support 支持数量，增量
     * @return
     */
    int supportQuestion(@Param("id")String id,@Param("support")Integer support);
    
   /**
    * 反对问题
    * @param id 问题ID
    * @param opposition 反对数量，增量
    * @return
    */
    int oppositionQuestion(@Param("id")String id,@Param("opposition")Integer opposition);
}