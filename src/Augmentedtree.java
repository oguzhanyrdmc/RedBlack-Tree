import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Augmentedtree<T> extends AbstractTree<T> {

	public Augmentedtree() {
		super();
		read();
	}
	
	@Override
	public void insert_(T id, String item) { //consoldan al�nan veriyi treeye eklemeye haz�rlan�yor
		Date key = splitDate(item);
		node = new Node<T>(key, id);
		insert(node);
		augmented();
	}
	
	private void augmented() {
		node.smaller = getNumSmaller1(root, node.key); // kendinden k���k nodelar�n say�s�n� say�yor
		smaller(root, node.key); // kendinden b�y�k nodelara +1 yap�yor
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
				node = new Node<T>(key, (T) data[0]);// data ge�irilecek generic olarak.
				
				insert(node);
				augmented();
			}
			System.out.println("File reading is succesfully. All items were inserted!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void smaller(Node<T> node, Date date) { //kendinden b�y�k nodelar�n smaller ded�erini bir artt�r�yor
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

	

	
	//asag�daki fonksiyonlarda -1 return ediliyorsa node tree de yoktur.
	
	protected int getNumSmaller1_(Date date) { //verilen tarihten nodu bulup node'da tutulan smaller return ediliyor
		if(findNode(root, date) == null) {
			return -1;
		}
		return findNode(root, date).smaller;
	}

	protected int getNumSmaller2_(T id) { // a�a�ta gezinmeye haz�rlan�l�yor ard�ndan bulunan node'da smaller return ediliyor
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
