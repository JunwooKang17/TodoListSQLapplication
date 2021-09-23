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
	
	public static void createItem(TodoList list) {
		
		String title, desc;
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
		
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.print("항목이 추가 되었습니다!\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 제목을 입력하세요 > ");
		
		String title = sc.next().trim();
		sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
			System.out.println("항목이 삭제되었습니다!");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 제목을 입력하시오 > ");
		String title = sc.next().trim();
		
		if (!l.isDuplicate(title)) {
			System.out.println("리스트에 없는 제목 입니다!");
			return;
		}

		System.out.print("새로운 제목을 입력하세요 >");
		String new_title = sc.next().trim();
		sc.nextLine();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 있는 제목입니다!\n");
			return;
		}
		
		System.out.print("새로운 내용을 입력하세요 > ");
		String new_description = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 수정되었습니다!");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
			
		}
	}
	
	public static void loadList(TodoList l, String filename) 
	{
		
		try {
			
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String oneline;
		
		int count=0;
		
		while((oneline = br.readLine()) != null) {
			
			StringTokenizer st = new StringTokenizer(oneline, "##");
			
			String title = st.nextToken();
			String desc = st.nextToken();
			String date = st.nextToken();
			
			TodoItem t = new TodoItem(title,desc,date);
			l.addItem(t);
			count++;
			
		}
		System.out.println(count+"개 정보 가져오기 완료!!");
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
	
	
}
