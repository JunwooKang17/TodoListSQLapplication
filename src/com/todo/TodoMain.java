package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l,"todolist");
		Menu.displaymenu();
		
		do {
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
				l.sortByName();
				isList = true;
				System.out.println("항목이 제목순으로 나열되었습니다!");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("항목이 제목역순으로 나열되었습니다!");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("항목이 날짜순으로 나열되었습니다!");
				isList = true;
				break;

			case "exit":
				System.out.println("프로그램을 종료합니다!");
				quit = true;
				break;

			case "help":
				Menu.displaymenu();
				break;
				
			default:
				System.out.println("정확한 명령어를 입력해 주세요. (도움말 - help)");
				break;
				
			}
			
			if(isList) l.listAll();
		} while (!quit);
		
		TodoUtil.saveList(l,"todolist");
	}
}
