package com.zoc.furns.dao.impl;

import com.zoc.furns.dao.BasicDAO;
import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.entity.Furn;

import java.util.List;

public class FurnDAOImpl extends BasicDAO implements FurnDAO {
    @Override
    public List<Furn> queryFurns() {
        // 为什么不用select *？
        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn";
        List<Furn> furns = queryMulti(sql, Furn.class);
        return furns;
    }
}
