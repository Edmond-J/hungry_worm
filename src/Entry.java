public class Entry {
	public static void main(String[] args) {
		World world = new World(20, 10, 5, 3);//width, height, food, worms
		world.createFood();
		world.createWorm();
		world.refreshWrold();
	}
}
