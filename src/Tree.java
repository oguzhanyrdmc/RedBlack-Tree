import java.util.Scanner;

public class Tree<T>  {

	Rbtree<T> rbTree;
	Augmentedtree<T> augTree;

	public Tree() {
		rbTree = new Rbtree<T>();
		augTree = new Augmentedtree<T>();
		consoleUI();
	}

	public void consoleUI() {

		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("\nMenu\n" + 
					"1- Insert a new person\n" + 
					"2- GETNUMSMALLER\n" + 
					"3- GETMAX\n" + 
					"4- GETMIN\n" + 
					"5- GETNUM\n" + 
					"6- PRINT");

			int choice = scan.nextInt();
			String item;
			T id;
			
			switch (choice) {
			case 1:
				System.out.println("please enter Id:");
				id = (T) scan.next();
				System.out.println("please enter birthday with split '/' ");
				item = scan.next();				
				rbTree.insert_(id, item);			
				System.out.println("------Red-Black Tree:------\n\t->Item were inserted.");
				
				augTree.insert_(id, item);
				System.out.println("------Augmented RB-Tree:------\n\t->Item were inserted.");
				break;
			case 2:
				System.out.println("	Menu 2.0\n" +
								   "	1- GETNUMSMALLER1\n" + 
								   "	2- GETNUMSMALLER2");
				int choice2 = scan.nextInt();
				switch(choice2) {
				case 1: // getNumSmaller1
					System.out.println("please enter birthday with split '/' ");
					item = scan.next();										
					System.out.println("------Red-Black Tree:------");
					System.out.println("The result of GETNUMSMALLER1 for the node with birthdate:" + item + " is " + rbTree.getNumSmaller1_(rbTree.splitDate(item)));			
					
					System.out.println("\n------Augmented RB-Tree:------");
					System.out.println("The result of GETNUMSMALLER1 for the node with birthdate:" + item + " is " + augTree.getNumSmaller1_(augTree.splitDate(item)));							
					break;
				case 2: // getNumSmaller2
					System.out.println("please enter Id:");
					id = (T) scan.next();				
					System.out.println("------Red-Black Tree:------");
					System.out.println("The result of GETNUMSMALLER2 for the node with id: " +id+ " is "+ rbTree.getNumSmaller2_(id));
					
					System.out.println("\n------Augmented RB-Tree:------");
					System.out.println("The result of GETNUMSMALLER2 for the node with id: " +id+ " is "+ augTree.getNumSmaller2_(id));
					break;
				}	
				break;
			case 3: //getMax
				System.out.println("------Red-Black Tree:------");
				System.out.println("The minimum age of all people is " + (2018 - rbTree.getMax(rbTree.root).key.year)
						+ " ,id: " + rbTree.getMax(rbTree.root).id + " ,Birthdate: "
						+ rbTree.printDate(rbTree.getMax(rbTree.root).key));
				
				System.out.println("\n------Augmented RB-Tree:------");
				System.out.println("The minimum age of all people is " + (2018 - augTree.getMax(augTree.root).key.year)
						+ " ,id: " + augTree.getMax(augTree.root).id + " ,Birthdate :"
						+ augTree.printDate(augTree.getMax(augTree.root).key));
				break;
			case 4: //getMin
				System.out.println("------Red-Black Tree:------");
				System.out.println("The minimum age of all people is " + (2018 - rbTree.getMin(rbTree.root).key.year)
						+ " ,id: " + rbTree.getMin(rbTree.root).id + " ,Birthdate: "
						+ rbTree.printDate(rbTree.getMin(rbTree.root).key));
				
				System.out.println("\n------Augmented RB-Tree:------");
				System.out.println("The minimum age of all people is " + (2018 - augTree.getMin(augTree.root).key.year)
						+ " ,id: " + augTree.getMin(augTree.root).id + " ,Birthdate: "
						+ augTree.printDate(augTree.getMin(augTree.root).key));
				break;
			case 5: //getNum
				System.out.println("------Red-Black Tree:------");
				System.out.println("The number of all people is: " + rbTree.getNum(rbTree.root));
			
				System.out.println("\n------Augmented RB-Tree:------");
				System.out.println("The number of all people is: " + augTree.getNum(augTree.root));
				break;
			case 6: //print
				System.out.println("------Red-Black Tree:------");
				rbTree.printTree(rbTree.root);
				System.out.println("\n------Augmented RB-Tree:------");
				augTree.printTree(augTree.root);
				break;
			}
		}
	}

	public static void main(String[] args) {

		Tree trees = new Tree();

	}
}
