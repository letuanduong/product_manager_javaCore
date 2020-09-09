package Client;

import java.util.Scanner;

public class User implements IUser {

	static Scanner scanner = new Scanner(System.in);
	
	@Override
	public boolean checkPassWord(Integer string) {
		System.out.println("------------------------------------------------");
		if(string == PASSWORD) {
			return true;
		} else {
			return false;
		}
	}
}
