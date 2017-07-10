package site.chronos.mapper;

import java.util.List;

import site.chronos.entity.Review;
import site.chronos.entity.page.ReviewPage;

public interface ReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
    
    List<Review> selectReview(ReviewPage reviewPage);
}