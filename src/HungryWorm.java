import java.util.ArrayList;

public class HungryWorm {
	boolean alive;
	ArrayList<Coordinate> body = new ArrayList<Coordinate>();

	public HungryWorm(int x, int y) {
		body.add(new Coordinate(x, y));
		body.add(new Coordinate(x, y));
		body.add(new Coordinate(x, y));
		body.add(new Coordinate(x, y));
		alive = true;
	}

	public void action(World w) {
		int xforward = 2*body.get(0).x-body.get(1).x;
		int yforward = 2*body.get(0).y-body.get(1).y;
		Coordinate next = new Coordinate();
		// 有40%的几率直接往前尝试
		if (Math.random() < 0.4 && w.checkAva(xforward, yforward)) {
			next = new Coordinate(xforward, yforward);
		} else {
			next = findDirect(body.get(0), w);
		}
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
		} else {
			alive = false;
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
