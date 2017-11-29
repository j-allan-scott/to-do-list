package scot.jallan.todo.domain;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Thread safe, in-memory representation of a list of to do list items.
 *
 * @author allan
 *
 */
public class ToDoList {

	private volatile long keyCounter = 0;

	private ArrayList<ToDoItem> items = new ArrayList<>();

	public synchronized List<ToDoItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public synchronized List<ToDoItem> addItem(ToDoItem item) {
		item.setKey(keyCounter++);
		items.add(item);
		return getItems();
	}

	public synchronized List<ToDoItem> updateItem(int index, ToDoItem item) {
		if (index < 0 || index >= items.size()) {
			throw new IllegalArgumentException("Index out of range");
		}

		items.set(index, item);
		return getItems();
	}

	public synchronized List<ToDoItem> moveUp(int index) {
		if (index < 1 || index > items.size()) {
			throw new IllegalArgumentException("Index out of range");
		}

		ToDoItem item = items.get(index);
		ToDoItem swap = items.get(index - 1);

		items.set(index - 1, item);
		items.set(index, swap);

		return getItems();
	}

	public synchronized List<ToDoItem> moveDown(int index) {
		if (index < 0 || index > (items.size() - 1)) {
			throw new IllegalArgumentException("Index out of range");
		}

		ToDoItem item = items.get(index);
		ToDoItem swap = items.get(index + 1);

		items.set(index + 1, item);
		items.set(index, swap);

		return getItems();
	}

	public synchronized List<ToDoItem> deleteItem(int index) {
		if (index < 0 || index >= items.size()) {
			throw new IllegalArgumentException("Index out of range");
		}

		items.remove(index);
		return getItems();
	}
}
