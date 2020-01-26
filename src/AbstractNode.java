

public abstract class AbstractNode<T> {

	public final int RED = 0;
	public final int BLACK = 1;

	public AbstractNode() {
		root = nil;
	}
	
	public class Node<K> {
		Date key;
		K id;
		int color = BLACK;
		Node<T> left;
		Node<T> right;
		Node<T> parent;
		int smaller;

		public Node(Date key, K id) {
			this.key = key;
			this.id = id;
			left = nil;
			right = nil;
			parent = nil;
			smaller = 0;
		}
	}

	public class Date {
		int day;
		int mount;
		int year;

		public Date(int day, int mount, int year) {
			this.day = day;
			this.mount = mount;
			this.year = year;
		}
	}
	
	
	
	public final Date date = new Date(-1, -1, -1);
	public final Node<T> nil = new Node<T>(date, null);
	public Node<T> root;

	public String printDate(Date date) { //tarihi string yapar
		return (date.day + "/" + date.mount + "/" + date.year);
	}

	public String printNode(Node<T> node) { //nodu string yapar
		return (((node.color == RED) ? "Color: Red " : "Color: Black") + "\tKey: " + printDate(node.key) + "\tParent: "
				+ printDate(node.parent.key));
	}
	
	public Date splitDate(String item) { //dosya okurken veya kullanýcýdan input alýrken date split edilir
		String[] dates = item.split("/");
		return new Date(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
		
	}

}
