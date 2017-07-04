package site.chronos.mapper;

import site.chronos.entity.UserRealname;

public interface UserRealnameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRealname record);

    int insertSelective(UserRealname record);

    UserRealname selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRealname record);

    int updateByPrimaryKey(UserRealname record);
}