package com.pl.airboss.crm.res.bean;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * Copyright: Asiainfo-Linkage
 * 
 * @desc: 队列
 * @version: v1.0
 * @author: lvyh
 * @date: 2011.02.20
 */
public class Queue {
	private LinkedList queue = new LinkedList();

	public Object get(int index) {
		return queue.get(index);
	}

	public int size() {
		return queue.size();
	}

	public void put(Object o) {
		queue.addLast(o);
	}

	// 使用removeFirst()方法，返回队列中第一个数据，然后将它从队列中删除
	public Object remove() {
		return queue.removeFirst();
	}

	// 读第一个对头元素，但不删除
	public Object front() {
		return queue.getFirst();
	}

	public Iterator iterator() {
		return queue.iterator();
	}

	public boolean empty() {
		return queue.isEmpty();
	}

}
