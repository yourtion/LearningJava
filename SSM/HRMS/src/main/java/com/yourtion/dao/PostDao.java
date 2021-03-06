package com.yourtion.dao;

import com.yourtion.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * PostDao
 * Created by Yourtion on 26/06/2017.
 */
@Repository
public interface PostDao {

    /**
     * 根据条件查询公告
     *
     * @return List
     */
    public List<Post> findPosts(Map<String, Object> map);

    /**
     * 根据条件查询公告数量
     *
     * @param map 条件
     * @return Integer
     */
    public Integer getCount(Map<String, Object> map);

    /**
     * 添加公告
     *
     * @param post 公告
     * @return Integer
     */
    public Integer addPost(Post post);

    /**
     * 修改公告
     *
     * @param post 公告
     * @return Integer
     */
    public Integer updatePost(Post post);

    /**
     * 删除公告
     *
     * @param id 公告ID
     * @return Integer
     */
    public Integer deletePost(Integer id);

    /**
     * 根据 ID 查询公告信息
     *
     * @param id 公告ID
     * @return Post
     */
    public Post getPostById(Integer id);
}
