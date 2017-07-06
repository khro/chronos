package site.chronos.mapper;

import java.util.List;

import site.chronos.entity.Comment;
import site.chronos.entity.page.CommentPage;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<Comment> selectByQuestionId(CommentPage commentPage);
    
    List<Comment> selectByCommentId(CommentPage commentPage);
}