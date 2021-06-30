package entity;

public class Gift {
	private int giftId;
	private String item;
	
	
	public Gift(int giftId, String item) {
		this.giftId = giftId;
		this.item = item;
	}

	public int getGiftId() {
		return giftId;
	}

	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
