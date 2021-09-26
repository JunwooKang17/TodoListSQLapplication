package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;


public class TodoUtil {
	public static int count = 0;
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목추가]\n"
				+ "제목 > ");
		
		
		title = sc.next();
		sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.print("제목이 중복 됩니다!\n");
			return;
		}
		
		System.out.print("카테고리 > ");
		category = sc.nextLine().trim();
		
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.print("항목이 추가 되었습니다!\n");
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하세요 > ");
		int num = sc.nextInt();
		sc.nextLine();
		
		
		
		
		int i =1;
		count = 1;
		for (TodoItem item : l.getList()) {
			if (i==num) {
				System.out.println(count+". "+item.toString());
				System.out.print("위 항목을 정말 삭제 하시겠습니까? (y/n) > ");
				String w = sc.next().trim();
				switch(w) {
					case "y":
				l.deleteItem(item);
				System.out.println("항목이 삭제되었습니다!");
				break;
					default:
						break;
				}
			}
			count++;
			i++;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+"수정할 항목의 번호를 입력하시오 > ");
			int num = sc.nextInt();
			sc.nextLine();
			
				
		
		

		System.out.print("새 제목 >");
		String new_title = sc.next().trim();
		sc.nextLine();
	
		
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 있는 제목입니다!\n");
			return;
		}
		
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.nextLine().trim();
		
		System.out.print("새 마감일정 > ");
		String new_due_date = sc.nextLine().trim();
		
		int i =1;
		for (TodoItem item : l.getList()) {
			if (i==num) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description,new_category,new_due_date );
				l.addItem(t);
				System.out.println("항목이 수정되었습니다!");
			}
			i++;
		}

	}

	public static void listAll(TodoList l) {
		count = 0;
		for (TodoItem item : l.getList()) {
			count++;
		}
		System.out.println("[전체 목록], 총 "+count+"개");
			count = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(count+". "+item.toString());
			count++;
		}
		
	}
	
	public static void loadList(TodoList l, String filename) 
	{
		
		try {
			
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String oneline;
		
		int countr=0;
		
		while((oneline = br.readLine()) != null) {
			
			StringTokenizer st = new StringTokenizer(oneline, "##");
			
			String category = st.nextToken();
			String title = st.nextToken();
			String desc = st.nextToken();
			String due_date = st.nextToken();
			String date = st.nextToken();
			
			
			
			TodoItem t = new TodoItem(title,desc,date,category,due_date);
			l.addItem(t);
			countr++;
			
		}
		System.out.println(countr+"개 정보 가져오기 완료!!");
		br.close();
		
		
		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}

		
		
		
		
	}
	public static void saveList(TodoList l, String filename) 
	{
		//filewriter

		try {
			Writer w = new FileWriter(filename);
			
			
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
				w.write("\n");
			}
			w.close();
			
			System.out.println("정보 저장 완료!");
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}

	
		
	}
	public static void findTitle(TodoList l, String f_title) {
		count = 1;
		int count_c = 0;
		for(TodoItem item: l.getList()) {
		if(item.getTitle().contains(f_title)||item.getDesc().contains(f_title)){
			System.out.println(count+". "+item.toString());
			count_c++;
		}
		count++;
		}
		System.out.println("총 "+count_c+"개의 항목을 찾았습니다.");
	}
	
	
	public static void findCate(TodoList l, String f_cate) {
		count =1;
		int count_c = 0;
		for(TodoItem item: l.getList()) {
			if(item.getCategory().contains(f_cate)){
				System.out.println(count+". "+item.toString());
				count_c++;
			}
			count++;
			}
		System.out.println("총 "+count_c+"개의 항목을 찾았습니다.");
	}
	
	public static void ls_cate(TodoList l) {
		String saver = "";
		int count_c = 0;
		int i =0;
		for(TodoItem item: l.getList()) {
			if(i==0) {
				saver += item.getCategory();
				count_c++;
			}
			else if(!saver.contains(item.getCategory())) {
				saver += "/ ";
				saver += item.getCategory();	
				count_c++;
			}
			i++;
		}
		System.out.println(saver);
		System.out.println("총 "+count_c+"개의 카테고리가 등록되어 있습니다.");
	}
	
}
	
