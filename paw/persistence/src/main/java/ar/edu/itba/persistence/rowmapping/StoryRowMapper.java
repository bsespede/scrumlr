package ar.edu.itba.persistence.rowmapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ar.edu.itba.models.PersistableStory;
import ar.edu.itba.models.Story;

public class StoryRowMapper implements RowMapper<Story> {

    @Override
    public Story mapRow(final ResultSet rs, final int rowNum) throws SQLException {

            return PersistableStory.builder()
            		.storyId(rs.getInt("story_id"))
            		.title(rs.getString("title"))
            		.build();
    }
}
