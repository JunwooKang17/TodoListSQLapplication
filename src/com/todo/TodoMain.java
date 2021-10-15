package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist");
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l,"todolist");
		Menu.displaymenu();
		
		do {
			TodoUtil.setlateness(l);
			System.out.print("Command > ");
			isList = false;
			String choice = sc.next().trim();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				TodoUtil.listAll(l, "title", 1);
				
				System.out.println("항목이 제목순으로 나열되었습니다!");
				break;

			case "ls_name_desc":
				TodoUtil.listAll(l, "title", 0);
			
				System.out.println("항목이 제목역순으로 나열되었습니다!");
				
				break;
				
			case "ls_date":
				TodoUtil.listAll(l, "due_date", 1);
				System.out.println("항목이 날짜순으로 나열되었습니다!");
				
				break;

			case "exit":
				System.out.println("프로그램을 종료합니다!");
				quit = true;
				break;

			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				String f_title = sc.next().trim();
				TodoUtil.findList(l, f_title);
				break;
				
			case "ls_date_desc":
				TodoUtil.listAll(l, "due_date", 0);
				System.out.println("항목이 날짜순으로 나열되었습니다!");
				break;
			
			case "find_cate":
				String f_category = sc.next().trim();
				TodoUtil.findCate(l, f_category);
				break;
				
			case "ls_cate":
				TodoUtil.ls_cate(l);
				break;
			default:
				System.out.println("정확한 명령어를 입력해 주세요. (도움말 - help)");
				break;
				
			case "comp":
				int num = sc.nextInt();
				sc.nextLine();
				TodoUtil.comp(l, num);
				break;
		
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				break;
				
			case "set_priori":
				num = sc.nextInt();
				sc.nextLine();
				TodoUtil.set_priori(l, num);
				break;
				
			case "ls_priori":
				TodoUtil.listAllp(l,1);
				break;
				
			case "ls_late":
				TodoUtil.listAllL(l, 1);
				break;
				
			}
			
			if(isList) l.listAll();
		} while (!quit);
		
		TodoUtil.saveList(l,"todolist");
	}
}
