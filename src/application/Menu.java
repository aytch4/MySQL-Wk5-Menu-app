package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.GiftDao;
import dao.ChildDao;
import entity.Gift;
import entity.Child;

public class Menu {

	private ChildDao childDao = new ChildDao();
	private GiftDao giftDao = new GiftDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Children", 
			"Display a Child",
			"Create Child",
			"Delete Child",
			"Create Gift",
			"Delete Gift");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayChildren();
				} else if (selection.equals("2")) {
					displayChild();
				} else if (selection.equals("3")) {
					createChild();
				} else if (selection.equals("4")) {
					deleteChild();	
				} else if (selection.equals("5")) {
					createGift();
				} else if (selection.equals("6")) {
					deleteGift();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue...");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));		
	}

	private void printMenu() {
		System.out.println("Select an Option:\n-----------------------");
		for(int i = 0; i< options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	private void displayChildren() throws SQLException {
			List<Child> children = childDao.getChildren();
			for (Child child : children) {
				System.out.println(child.getChildId() +": " + child.getName());
			}
		}	
		
	private void displayChild() throws SQLException {
			System.out.print("Enter child id: ");	
			int id = Integer.parseInt(scanner.nextLine());
			Child child = childDao.getChildById(id);
			System.out.println(child.getChildId() + ": " + child.getName());
			for (Gift gift : child.getGifts()) {
				System.out.println("\tGiftId: " + gift.getGiftId() + " - Item: " + gift.getItem());;
		}
	}

	private void createChild() throws SQLException {
		System.out.print("Enter new child's name: ");	
		String name = scanner.nextLine();
		childDao.createNewChild(name);
	}

	private void deleteChild() throws SQLException {
		System.out.print("Enter child's id to delete: ");	
		int id = Integer.parseInt(scanner.nextLine());
		childDao.deleteChildById(id);
	}
		
	private void createGift() throws SQLException {
		System.out.print("Enter item: ");	
		String item = scanner.nextLine();
		System.out.print("Enter the ID of the child the gift is for: ");	
		int childId = Integer.parseInt(scanner.nextLine());
		giftDao.createNewGift(item, childId);
	}
	
	private void deleteGift() throws SQLException {
			System.out.print("Enter gift id to delete: ");	
			int id = Integer.parseInt(scanner.nextLine());
			giftDao.deleteGiftById(id);
		}
		
	}
	



