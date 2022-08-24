import java.util.Scanner;
public class MainClass{ 
	public static void main(String args[]){
 		Scanner _key = new Scanner(System.in);
		int  a;
		int  b;
		int  i;
		String  t1;
		a= _key.nextInt();
		t1 = "kaio";
		b = a;
		if (a>b) {
			System.out.println(a);
		}

		_key.close();
	}
}