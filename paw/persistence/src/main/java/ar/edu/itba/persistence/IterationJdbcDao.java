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

import ar.edu.itba.interfaces.IterationDao;
import ar.edu.itba.models.Iteration;
import ar.edu.itba.models.IterationDetail;
import ar.edu.itba.models.Task;
import ar.edu.itba.persistence.rowmapping.IterationDetailRowMapper;
import ar.edu.itba.persistence.rowmapping.TaskUserRowMapper;

@Repository
public class IterationJdbcDao implements IterationDao {

	private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;
    private IterationDetailRowMapper iterationDetailRowMapper;
    private TaskUserRowMapper taskUserRowMapper;
    
    @Autowired
    public IterationJdbcDao(final DataSource ds) {
    		iterationDetailRowMapper = new IterationDetailRowMapper();
    		taskUserRowMapper = new TaskUserRowMapper();
            jdbcTemplate = new JdbcTemplate(ds);
            jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("iteration").usingGeneratedKeyColumns("iteration_id");

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS iteration ("
            				+ "iteration_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,"
                            + "project_id INTEGER NOT NULL,"
                            + "number INTEGER,"
                            + "date_start DATE,"
                            + "date_end DATE,"
                            + "PRIMARY KEY ( iteration_id ),"
                            + "FOREIGN KEY ( project_id ) REFERENCES project ( project_id )"
                    + ")");
    }
    
	@Override
	public IterationDetail createIteration(int projectId, Date beginDate, Date endDate) {
		final int itNumber = getCurIterationNumberForProject(projectId);
		
		final Map<String, Object> args = new HashMap<String, Object>();
		args.put("project_id", projectId);
        args.put("number", itNumber+1);
        args.put("date_start", new java.sql.Date(beginDate.getTime()));
        args.put("date_end", new java.sql.Date(endDate.getTime()));
        jdbcInsert.execute(args);
		
		return new IterationDetail(projectId, itNumber+1, beginDate, endDate);
	}

	private int getCurIterationNumberForProject(int projectId){
		return jdbcTemplate.queryForObject("SELECT MAX(number) FROM iteration WHERE project_id = "+ projectId, Integer.class);
	}
	
	@Override
	public boolean deleteIteration(int iterationId) {
		boolean hasEntriesToDelete = jdbcTemplate.update("DELETE FROM log WHERE iteration_id = ?", iterationId) > 0;
		
		if (hasEntriesToDelete) {
			jdbcTemplate.update("DELETE FROM task WHERE iteration_id = ?", iterationId);
			jdbcTemplate.update("DELETE FROM log WHERE iteration_id = ?", iterationId);			
			// TODO falta updetear los numbers de las iteraciones en la tabla
		}		

		return hasEntriesToDelete;
	}

	@Override
	public Iteration getIterationById(int iterationId) {
		List<IterationDetail> detailList = jdbcTemplate.query("SELECT * FROM iteration WHERE iteration_id = ?", iterationDetailRowMapper, iterationId);
		
		if (detailList.isEmpty()) {
			return null;
		}
		
		Iteration requestedIteration = new Iteration(detailList.get(0));
		
		List<Task> taskList = jdbcTemplate.query("SELECT * FROM task INNER JOIN iteration "
				+ "ON task.iteration_id = iteration.iteration_id WHERE iterationId = ?", taskUserRowMapper, iterationId);
		
		for (Task task: taskList) {
			requestedIteration.addTask(task);
		}
		
		// TODO Falta agregarle los logs
		
		return requestedIteration;
	}
}
