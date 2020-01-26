
public abstract class AbstractTree<T> extends AbstractNode<T> {

	public Date key;
	public Node<T> node;
	
	public AbstractTree() {
		super();
	}

	public abstract void read();
	
	protected void insert(Node<T> node) {
		Node<T> temp = root;
		if (root == nil) {
			root = node;
			node.color = BLACK;
			node.parent = nil;
		} else {
			node.color = RED;
			while (true) {
				if (node.key.year > temp.key.year) {
					if (temp.left == nil) {
						temp.left = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.left;
					}
				} 
				else if (node.key.year < temp.key.year) {
					if (temp.right == nil) {
						temp.right = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.right;
					}
				} else if (node.key.year == temp.key.year && node.key.mount > temp.key.mount) {
					if (temp.left == nil) {
						temp.left = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.left;
					}
				} else if (node.key.year == temp.key.year && node.key.mount < temp.key.mount) {
					if (temp.right == nil) {
						temp.right = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.right;
					}
				} else if (node.key.year == temp.key.year && node.key.mount == temp.key.mount
						&& node.key.day > temp.key.day) {
					if (temp.left == nil) {
						temp.left = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.left;
					}
				} else if (node.key.year == temp.key.year && node.key.mount == temp.key.mount
						&& node.key.day < temp.key.day) {
					if (temp.right == nil) {
						temp.right = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.right;
					}
				}
			}
			fixTree(node);
		}
	}

	public void insert_(T id, String item) { //Consoldan alýnan inputlarý insert etmek için kullanýlýr
		Date key = splitDate(item);
		node = new Node<T>(key, id);
		insert(node);
	}
	
	private void fixTree(Node<T> node) {
		while (node.parent.color == RED) {
			Node<T> uncle = nil;

			if (node.parent == node.parent.parent.left) {
				uncle = node.parent.parent.right;
				if (uncle != nil && uncle.color == RED) {
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.right) {// Double rotation
					node = node.parent;
					rotateLeft(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rotateRight(node.parent.parent);
			} else {
				uncle = node.parent.parent.left;
				if (uncle != nil && uncle.color == RED) {
					node.parent.color = BLACK;
					uncle.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.left) {// Double rotation
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rotateLeft(node.parent.parent);
			}
		}
		root.color = BLACK;
	}

	private void rotateLeft(Node<T> node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.right;
			} else {
				node.parent.right = node.right;
			}
			node.right.parent = node.parent;
			node.parent = node.right;
			if (node.right.left != nil) {
				node.right.left.parent = node;
			}
			node.right = node.right.left;
			node.parent.left = node;
		} else {// rotate root
			Node<T> right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = nil;
			root = right;
		}
	}

	private void rotateRight(Node<T> node) {
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}

			node.left.parent = node.parent;
			node.parent = node.left;
			if (node.left.right != nil) {
				node.left.right.parent = node;
			}
			node.left = node.left.right;
			node.parent.right = node;
		} else { // rotate root
			Node<T> left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left;
		}
	}

	public Node<T> findNode(Node<T> node, Date date) { //Aradýðýmýz nodu buluyor
		if (node == nil)
			return null;

		if (node.key.year < date.year) {
			return findNode(node.left, date);
		} 
		else if (node.key.year > date.year) {
			return findNode(node.right, date);
		} 
		else if (node.key.year == date.year && node.key.mount < date.mount) {
			return findNode(node.left, date);
		} 
		else if (node.key.year == date.year && node.key.mount > date.mount) {
			return findNode(node.right, date);
		} 
		else if (node.key.year == date.year && node.key.mount == date.mount && node.key.day < date.day) {
			return findNode(node.left, date);
		} 
		else if (node.key.year == date.year && node.key.mount == date.mount && node.key.day > date.day) {
			return findNode(node.right, date);
		} 
		else if (node.key.year == date.year && node.key.mount == date.mount && node.key.day == date.day) {
			return node;
		}
		return null;
	}
	
	static boolean found = false;
	protected Node<T> temp;
	public Node<T> walkNode(Node<T> node, T id) { //idyi bulana kadar treede geziniyor
		if (node == nil) {
			return null;
		}

		if (node.id.equals(id)) {
			temp = node;
			found = true;
			return temp;
		}

		walkNode(node.left, id);
		walkNode(node.right, id);
		if (found) {

			return temp;
		}
		
		return null;
	}
	
	
	protected int count = 0;
	public int getNumSmaller1(Node<T> node, Date date) { //tarihi verilen input bulunuyor
		if (node == nil) {
			return count;
		}
		getNumSmaller1(node.left, date);

		if (node.key.year > date.year) {
			count = count + 1;
		} else if (node.key.year == date.year && node.key.mount > date.mount) {
			count = count + 1;

		} else if (node.key.year == date.year && node.key.mount == date.mount && node.key.day > date.day) {
			count = count + 1;
		}

		getNumSmaller1(node.right, date);
		return count;
	}
	
	public Node<T> getMax(Node<T> node) { 
		while (node.right != nil) {
			node = node.right;
		}
		return node;
	}

	public Node<T> getMin(Node<T> node) {
		while (node.left != nil) {
			node = node.left;
		}
		return node;
	}
	
	public int getNum(Node<T> node) { // tree size'ýný hesaplýyor
		if (node == nil) {
			return 0;
		}
		int x = getNum(node.left);
		int count = 1;
		int y = getNum(node.right);
		return x+count+y;
	}
	
	public void printTree(Node<T> node) { //treeyi ekrana yazdýrýyor
		if (node == nil) {
			return;
		}
		
		printTree(node.left);
		System.out.println(printNode(node));
		printTree(node.right);
	}
	
	
}
