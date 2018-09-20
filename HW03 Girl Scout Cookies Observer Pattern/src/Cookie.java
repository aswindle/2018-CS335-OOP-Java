
public class Cookie {
	private int boxes;
	private String name;

	public Cookie(String type, int inventory) {
		this.name = type;
		this.boxes = inventory;
	}

	/**
	 * @return the boxes
	 */
	public int getBoxes() {
		return boxes;
	}

	/**
	 * @param boxes
	 *            the boxes to set
	 */
	public void setBoxes(int boxes) {
		this.boxes = boxes;
	}

	/**
	 * @param boxes
	 *            to add to inventory
	 */
	public void addBoxes(int boxes) {
		this.boxes += boxes;
	}

	/**
	 * @param boxes
	 *            to subtract
	 * @return true if boxes were removed; false if that would create negative
	 *         inventory
	 */
	public boolean removeBoxes(int boxes) {
		boolean result;
		if (this.boxes - boxes < 0) {
			result = false;
		}
		else {
			this.boxes -= boxes;
			result = true;
		}
		return result;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
