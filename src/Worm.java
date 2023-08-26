import java.util.ArrayList;

public class Worm {
	ArrayList<Coordinate> body = new ArrayList<Coordinate>();

	public Worm(int x, int y) {
		body.add(new Coordinate(x, y));
	}

	public void action(World w) {
		for (int i = 0; body.size() < 3; i++) {
			Coordinate tail = findDirect(body.get(i), w);
			body.add(tail);
		}
		Coordinate next = findDirect(body.get(0), w);
		if (next != null) {
			for (Coordinate food : w.foodList) {
				if (next.x == food.x && next.y == food.y) {
					body.add(0, next);
					w.foodList.remove(food);
					return;
				}
			}
			body.remove(body.size()-1);
			body.add(0, next);
		}
	}

	public Coordinate findDirect(Coordinate co, World w) {
		ArrayList<Coordinate> nearBy = new ArrayList<Coordinate>();
//		int x0 = w.body.get(0).x;
//		int y0 = w.body.get(0).y;
//		int x1 = w.body.get(1).x;
//		int y1 = w.body.get(1).y;
		int x = co.x;
		int y = co.y;
		if (w.checkAva(x+1, y)) {
			nearBy.add(new Coordinate(x+1, y));
		}
		if (w.checkAva(x, y+1)) {
			nearBy.add(new Coordinate(x, y+1));
		}
		if (w.checkAva(x-1, y)) {
			nearBy.add(new Coordinate(x-1, y));
		}
		if (w.checkAva(x, y-1)) {
			nearBy.add(new Coordinate(x, y-1));
		}
		if (nearBy.size() != 0) {
			return nearBy.get((int)(Math.random()*nearBy.size()));
		}
		return null;
	}
}
