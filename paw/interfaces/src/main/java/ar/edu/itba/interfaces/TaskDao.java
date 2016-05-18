package ar.edu.itba.interfaces;

import java.util.List;
import java.util.Optional;

import ar.edu.itba.models.Priority;
import ar.edu.itba.models.Score;
import ar.edu.itba.models.Status;
import ar.edu.itba.models.Story;
import ar.edu.itba.models.Task;
import ar.edu.itba.models.User;

public interface TaskDao {

	public List<? extends Task> getTasksForStory(final Story story);

	public boolean taskExists(final Task task);
	
	public boolean taskExists(final Story story, final String title);

	public Task updateStatus(final Task task, final Status status);

	public Task updateOwner(final Task task, final Optional<User> user);

	public void deleteTask(final Task task);

	public Task getTaskById(final int taskId);

	public Task createTask(final Story story, final String name, final Optional<String> description, final Status status, final Optional<User> user, final Score score, final Priority priority);

	public Task updatePriority(final Task task, final Priority priority);

	public Task updateScore(final Task task, final Score score);

	public Story getParent(final Task task);

	public Task updateTitle(final Task task, final String title);

	public Task updateDescription(final Task task, final Optional<String> description);
	
}
