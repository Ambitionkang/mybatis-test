package mapper;

import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kfm
 * @date 2019.02.13 10:47
 */
public interface UserMapper {

    User selectById(@Param("id") Integer id);

    List<User> selectAll();

    /**
     * 统计
     * @return
     */
    int countUser();

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insert(User user);
}
