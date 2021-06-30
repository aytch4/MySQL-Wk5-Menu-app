package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Child;

public class ChildDao {

	private Connection connection;
	private GiftDao giftDao;
	private final String GET_CHILDREN_QUERY = "SELECT * FROM children";
	private final String GET_CHILD_BY_ID_QUERY = "SELECT * FROM children WHERE id = ?";
	private final String CREATE_NEW_CHILD_QUERY = "INSERT INTO children(name) VALUES(?)";
	private final String DELETE_CHILD_BY_ID_QUERY = "DELETE FROM children WHERE id = ?";
	
	public ChildDao() {
		connection = DBConnection.getConnection();
		giftDao = new GiftDao();
	}
	
	public List<Child> getChildren() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CHILDREN_QUERY).executeQuery();
		List<Child> children = new ArrayList<Child>();
		
		while (rs.next()) {
			children.add(assignGiftsToChild(rs.getInt(1), rs.getString(2)));
		}
		return children;
	}
	
	public Child getChildById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CHILD_BY_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return assignGiftsToChild(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewChild(String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CHILD_QUERY);
		ps.setString(1,  name);
		ps.executeUpdate();
	}
	
	
	public void deleteChildById(int id) throws SQLException {
		giftDao.deleteGiftsByChildId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_CHILD_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	private Child assignGiftsToChild(int id, String item) throws SQLException {
		return new Child(id, item, giftDao.getGiftsByChildId(id));
	}
	
	
	
}
