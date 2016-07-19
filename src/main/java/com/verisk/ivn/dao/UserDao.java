package com.verisk.ivn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.verisk.ivn.common.DbConnection;
import com.verisk.ivn.model.UserModel;

public class UserDao {

	@Autowired
	DbConnection dbConnection;

	public boolean validateAuthentication(UserModel userModel) {

		String query = "select COUNT(*) from users where uname =:uname and password =:password";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("uname", userModel.getUserName());
		parameterSource.addValue("password", userModel.getPassWord());
		int result = dbConnection.getJdbcTemplate().queryForObject(query,
				parameterSource, Integer.class);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static final class UserMapper implements RowMapper<UserModel> {

		public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserModel userModel = new UserModel();
			userModel.setId(rs.getInt("id"));
			userModel.setUserName(rs.getString("uname"));
			userModel.setPassWord(rs.getString("password"));
			return userModel;
		}
	}

	public List<UserModel> getUserDetail() {
		String query = "select * from users";
		return dbConnection.getJdbcTemplate().query(query, new UserMapper());
	}

	public boolean saveUserDetail(UserModel userModel) {
		String query = "Insert into users(uname,password) values (:uname,:password)";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("uname", userModel.getUserName());
		parameterSource.addValue("password", userModel.getPassWord());
		int result = dbConnection.getJdbcTemplate().update(query,
				parameterSource);
		if (result > 0)
			return true;
		else
			return false;
	}

	public void deleteUserDetail(int id) {
		String query = "delete from users where id=:id";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		dbConnection.getJdbcTemplate().update(query, parameterSource);

	}

	public UserModel editUserDetail(int id) {
		String query = "select * from users where id=:id";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		return dbConnection.getJdbcTemplate().queryForObject(query,
				parameterSource, new UserMapper());
	}

	public void updateUserDetail(UserModel userModel) {
		String query = "Update users set uname=:uname, password=:password where id=:id";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("uname", userModel.getUserName());
		parameterSource.addValue("password", userModel.getPassWord());
		parameterSource.addValue("id", userModel.getId());
		dbConnection.getJdbcTemplate().update(query, parameterSource);
	}

}
