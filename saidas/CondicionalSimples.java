import java.util.Scanner;
public class CondicionalSimples{ 
	public static void main(String args[]){
 		Scanner _key = new Scanner(System.in);
		int  a;
		int  b;
		int  i;
		b= _key.nextInt();
		i= _key.nextInt();
		a = b+i;
		if (a>b) {
			System.out.println(a);
		}

		_key.close();
	}
}