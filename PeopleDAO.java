package CSDL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PeopleDAO implements DAO_Interface<People> {

	public static PeopleDAO getInstance() {
		return new PeopleDAO();
	}
	
	public int insert(People t) {
        int ketQua = 0;
		try {
			Connection con = connectDatabase.getConnection();
			Statement st = con.createStatement();
			
			String sql = "INSERT INTO NguoiChoi(TenNguoiChoi, Diem)"
					 + " VALUES ('"+t.getTenNguoiChoi()+"' , "+t.getDiem()+" )";
			ketQua = st.executeUpdate(sql);
						
			connectDatabase.closeConnection(con);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ketQua;
	}

	public int delete(People t) {
		int ketQua = 0;
		try {
			Connection con = connectDatabase.getConnection();
			Statement st = con.createStatement();
			
			String sql = "DELETE from NguoiChoi"+
			             " WHERE TenNguoiChoi= '"+t.getTenNguoiChoi()+"' ";
			ketQua = st.executeUpdate(sql);
			
			connectDatabase.closeConnection(con);
			
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ketQua;
	}
	
	public ArrayList<People> selectAll() {
		ArrayList<People> ketQua = new ArrayList<People>();
		try {
			Connection con = connectDatabase.getConnection();
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM NguoiChoi ORDER BY Diem DESC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";			
			ResultSet rs = st.executeQuery(sql);	    
	
			while(rs.next()) {
				String TenNguoiChoi = rs.getString("TenNguoiChoi");
				int Diem = rs.getInt("Diem");								
				People p = new People(TenNguoiChoi, Diem);
				ketQua.add(p);
			}			
			connectDatabase.closeConnection(con);
			
        } catch (SQLException e) {			
			e.printStackTrace();
		}				
		return ketQua;
	}
    
	public boolean nameExists(String name) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM NguoiChoi WHERE TenNguoiChoi = ?";
        try (Connection conn = connectDatabase.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
	
	public People findByName(String name) {
        People player = null;
        try (Connection con = connectDatabase.getConnection()) {
            String sql = "SELECT * FROM NguoiChoi WHERE TenNguoiChoi = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String tenNguoiChoi = rs.getString("TenNguoiChoi");
                        int diem = rs.getInt("Diem");
                        player = new People(tenNguoiChoi, diem);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }
	
	public int update(People t) {
        int rowsAffected = 0; 
        try {
            Connection con = connectDatabase.getConnection();
            String sql = "UPDATE NguoiChoi SET Diem = ? WHERE TenNguoiChoi = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, t.getDiem()); 
            pst.setString(2, t.getTenNguoiChoi()); 
            
            rowsAffected = pst.executeUpdate();
            
            pst.close();
            connectDatabase.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected; 
    }
	
	 public void deletePlayersWithZeroScore() {
	        Connection con = null;
	        PreparedStatement stmt = null;
	        try {
	            con = connectDatabase.getConnection();
	            String sql = "DELETE FROM NguoiChoi WHERE Diem = 0";
	            stmt = con.prepareStatement(sql);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (stmt != null) {
	                try {
	                    stmt.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (con != null) {
	                connectDatabase.closeConnection(con);
	            }
	        }
	    }
}
