package com.yourtion.dao;

import com.yourtion.domain.Position;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * PositionDao
 * Created by Yourtion on 26/06/2017.
 */
@Repository
public interface PositionDao {

    /**
     * 根据条件查询职位
     *
     * @param map 条件
     * @return List
     */
    public List<Position> findPositions(Map<String, Object> map);

    /**
     * 根据条件查询职位数量
     *
     * @param map 条件
     * @return Integer
     */
    public Integer getCount(Map<String, Object> map);

    /**
     * 添加职位
     *
     * @param position 职位
     * @return Integer
     */
    public Integer addPosition(Position position);

    /**
     * 修改职位
     *
     * @param position 职位
     * @return Integer
     */
    public Integer updatePosition(Position position);

    /**
     * 删除职位
     *
     * @param id 职位ID
     * @return Integer
     */
    public Integer deletePosition(Integer id);
}
