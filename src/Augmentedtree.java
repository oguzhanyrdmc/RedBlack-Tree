import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Augmentedtree<T> extends AbstractTree<T> {

	public Augmentedtree() {
		super();
		read();
	}
	
	@Override
	public void insert_(T id, String item) { //consoldan alýnan veriyi treeye eklemeye hazýrlanýyor
		Date key = splitDate(item);
		node = new Node<T>(key, id);
		insert(node);
		augmented();
	}
	
	private void augmented() {
		node.smaller = getNumSmaller1(root, node.key); // kendinden küçük nodelarýn sayýsýný sayýyor
		smaller(root, node.key); // kendinden büyük nodelara +1 yapýyor
		count = 0;
	}
	
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
				augmented();
			}
			System.out.println("File reading is succesfully. All items were inserted!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void smaller(Node<T> node, Date date) { //kendinden büyük nodelarýn smaller dedðerini bir arttýrýyor
		if (node == nil) {
			return;
		}

		smaller(node.right, date);

		if (node.key.year < date.year) {
			node.smaller++;
		} 
		else if (node.key.year == date.year && node.key.mount < date.mount) {
			node.smaller++;
		} 
		else if (node.key.year == date.year && node.key.mount == date.mount && node.key.day < date.day) {
			node.smaller++;
		}

		smaller(node.left, date);
		return;
	}

	

	
	//asagýdaki fonksiyonlarda -1 return ediliyorsa node tree de yoktur.
	
	protected int getNumSmaller1_(Date date) { //verilen tarihten nodu bulup node'da tutulan smaller return ediliyor
		if(findNode(root, date) == null) {
			return -1;
		}
		return findNode(root, date).smaller;
	}

	protected int getNumSmaller2_(T id) { // aðaçta gezinmeye hazýrlanýlýyor ardýndan bulunan node'da smaller return ediliyor
		found = false;
		temp = null;
		if(walkNode(root, id) == null) {			
			return -1;
		}
		else {	
			return walkNode(root, id).smaller;
		}
	}

}
