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
import java.text.SimpleDateFormat;

import com.todo.service.TodoSortByDate;

import com.google.gson.Gson;


public class TodoUtil {
	
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
		if(list.addItem(t)>0)
			System.out.print("항목이 추가 되었습니다!\n");
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하세요 > ");
		int num = sc.nextInt();
		sc.nextLine();
		
		if(l.deleteItem(num)>0)
				System.out.println("삭제되었습니다!.");
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
				
				TodoItem t = new TodoItem(new_title, new_description,new_category,new_due_date );
				t.setId(num);
				if(l.updateItem(t)>0)
					System.out.println("항목이 수정되었습니다!");
			
			
		}

	

	public static void listAll(TodoList l) {

		
		System.out.println("[전체 목록], 총 "+l.getCount()+"개");
		
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[ 전체 목록, 총 " + l.getCount() + "개]");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	public static void comp(TodoList l, int id) {
		if(l.completeItem(id) > 0)
			System.out.println("완료 체크하였습니다.");
	}
	
	public static void set_priori(TodoList l, int id) {
		if(l.setpriority(id) > 0) {
			System.out.println("우선적으로 해야 할 일을 설정하셨습니다.");
		}
		
	}
	
	
	public static void listAll(TodoList l, int comp) {
		int count =0;
		
		for (TodoItem myitem : l.getList(comp)) {
			count++;
			System.out.println(myitem.toString());
		}
		System.out.println("총 " + count+ "개 완료!");
	}
	
	
	public static void listAllp(TodoList l, int priority) {
		int count =0;
		
		for (TodoItem myitem : l.getListp(priority)) {
			count++;
			System.out.println(myitem.toString());
		}
		System.out.println("총 " + count+ "개의 우선순위 목록!");
	}
	
	public static void listAllL(TodoList l, int late) {
		int count = 0;
		for(TodoItem myitem : l.getListL(late)) {
			count++;
			System.out.println(myitem.toString());
		}
		System.out.println("총 " + count+ "개의 지각 목록!");
	}
	
	
	public static void loadList(TodoList l, String filename) 
	{
		List<TodoItem> list = new ArrayList<TodoItem>();
		Gson gson = new Gson();
		
		String jsonstr2 = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			jsonstr2 = br.readLine();
			br.close();
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("파일에서 데이터를 가져왔습니다!");
		
		TodoItem [] array = gson.fromJson(jsonstr2,TodoItem[].class);
		List<TodoItem> list1 = Arrays.asList(array);
		/*
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
		}*/
	}
	
	
	public static void saveList(TodoList l, String filename) 
	{
		//filewriter
		List<TodoItem> list = new ArrayList<TodoItem>();
		Gson gson = new Gson();
		for(TodoItem item : l.getList()) {
			list.add(item);
		}
		String jsonstr = gson.toJson(list);
		
		
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(jsonstr);
			writer.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("정보저장 완료!");
		/*
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
		}*/
		

	}
	public static void findList(TodoList l, String f_title) {
		
		int count = 0;
		for(TodoItem item: l.getList(f_title)) {
		
			System.out.println(item.toString());
			count++;
		
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.");
	}
	
	
	public static void findCate(TodoList l, String f_cate) {
		
		int count_c = 0;
		for(TodoItem item: l.getListCategory(f_cate)) {
				System.out.println(item.toString());
				count_c++;
			}
			
			
		System.out.println("총 "+count_c+"개의 항목을 찾았습니다.");
	}
	
	public static void ls_cate(TodoList l) {
		
		int count_c = 0;
		
		for(String item: l.getCategories()) {
			
				System.out.print(item+ " ");
				count_c++;
			
		}
		
		
		System.out.println("총 "+count_c+"개의 카테고리가 등록되어 있습니다.");
	}
		
	public static void setlateness(TodoList l) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd"); 
		String time = f.format(new Date());
		for (TodoItem myitem : l.getList()) {
			if(myitem.getDue_date().compareTo(time)<0) {
				l.setlateness(myitem.getId());
			}
		}
		
	}
	
}
	
