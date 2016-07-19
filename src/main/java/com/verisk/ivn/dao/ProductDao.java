package com.verisk.ivn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.verisk.ivn.common.DbConnection;
import com.verisk.ivn.model.CategoryModel;
import com.verisk.ivn.model.ProductModel;

public class ProductDao {
	@Autowired
	private DbConnection dbconnection;

	@Autowired
	private static CategoryDao cdao;

	private final static class ProductMapper implements RowMapper<ProductModel> {
		public ProductModel mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			ProductModel pmodel = new ProductModel();
			CategoryModel cmodel = new CategoryModel();
			pmodel.setPid(rs.getInt("pid"));
			pmodel.setPname(rs.getString("pname"));
			pmodel.setPrice(rs.getInt("price"));
			pmodel.setQuantity(rs.getInt("quantity"));
			cmodel.setCname(rs.getString("cname"));
			cmodel.setCid(rs.getInt("cid"));
			pmodel.setCmodel(cmodel);
			return pmodel;
		}

	}

	public void saveProduct(ProductModel prodModel) {
		String query = "Insert into product (pname,price,quantity,cid) values (:pname,:price,:quantity,:cid)";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("pname", prodModel.getPname());
		parameterSource.addValue("price", prodModel.getPrice());
		parameterSource.addValue("quantity", prodModel.getQuantity());
		parameterSource.addValue("cid", prodModel.getCmodel().getCid());
		dbconnection.getJdbcTemplate().update(query, parameterSource);
	}

	public List<ProductModel> displayProduct() {
		String query = "SELECT a.pid, a.pname, a.price, a.quantity, b.cid, b.cname FROM product a LEFT JOIN category b ON a.cid= b.cid";
		return dbconnection.getJdbcTemplate().query(query, new ProductMapper());
	}

	public void deleteProduct(int pid) {
		String query = "delete from product where pid=:pid";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("pid", pid);
		dbconnection.getJdbcTemplate().update(query, parameterSource);
	}

	public ProductModel getProductFromId(int pid) {
		String query = "select a.pid, a.pname, a.price, a.quantity, b.cid, b.cname FROM product a LEFT JOIN category b ON a.cid= b.cid WHERE pid=:pid";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("pid", pid);
		return dbconnection.getJdbcTemplate().queryForObject(query,
				parameterSource, new ProductMapper());
	}

	public void updateProduct(ProductModel pmodel) {
		String query = "Update product set pname=:pname, price=:price, quantity=:quantity,cid=:cid where pid=:pid";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("pname", pmodel.getPname());
		parameterSource.addValue("price", pmodel.getPrice());
		parameterSource.addValue("quantity", pmodel.getQuantity());
		parameterSource.addValue("cid", pmodel.getCmodel().getCid());
		parameterSource.addValue("pid", pmodel.getPid());
		dbconnection.getJdbcTemplate().update(query, parameterSource);

	}
}
