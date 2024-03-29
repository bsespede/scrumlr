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

import ar.edu.itba.interfaces.dao.ProjectDao;
import ar.edu.itba.models.Project;
import ar.edu.itba.models.ProjectUser;
import ar.edu.itba.models.User;

@Primary
@Repository
public class ProjectHibernateDao implements ProjectDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public boolean projectExists(Project project) {
		try {
			final TypedQuery<Long> query = em.createQuery("select count(*) from Project project where project = :project", Long.class);
			query.setParameter("project", project);
			return query.getSingleResult() > 0;
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to check project exists");
		}	
	}

	@Override
	@Transactional
	public boolean projectNameExists(String name) {
		try {
			final TypedQuery<Long> query = em.createQuery("select count(*) from Project project where project.name = :name", Long.class);
			query.setParameter("name", name);
			return query.getSingleResult() > 0;
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to check project name is free");
		}	
	}

	@Override
	@Transactional
	public Project createProject(User admin, String title, String description, String code) {
		try {
			final Project persistableProject = Project.builder()
					.name(title)
					.description(description)
					.code(code)
					.startDate(LocalDate.now())
					.admin(admin)
					.build();
			em.persist(persistableProject);
			em.flush();
			return persistableProject;
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to create project");
		}	
	}

	@Override
	@Transactional
	public boolean projectCodeExists(String code) {
		try {
			final TypedQuery<Long> query = em.createQuery("select count(*) from Project project where project.code = :code", Long.class);
			query.setParameter("code", code);
			return query.getSingleResult() > 0;
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to check project code is used");
		}	
	}

	@Override
	@Transactional
	public void deleteProject(Project project) {
		try {
			final Query query = em.createQuery("delete from Project where projectId = :projectId");
			query.setParameter("projectId", project.projectId());
			query.executeUpdate();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to delete project");
		}	
	}

	@Override
	@Transactional
	public void deleteProjectUser(Project project) {
		try {
			final Query query = em.createQuery("delete from ProjectUser where project = :project");
			query.setParameter("project", project);
			query.executeUpdate();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to delete projectuser");
		}	
	}

	@Override
	@Transactional
	public void updateName(Project project, String name) {
		try {
			final Query query = em.createQuery("update Project set name = :name where projectId = :projectId");
			query.setParameter("projectId", project.projectId());
			query.setParameter("name", name);
			query.executeUpdate();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to update project name");
		}	
	}

	@Override
	@Transactional
	public void updateDescription(Project project, String description) {
		try {
			final Query query = em.createQuery("update Project set description = :description where projectId = :projectId");
			query.setParameter("projectId", project.projectId());
			query.setParameter("description", description);
			query.executeUpdate();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to update project description");
		}	
	}

	@Override
	@Transactional
	public void updateCode(Project project, String code) {
		try {
			final Query query = em.createQuery("update Project set code = :code where projectId = :projectId");
			query.setParameter("projectId", project.projectId());
			query.setParameter("code", code);
			query.executeUpdate();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to get update project code");
		}	
	}

	@Override
	@Transactional
	public Project getProjectById(int projectId) {
		try {
			final TypedQuery<Project> query = em.createQuery("from Project project where project.projectId = :projectId", Project.class);
			query.setParameter("projectId", projectId);
			return query.getSingleResult();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to get project by id");
		}	
	}

	@Override
	@Transactional
	public List<Project> getProjectsForUser(User user) {
		try {
			final TypedQuery<Project> query = em.createQuery("select projectUser.project from ProjectUser projectUser where projectUser.user = :user", Project.class);
			query.setParameter("user", user);
			return query.getResultList();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to get projects list");
		}	
	}

	@Override
	@Transactional
	public Project getProjectByCode(String code) {
		try {
			final TypedQuery<Project> query = em.createQuery("from Project project where project.code = :code", Project.class);
			query.setParameter("code", code);
			return query.getSingleResult();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to get project by code");
		}	
	}

	@Override
	@Transactional
	public void addProjectMember(Project project, User user) {
		try {
			final ProjectUser projectUser = ProjectUser.builder()
					.project(project)
					.user(user)
					.build();
			em.persist(projectUser);
			em.flush();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to add project member");
		}	
	}

	@Override
	@Transactional
	public List<User> getProjectMembers(Project project) {
		try {
			final TypedQuery<User> query = em.createQuery("select pu.user from ProjectUser pu where pu.project = :project", User.class);
			query.setParameter("project", project);
			return query.getResultList();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to get project members");
		}
	}

	@Override
	@Transactional
	public void deleteProjectMember(Project project, User userToDelete) {
		try {
			final Query query = em.createQuery("delete from ProjectUser where project = :project and user = :user");
			query.setParameter("project", project);
			query.setParameter("user", userToDelete);
			query.executeUpdate();
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to delete user from project");
		}
	}

	@Override
	@Transactional
	public boolean userBelongsToProject(Project project, User user) {
		try {
			final TypedQuery<Long> query = em.createQuery("select count(*) from ProjectUser pu where pu.project = :project and pu.user = :user", Long.class);
			query.setParameter("project", project);
			query.setParameter("user", user);
			return query.getSingleResult() > 0;
		} catch (Exception exception) {
			throw new IllegalStateException("Database failed to check user belongs to project");
		}	
	}

}
