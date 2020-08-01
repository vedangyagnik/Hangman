//Vedang Yagnik
//101285281
public class Category {
	private String[] foods = {"Pizza", "Pasta", "Sandwich", "Ice Cream", "Croissant"};
	private String[] sports = {"Cricket", "Football", "Table Tennis", "Baseball", "Hockey"};
	private String[] games = {"Counter Strike", "Call of duty", "Max Payne", "Black Mesa", "Age of empire"};
	
	public Category() {
		
	}

	public String[] getCategory(int catgory) {
		String[] category = {};
		switch (catgory) {
		case 1:
			category = this.foods;
			break;
		case 2:
			category = this.sports;
			break;
		case 3:
			category = this.games;
			break;
		}
		
		return category;
	}

	public String getCategoryName(int catgory) {;
		String catName = "";
		switch (catgory) {
		case 1:
			catName = "Foods";
			break;
		case 2:
			catName = "Sports";
			break;
		case 3:
			catName = "Games";
			break;
		}
		
		return catName;
	}
}


