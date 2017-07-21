package site.chronos.mapper;

import site.chronos.entity.QuestionRecording;

public interface QuestionRecordingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionRecording record);

    int insertSelective(QuestionRecording record);

    QuestionRecording selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionRecording record);

    int updateByPrimaryKey(QuestionRecording record);
    
    QuestionRecording selectByUserId(String questionId,String createBy);
}