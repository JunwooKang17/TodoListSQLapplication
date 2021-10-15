package com.todo.service;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.todo.dao.TodoItem;

public class TodoSortByDate implements Comparator<TodoItem> {
    @Override
    public int compare(TodoItem o1, TodoItem o2) {
        return o1.getCurrent_date().compareTo(o2.getCurrent_date());

    }
    
   
}
