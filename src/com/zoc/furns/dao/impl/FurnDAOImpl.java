package com.zoc.furns.dao.impl;

import com.zoc.furns.dao.BasicDAO;
import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.entity.Furn;

import java.util.List;

public class FurnDAOImpl extends BasicDAO<Furn> implements FurnDAO {
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
    public int delFurn(int id) {
        String sql = "DELETE FROM furn WHERE id=?";
        return update(sql, id);
    }

    @Override
    public Furn queryFurn(int id) {
        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn WHERE id=?";
        return querySingle(sql, Furn.class, id);
    }

    @Override
    public int updateFurn(Furn furn) {
        String sql = "UPDATE furn SET NAME=?,maker=?,price=?,sales=?,stock=?,img_path=? WHERE id=?";
        return update(sql,furn.getName(),furn.getMaker(),furn.getPrice(),furn.getSales(),furn.getStock(),furn.getImgPath(),furn.getId());
    }

    @Override
    public int getTotalRow() {
        String sql = "SELECT COUNT(*) FROM furn";
        // Java基本类中包装类
        return  ((Number) queryScalar(sql)).intValue();
    }

    @Override
    public List<Furn> getPageItems(int begin, int pageSize) {
        // 这里的begin应该是pageNo的，但是这个pageNo需要自己计算，把这个计算放到service中去做
        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn LIMIT ? , ?";
        return  queryMulti(sql, Furn.class, begin, pageSize);
    }

    @Override
    public int getTotalRowByName(String name) {
        String sql = "SELECT COUNT(*) FROM furn WHERE name LIKE ?";
        // String name1 = "'%" + name + "%'";
        return ((Number) queryScalar(sql, "%" + name + "%")).intValue();
    }

    @Override
    public List<Furn> getPageItemsByName(int begin, int pageSize,String name) {
        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn WHERE name LIKE ? LIMIT ? , ?";
        return  queryMulti(sql, Furn.class,"%" + name + "%", begin, pageSize);
    }
}
