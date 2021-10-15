package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int id;
    private int is_completed;
    private int priority;
    private int late = 0;

    
    public TodoItem(String title, String desc, String category, String due_date){
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss"); 
        this.current_date= f.format(new Date());
        this.category = category;
        this.due_date = due_date;
        this.is_completed = 0;
        this.priority = 0;
        this.late = 0;

    }
    
    public TodoItem(String title, String desc, String date, String category, String due_date){
        this.title=title;
        this.desc=desc;
        current_date=date;
        this.category = category;
        this.due_date = due_date;
    }
    
    

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getCategory() {
    	return category;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    public String toString() {
    	
    	if(late == 0) {
    	
    	if(is_completed == 0) {
    		
    		if(priority == 0)
    			return id+"."+"[" + category+ "] "+title+" - "+ desc + " - " + due_date +" - "+current_date;
    		else
    			return id+"*." + " [" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
    	}else {
    		if(priority==1)
    			return id+"*." + " [" + category + "] " + title + "[V] - " + desc + " - " + due_date + " - " + current_date;
    		else
    			return id+"." + " [" + category + "] " + title + "[V] - " + desc + " - " + due_date + " - " + current_date;
    	}
    	}else {
    		if(is_completed == 0) {
        		
        		if(priority == 0)
        			return id+"."+"[" + category+ "] "+title+" - "+ desc + " - " + due_date +"[late] - "+current_date;
        		else
        			return id+"*." + " [" + category + "] " + title + " - " + desc + " - " + due_date + "[late] - " + current_date;
        	}else {
        		if(priority==1)
        			return id+"*." + " [" + category + "] " + title + "[V] - " + desc + " - " + due_date + "[late] - " + current_date;
        		else
        			return id+"." + " [" + category + "] " + title + "[V] - " + desc + " - " + due_date + "[late] - " + current_date;
        	}
        	}
    		
    	}
    	
    
    public String toSaveString() {
    	return category+"##"+ title+ "##"+ desc +"##"+due_date+ "##" + current_date;
    }

	public String getDue_date() {
		// TODO Auto-generated method stub
		return due_date;
	}
	public int getIs_completed() {
		return is_completed;
	}
	
	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public void setLate(int late) {
		this.late = late;
			
		}
	public int getLate() {
		return late;
	}
	}

