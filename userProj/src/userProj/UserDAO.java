package userProj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	public List<UserVO> getUserList() {
		conn = DBCon.getConnect();
		List<UserVO> list = new ArrayList<UserVO>();

		try {
			psmt = conn.prepareStatement("select * from user_temp");
			rs = psmt.executeQuery();
			while (rs.next()) {
				UserVO vo = new UserVO();

				vo.setId(rs.getString("user_id"));
				vo.setName(rs.getString("user_name"));
				vo.setPhone(rs.getString("user_phone"));
				vo.setGender(rs.getString("user_gender"));
				vo.setPass(rs.getString("user_pass"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	public int UserInsert(UserVO vo) {
		conn = DBCon.getConnect();
		int r = 0;
		String sql = "insert into user_temp values(?,?,?,?,?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPhone());
			psmt.setString(4, vo.getGender());
			psmt.setString(5, vo.getPass());
			r = psmt.executeUpdate();
			System.out.println(r + " 건 입력");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return r;
	}
	
	public boolean updateUser(UserVO vo) {
		conn = DBCon.getConnect();
		String sql = "update user_temp set user_name=?, user_pass=?, user_gender=?, user_phone=? where user_id=?";
		
		int Cnt = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getGender());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getId());
			
			Cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return Cnt == 0? false : true;
	}

	private void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
