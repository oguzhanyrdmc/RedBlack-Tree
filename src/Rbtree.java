import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rbtree<T> extends AbstractTree<T> {

	public Rbtree() {
		super();
		read();
	}

	static boolean found = false;

	public void read() {
		File file = new File("people.txt");
		Scanner scanner;

		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data, dates;
				data = line.split(",");
				dates = data[1].split("/");
				key = new Date(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
				node = new Node<T>(key, (T) data[0]);// data geçirilecek generic olarak.

				insert(node);
			}
			System.out.println("File reading is succesfully. All items were inserted!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int getNumSmaller2(Node<T> node, T id) { //id yi bulana kadar aðaçta in-order dolanýyor
		if (node == nil) {
			return count;
		}
		if (found) {
			return count;
		}

		getNumSmaller2(node.left, id);
		if (node.id.equals(id)) {
			found = true;
			return count;
		}
		if (!found) {
			count = count + 1;
		}
		getNumSmaller2(node.right, id);
		return count;
	}

	//asagýdaki fonksiyonlarda -1 return ediliyorsa node tree de yoktur.
	
	public int getNumSmaller1_(Date date) { //getnumsmaller1 fonksiyounu çalýþtýrmaya hazýrlýyor
		count = 0;
		
		if(findNode(root, date) == null) {			
			return -1;
		}
		else {	
			return getNumSmaller1(root, date);
		}
	}
	
	public int getNumSmaller2_(T id) { //getnumsmaller2 fonksiyonunu çalýþmaya hazýrlýyor
		found = false;
		temp = null;
		count = 0;
		if(walkNode(root, id) == null) {			
			return -1;
		}
		else {	
			return getNumSmaller2(root, id);
		}
	}
	
}