package com.weidai.action.mapper;

import com.weidai.action.model.UserBean;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by fuck on 17/1/6.
 */
public interface UserMapper {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Select("select * from t_user where username=#{un} and password=#{pw}")
    @Results({
            @Result(id = true, property = "id", column = "id", javaType = Integer.class),
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "account", column = "account", javaType = Double.class)


    })
    public UserBean login(@Param("un") String username, @Param("pw") String password);


    /**
     * 新增用户
     * @param user
     * @return
     * @throws Exception
     */
    @Insert("insert into t_user value (null,user.username,user.password,user.anount)")
    @Options(useGeneratedKeys = true,keyProperty = "user.id")
    public int insertUser(@Param("user")UserBean user) throws Exception;

    /**
     * 修改用户
     * @param user
     * @param id
     * @return
     * @throws Exception
     */
    @Update("update t_user set username=#{u.username},password=#{u.password},account=#{u.account} where id=#{id}")
    public int updateUser(@Param("u")UserBean user,@Param("id")int id) throws Exception;


    /**
     * 删除用户
     * @param id
     * @return
     * @throws Exception
     */
    @Delete("delete from t_user where id=#{id}")
    public int deleteUser(int id) throws Exception;

    /**
     * 根据id 查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from t_user where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id",javaType = Integer.class),
            @Result(property = "username",column = "username",javaType=String.class),
            @Result(property = "password",column = "password",javaType = String.class),
            @Result(property = "account",column ="account",javaType = Double.class)
    })
    public UserBean selectUserById(int id) throws Exception;

    /**
     * 查询所有用户信息
     * @return
     * @throws Exception
     */
    @Select("select * from t_user")
    @ResultMap("userMap")
    public List<UserBean> selectAllUser() throws Exception;

    /**
     * 批量增加
     * @param user
     * @return
     * @throws Exception
     */
    public int batchInsertUser(@Param("users")List<UserBean> user) throws Exception;

    /**
     * 批量删除
     * @param list
     * @return
     * @throws Exception
     */
    public int batchDeleteUser(@Param("List")List<Integer> list) throws Exception;

    /**
     * 分页查询数据
     * @param params
     * @return
     * @throws Exception
     */
    public List<UserBean> pagerUser(Map<String,Object> params) throws Exception;

    /**
     * 分页统计数据
     * @param params
     * @return
     * @throws Exception
     */
    public int countUser(Map<String,Object> params) throws  Exception;

}
