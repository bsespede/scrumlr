package ar.edu.itba.persistence;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.interfaces.ProjectDao;
import ar.edu.itba.models.Project;

@Primary
@Repository
public class ProjectHibernateDao implements ProjectDao{

	@PersistenceContext
    private EntityManager em;
	
	@Override
	@Transactional
	public boolean projectExists(Project project) {
		final TypedQuery<Long> query = em.createQuery("select count(*) from Project project where project = :project", Long.class);
        query.setParameter("project", project);
        return query.getSingleResult() > 0;
	}

	@Override
	@Transactional
	public boolean projectNameExists(String name) {
		final TypedQuery<Long> query = em.createQuery("select count(*) from Project project where project.name = :name", Long.class);
        query.setParameter("name", name);
        return query.getSingleResult() > 0;
	}

	@Override
	@Transactional
	public Project createProject(String title, String description, String code) {
		final Project persistableProject = Project.builder()
				.name(title)
				.description(description)
				.code(code)
				.startDate(LocalDate.now())
				.build();
		em.persist(persistableProject);
		em.flush();
		return persistableProject;
	}

	@Override
	@Transactional
	public boolean projectCodeExists(String code) {
		final TypedQuery<Long> query = em.createQuery("select count(*) from Project project where project.code = :code", Long.class);
        query.setParameter("code", code);
        return query.getSingleResult() > 0;
	}

	@Override
	@Transactional
	public void deleteProject(Project project) {
		final Query query = em.createQuery("delete from Project where projectId = :projectId");
		query.setParameter("projectId", project.projectId());
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void updateName(Project project, String name) {
		final Query query = em.createQuery("update Project set name = :name where projectId = :projectId");
		query.setParameter("projectId", project.projectId());
		query.setParameter("name", name);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void updateDescription(Project project, String description) {
		final Query query = em.createQuery("update Project set description = :description where projectId = :projectId");
		query.setParameter("projectId", project.projectId());
		query.setParameter("description", description);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void updateCode(Project project, String code) {
		final Query query = em.createQuery("update Project set code = :code where projectId = :projectId");
		query.setParameter("projectId", project.projectId());
		query.setParameter("code", code);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public Project getProjectById(int projectId) {
		final TypedQuery<Project> query = em.createQuery("from Project project where project.projectId = :projectId", Project.class);
        query.setParameter("projectId", projectId);
        return query.getSingleResult();
	}

	@Override
	@Transactional
	public List<Project> getProjects() {
		final TypedQuery<Project> query = em.createQuery("from Project", Project.class);
        return query.getResultList();
	}

	@Override
	@Transactional
	public Project getProjectByCode(String code) {
		final TypedQuery<Project> query = em.createQuery("from Project project where project.code = :code", Project.class);
        query.setParameter("code", code);
        return query.getSingleResult();
	}

}
