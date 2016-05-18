package ar.edu.itba.interfaces;

import java.util.List;
import java.util.Optional;

import ar.edu.itba.models.BacklogItem;
import ar.edu.itba.models.Project;

public interface BacklogDao {

	public BacklogItem createBacklogItem(final String title, final Optional<String> description, final Project project);

	public boolean backlogItemExists(final Project project, final String title);

	public boolean backlogItemExists(final BacklogItem backlogItem);
	
	public void deleteItem(final BacklogItem backlogItem);
	
	public BacklogItem updateTitle(final BacklogItem backlogItem, final String title);
	
	public BacklogItem updateDescription(final BacklogItem backlogItem, final Optional<String> description);
	
	public List<? extends BacklogItem> getBacklogForProject(final Project project);

	public Project getParent(final BacklogItem backlogItem);

	public BacklogItem getBacklogItemById(final int backlogItemId);

}
