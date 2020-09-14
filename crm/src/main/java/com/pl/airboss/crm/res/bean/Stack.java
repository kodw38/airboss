package com.pl.airboss.crm.res.bean;

import java.util.LinkedList;

/**
 * 
 * Copyright: Asiainfo-Linkage
 * 
 * @desc: 堆栈
 * @version: v1.0
 * @author: lvyh
 * @date: 2011.02.20
 */
public class Stack {
	LinkedList stackList = new LinkedList();

	public Stack() {

	}

	/**
	 * 入栈
	 * 
	 * @param expression
	 */
	public void push(String expression) {
		stackList.addLast(expression);
	}

	/**
	 * 出栈
	 * 
	 * @return
	 */
	public String pop() {
		return (String) stackList.removeLast();
	}

	/**
	 * 栈顶元素
	 * 
	 * @return
	 */
	public String top() {
		return (String) stackList.getLast();
	}

	/**
	 * 栈是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return stackList.isEmpty();
	}

	public int size() {
		return stackList.size();
	}
}
