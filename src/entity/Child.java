package entity;

import java.util.List;

public class Child {

	 private int childId;
	 private String name;
	 private List<Gift> gifts;
	 
	 public Child(int childId, String name, List<Gift> gifts) {
		 this.setChildId(childId);
		 this.setName(name);
		 this.setGifts(gifts);
	 }

	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}
	 
	 
}
