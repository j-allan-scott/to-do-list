package scot.jallan.todo.service;

import static javax.ejb.LockType.READ;

import java.util.List;

import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import scot.jallan.todo.domain.ToDoItem;
import scot.jallan.todo.domain.ToDoList;

@Path("/to-do")
@Singleton
@Lock(READ)
public class ToDoService {

	private ToDoList todoList = new ToDoList();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ToDoItem> list() {
		return todoList.getItems();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<ToDoItem> create(@FormParam("complete") boolean complete,
			@FormParam("title") String title,
			@FormParam("description") String description) {
		ToDoItem item = new ToDoItem();
		item.setComplete(complete);
		item.setTitle(title);
		item.setDescription(description);
		return todoList.addItem(item);
	}

	@POST
	@Path("/{index}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<ToDoItem> update(@PathParam("index") int index,
			@FormParam("key") long key,
			@FormParam("complete") boolean complete,
			@FormParam("title") String title,
			@FormParam("description") String description) {
		ToDoItem item = new ToDoItem();
		item.setKey(key);
		item.setComplete(complete);
		item.setTitle(title);
		item.setDescription(description);
		return todoList.updateItem(index, item);
	}

	@POST
	@Path("/{index}/move-up")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ToDoItem> moveUp(@PathParam("index") int index) {
		return todoList.moveUp(index);
	}

	@POST
	@Path("/{index}/move-down")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ToDoItem> moveDown(@PathParam("index") int index) {
		return todoList.moveDown(index);
	}

	@DELETE
	@Path("/{index}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ToDoItem> delete(@PathParam("index") int index) {
		return todoList.deleteItem(index);
	}
}