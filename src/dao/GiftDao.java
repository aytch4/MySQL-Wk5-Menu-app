package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Gift;

public class GiftDao {

	private Connection connection;
	private final String GET_GIFTS_BY_CHILD_ID_QUERY = "SELECT * FROM gifts WHERE children_id = ?";
	private final String DELETE_GIFTS_BY_CHILD_ID_QUERY = "DELETE FROM gifts WHERE children_id = ?";
	private final String CREATE_NEW_GIFT_QUERY = "INSERT INTO gifts(item, children_id) VALUES (?, ?)";
	private final String DELETE_GIFT_BY_ID_QUERY = "DELETE FROM gifts WHERE id = ?";
	
	public GiftDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Gift> getGiftsByChildId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_GIFTS_BY_CHILD_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		List<Gift> gifts = new ArrayList<Gift>();
		
		while (rs.next()) {
			gifts.add(new Gift(rs.getInt(1), rs.getString(2)));
		}
		return gifts;
	}

	public void createNewGift(String item, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_GIFT_QUERY);
		ps.setString(1, item);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void deleteGiftsByChildId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_GIFTS_BY_CHILD_ID_QUERY);
		ps.setInt(id, id);
		ps.executeUpdate();
	}
	
	public void deleteGiftById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_GIFT_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
}
