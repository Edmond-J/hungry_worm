public class Entry {
	public static void main(String[] args) {
		World world = new World(20, 10, 3, 3);
		world.createFood();
		world.createWorm();
		world.refreshWrold(100, 5);
	}
}
