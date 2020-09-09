package Client;

import java.io.IOException;
import java.util.Scanner;

import manager.ManagerFacade;
import manager.IkeyFunction;
import product.Book;
import product.Candy;
import product.Food;
import product.Product;
import product.ProductTypes;
import product.Samsung;
import product.iPhone;
import storage.Converter;
import storage.Exporter;

public class Client implements IkeyFunction {

	private ManagerFacade facadeManager = null;
	private Exporter exporter = null;
	private User user = null;
	private Converter converter = null;
	static Scanner scanner = new Scanner(System.in);

	public Client() throws IOException {
		facadeManager = new ManagerFacade();
		exporter = new Exporter();
	}

	public void importStorge() throws IOException {
		exporter.importer((facadeManager.getManager()).getList());
	}

	public void exportStorge() throws Exception {
		for (String string : exporter.exporter()) {
			if (string != null) {
				converter = new Converter();
				converter.regax(string);
				converter.convert();
				if(converter.getArrConveter()[0] != null) {
					if (converter.getArrConveter()[0].equals("BOOK")) {
						Book book = new Book(ProductTypes.BOOK, Integer.parseInt(converter.getArrConveter()[1]),
								Double.parseDouble(converter.getArrConveter()[2]), converter.getArrConveter()[3]);
						facadeManager.getManager().insert((Product) book);
					} else if ((converter.getArrConveter()[0]).equals("CANDY")) {
						Candy candy = new Candy(ProductTypes.BOOK, Integer.parseInt(converter.getArrConveter()[1]),
								Double.parseDouble(converter.getArrConveter()[2]), converter.getArrConveter()[3]);
						facadeManager.getManager().insert((Product) candy);
					} else if ((converter.getArrConveter()[0]).equals("FOOD")) {
						Food food = new Food(ProductTypes.BOOK, Integer.parseInt(converter.getArrConveter()[1]),
								Double.parseDouble(converter.getArrConveter()[2]), converter.getArrConveter()[3]);
						facadeManager.getManager().insert((Product) food);
					} else {
						System.out.println("Product don't have in productType");
					}
				}
//#######################################################################################################################
				if((converter.getArrConveter()[4]) != null)	{
					if ((converter.getArrConveter()[4]).equals("Samsung")) {
						Samsung samsung = new Samsung(ProductTypes.Samsung, Integer.parseInt(converter.getArrConveter()[5]),
								Double.parseDouble(converter.getArrConveter()[6]), converter.getArrConveter()[7]);
						facadeManager.getManager().insert((Product) samsung);
					} else if ((converter.getArrConveter()[4]).equals("iPhone")) {
						iPhone iPhone = new iPhone(ProductTypes.iPhone, Integer.parseInt(converter.getArrConveter()[5]),
								Double.parseDouble(converter.getArrConveter()[6]), converter.getArrConveter()[7]);
						facadeManager.getManager().insert((Product) iPhone);
					} else {
						System.out.println("Product don't have in productType");
					}
				}
//#######################################################################################################################
			}
		}
	}
	
	public void menu() {
		System.out.println("\n==============================================MENU BOARD============================================");
		System.out.println(INSERT_PRODUCT + ". INSERT_PRODUCT");
		System.out.println(DELETE_BY_ID +". DELETE_BY_ID");
		System.out.println(DELETE_BY_NAME +". DELETE_BY_NAME");
		System.out.println(EDIT_BY_ID +". EDIT_BY_ID");
		System.out.println(EDIT_BY_NAME +". EDIT_BY_NAME");
		System.out.println(SORT_BY_ID +". SORT_BY_ID");
		System.out.println(SORT_BY_NAME +". SORT_BY_NAME");
		System.out.println(SORT_BY_COST +". SORT_BY_COST");
		System.out.println(SEARCH_BY_ID +". SEARCH_BY_ID");
		System.out.println(SEARCH_BY_NAME +". SEARCH_BY_NAME");
		System.out.println(DISLAY_PRODUCT + ". DISLAY_PRODUCT");
		System.out.println(EXIT_PROGRAM +". EXIT_PROGRAM");
		System.out.println("====================================================================================================");
	}

	void check() {
		user = new User();
		System.out.println("========================!!! WELLCOME YOU !!!===============================");
		System.out.println("Enter Your PassWord");
		Integer pass = scanner.nextInt();
		if (user.checkPassWord(pass)) {
			System.out.println("You logined with account admin");
			while (true) {
				menu();
				int value = scanner.nextInt();
				if (value == 11) {
					facadeManager.action(value);
					break;
				}
				facadeManager.action(value);
			}
		} else {
			System.out.println("False PassWord!!! You logined with account customer");
			while (true) {
				menu();
				int value = scanner.nextInt();
				if (value == 11) {
					facadeManager.action(value);
					break;
				}
				if (value < 8)
					System.out.println("You must be logged into the admin account to use this feature!!!");
				else
					facadeManager.action(value);
			}
		}
	}

	public void start() throws Exception {
		exportStorge();
		check();
		importStorge();
	}
}
