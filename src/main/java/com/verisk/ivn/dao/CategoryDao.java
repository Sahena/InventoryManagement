package com.verisk.ivn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.verisk.ivn.common.DbConnection;
import com.verisk.ivn.model.CategoryModel;

public class CategoryDao {
	@Autowired
	DbConnection dbconnection;

	private static final class CategoryMapper implements
			RowMapper<CategoryModel> {
		public CategoryModel mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			CategoryModel catModel = new CategoryModel();
			catModel.setCid(rs.getInt("cid"));
			catModel.setCname(rs.getString("cname"));
			return catModel;
		}
	}

	public void saveCategory(CategoryModel catModel) {
		String query = "Insert into category (cname) values (:cname)";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("cname", catModel.getCname());
		dbconnection.getJdbcTemplate().update(query, parameterSource);
	}

	public List<CategoryModel> displayCategory() {
		String query = "select * from category";
		return dbconnection.getJdbcTemplate()
				.query(query, new CategoryMapper());
	}

	public CategoryModel editCategory(int cid) {
		String query = "select * from category where cid=:cid";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("cid", cid);
		return dbconnection.getJdbcTemplate().queryForObject(query,
				parameterSource, new CategoryMapper());
	}

	public void updateCategory(CategoryModel catModel) {
		String query = "UPDATE category set cname=:cname where cid=:cid";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("cname", catModel.getCname());
		parameterSource.addValue("cid", catModel.getCid());
		dbconnection.getJdbcTemplate().update(query, parameterSource);
	}

	public void deleteCategory(int cid) {
		String query = "delete from category where cid=:cid";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("cid", cid);
		dbconnection.getJdbcTemplate().update(query, parameterSource);
	}
	
}
