package activitytext.example.com.demo04.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import activitytext.example.com.demo04.bean.User;

public class UserListDao {
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<User>();
        Connection conn = DataBase.getConnection();
        ResultSet rs=null;
        PreparedStatement pstmt = null;
        String sql = "select * \r\n" +
                "from user_list;";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userEmail"));
                userList.add(user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            DataBase.close(rs);
            DataBase.close(pstmt);
            DataBase.close(conn);
        }
        return userList;
    }
}
