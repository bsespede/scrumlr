package ar.edu.itba.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.interfaces.ProjectDao;
import ar.edu.itba.models.IterationDetail;
import ar.edu.itba.models.Project;
import ar.edu.itba.models.ProjectDetail;
import ar.edu.itba.models.ProjectStatus;
import ar.edu.itba.persistence.rowmapping.IterationDetailRowMapper;
import ar.edu.itba.persistence.rowmapping.ProjectDetailRowMapper;

@Repository
public class ProjectJdbcDao implements ProjectDao{
	
	private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    private ProjectDetailRowMapper projectDetailRowMapper;
    private IterationDetailRowMapper iterationDetailRowMapper;

    @Autowired
    public ProjectJdbcDao(final DataSource ds) {
    		projectDetailRowMapper = new ProjectDetailRowMapper();
    		iterationDetailRowMapper = new IterationDetailRowMapper();
            jdbcTemplate = new JdbcTemplate(ds);
            jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("project").usingGeneratedKeyColumns("project_id");

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS project ("
                            + "project_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,"
                            + "name varchar(100) NOT NULL,"
                            + "description varchar(500),"
                            + "date_start DATE,"
                            + "status INTEGER,"
                            + "PRIMARY KEY ( project_id, name )"
                    + ")");
    }

	@Override
	public ProjectDetail createProject(final String name, final String description) {
		final Date curDate = new Date();
		final Map<String, Object> args = new HashMap<String, Object>();
        args.put("name", name);
        args.put("description", description);
        args.put("start_date", new java.sql.Date(curDate.getTime()));
        args.put("status", ProjectStatus.OPEN.getValue());
        int projectId = jdbcInsert.executeAndReturnKey(args).intValue();

        return new ProjectDetail(projectId, name, description, curDate, ProjectStatus.OPEN);
	}
	
	@Override
	public boolean deleteProject(int projectId) {
		List<IterationDetail> projectIterations = jdbcTemplate.query("SELECT * FROM iteration WHERE project_id = ?", iterationDetailRowMapper, projectId);
		
		for (IterationDetail iteration: projectIterations){
			int itId = iteration.getIterationId();
			jdbcTemplate.update("DELETE FROM task WHERE iteration_id = ?", itId);
			jdbcTemplate.update("DELETE FROM log WHERE iteration_id = ?", itId);
			jdbcTemplate.update("DELETE FROM iteration WHERE iteration_id = ?", itId);
			// TODO falta updetear los numbers de las iteraciones en la tabla
		}
		
		return jdbcTemplate.update("DELETE FROM project WHERE project_id = ?", projectId) > 0;
	}
	
	@Override
	public List<ProjectDetail> getProjectDetailList() {
        final List<ProjectDetail> list = jdbcTemplate.query("SELECT * FROM project", projectDetailRowMapper);
        if (list.isEmpty()) {
                return null;
        }

        return list;
	}
	
	@Override
	public Project getProjectWithDetails(String projectName) {
      final List<ProjectDetail> resultRows = jdbcTemplate.query("SELECT * FROM project WHERE name = ? LIMIT 1", projectDetailRowMapper, projectName);
      if (resultRows.isEmpty()) {
              return null;
      }

      Project requestedProject = new Project(resultRows.get(0));
      
      final List<IterationDetail> iterationDetailRows = jdbcTemplate.query("SELECT * FROM iteration WHERE project_id = ?", iterationDetailRowMapper, requestedProject.getProjectDetails().getProjectId());
      
      for (IterationDetail itDetail: iterationDetailRows){
    	  requestedProject.addIteration(itDetail);
      }
      
      return requestedProject;
	}
}