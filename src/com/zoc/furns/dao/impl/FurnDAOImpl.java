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

    @Override
    public boolean addFurn(Furn furn) {
        String sql = "INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`) " +
                "VALUES(NULL , ? , ? , ? , ? , ? , ?);";
        int row = update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath());
        return row > 0;
    }

    // 这里其实是根据id来删除furn
    @Override
    public int delFurn(Furn furn) {
        String sql = "DELETE FROM furn WHERE id=?";
        return update(sql, furn.getId());
    }
}
