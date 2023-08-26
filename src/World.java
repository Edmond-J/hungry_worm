import java.util.ArrayList;

public class World {
	int width;
	int height;
	int foodQua;
	int wormQua;
	String map[][] = new String[height][width];
	ArrayList<Worm> wormList = new ArrayList<Worm>();
	ArrayList<Coordinate> foodList = new ArrayList<Coordinate>();

	public World(int w, int h, int f, int worm) {
		width = w;
		height = h;
		foodQua = f;
		wormQua = worm;
	}

	public void createWorm() {
		for (int i = 0; i < wormQua;) {
			int x = (int)(Math.random()*width);
			int y = (int)(Math.random()*height);
			if (checkAva(x, y)) {
				wormList.add(new Worm(x, y));
				i++;
			}
		}
	}

	public void createFood() {
		while (foodList.size() < foodQua) {
			int x = (int)(Math.random()*width);
			int y = (int)(Math.random()*height);
			if (checkAva(x, y)) {
				foodList.add(new Coordinate(x, y));
			}
		}
	}

	public boolean checkAva(int x, int y) {
		if (x < 0 || x > width-1 || y < 0 || y > height-1) {
			return false;
		}
		for (Worm w : wormList) {
			for (Coordinate co : w.body) {
				if ((x == co.x && y == co.y)) {
					return false;
				}
			}
		}
		return true;
	}

	public void refreshWrold(int n, int speed) {
		for (int i = 0; i < n; i++) {
			drawWorld();
			try {
				Thread.sleep(1000/speed);
			} catch (InterruptedException e) {
			}
		}
	}

	public void drawWorld() {// 不断刷新
		map = new String[height][width];
		createFood();
		for (Worm w : wormList) {// load the new position of all bugs
			for (Coordinate co : w.body) {
				map[co.y][co.x] = "O ";
			}
			map[w.body.get(w.body.size()-1).y][w.body.get(w.body.size()-1).x] = "o ";
			map[w.body.get(0).y][w.body.get(0).x] = "X ";
		}
		System.out.println("------------------------------------------");
		for (int i = 0; i < height; i++) {
			System.out.print('|');
			for (int j = 0; j < width; j++) {
				for (Coordinate co : foodList) {
					if (co.x == j && co.y == i) {
						map[i][j] = "* ";
					}
				}
				if (map[i][j] == null) {
					map[i][j] = "  ";
				}
				System.out.print(map[i][j]);
			}
			System.out.println('|');
		}
		System.out.println("------------------------------------------");
		for (Worm w : wormList) {
			w.action(this);
		}
	}
}
