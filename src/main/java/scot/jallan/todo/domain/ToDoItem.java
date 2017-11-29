package scot.jallan.todo.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a ToDoItem
 *
 * @author allan
 */
@XmlRootElement
public class ToDoItem {

	private Long key;

	private String title;

	private boolean complete;

	private String description;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
