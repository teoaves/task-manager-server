package org.task.taskserver.repository;

import org.task.taskserver.dto.TaskDto;
import org.task.taskserver.dto.TaskSeriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TaskSeriesRepository")
public class TaskSeriesRepositoryImpl implements TaskSeriesRepository {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<TaskDto> fetchTasksSeriesCodes() {

        String sqlQuery = "select ins.id, ins.task_series_qr_code as taskSeriesCode \n" +
                "from tasks_series as ins \n";
        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(TaskDto.class));
    }

    @Override
    public List<TaskDto> fetchAvailableTasks() {

        String sqlQuery = "select i.id as id, i.name as name, i.description AS description, i.lot AS taskLot \n" +
                "from tasks as i where available = 1 \n";
        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(TaskDto.class));
    }

    @Override
    public List<TaskSeriesDto> getTaskSeriesList() {

        String sqlFromClause = "From tasks as i\n" +
                " INNER JOIN tasks_series AS ins ON i.task_series_id = ins.id \n";

        String sqlWhereClause = "WHERE i.id is not null\n";

        // Προσθέτουμε το ins.id στο GROUP BY
        String groupByClause = "GROUP BY taskSeriesCode, name, description, i.lot, ins.id ORDER BY taskSeriesCode \n";  // Προσθήκη του ins.id στο GROUP BY

        String sqlQuery = "Select i.name AS name, i.description AS description, i.lot AS taskLot, ins.id AS id,\n" +
                "ins.task_series_qr_code AS taskSeriesCode, COUNT(i.name) as tasksCount \n" +
                sqlFromClause + sqlWhereClause + groupByClause;

        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(TaskSeriesDto.class));
    }

    @Override
    public List<TaskSeriesDto> fetchTasksByTaskSeriesCode(String qrCode) {

        MapSqlParameterSource in = new MapSqlParameterSource();

        String sqlQuery = "Select i.name as name, i.lot AS taskLot, i.description AS description, ins.task_series_qr_code as qrCode, COUNT(i.name) as tasksCount \n" +
                "From tasks as i INNER JOIN tasks_series as ins ON i.task_series_id = ins.id \n" +
                "and ins.task_series_qr_code= :qrCode GROUP BY name, description, i.lot \n";  // Προσθήκη του i.lot στο GROUP BY
        in.addValue("qrCode", qrCode);
        return namedParameterJdbcTemplate.query(sqlQuery, in, new BeanPropertyRowMapper<>(TaskSeriesDto.class));
    }

    @Override
    public long createTaskSeries(TaskSeriesDto dto) {

        String sqlQueryOne = "INSERT INTO tasks_series (\n" +
                "task_series_qr_code\n" +
                ") VALUES (\n" +
                ":taskSeriesCode" +
                ")";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("taskSeriesCode", dto.getTaskSeriesCode());

        namedParameterJdbcTemplate.update(sqlQueryOne, in, keyHolder);

        if (dto.getConnectedTasksIds().size() == 0) {
            return keyHolder.getKey().longValue();  // Επιστροφή του ID της νέας σειράς εργασιών
        }

        String sqlQueryTwo = "UPDATE tasks SET \n " +
                "task_series_id = :keyholder,\n " +
                "available = 0 \n " +
                "WHERE tasks.id in (:connectedtasksIds)";

        MapSqlParameterSource in2 = new MapSqlParameterSource();
        in2.addValue("keyholder", keyHolder.getKey());
        in2.addValue("connectedtasksIds", dto.getConnectedTasksIds());

        namedParameterJdbcTemplate.update(sqlQueryTwo, in2);

        return keyHolder.getKey().longValue();  // Επιστροφή του ID της νέας σειράς εργασιών
    }

    @Override
    public boolean updateTaskSeries(TaskSeriesDto taskSeriesDto) {

        String sqlQueryOne = "";
        String sqlQueryTwo = "";
        String sqlQueryThree = "";
        String sqlQueryFour = "";

        if (taskSeriesDto.getConnectedTasksIds().size() > 0 && taskSeriesDto.getUnconnectedTasksIds().size() > 0) {

            sqlQueryOne = "UPDATE tasks SET \n " +
                    "task_series_id = null, \n " +
                    "available = 1 \n " +
                    "WHERE tasks.id in (:unconnectedtasksIds)";

            MapSqlParameterSource in = new MapSqlParameterSource();
            in.addValue("unconnectedtasksIds", taskSeriesDto.getUnconnectedTasksIds());

            namedParameterJdbcTemplate.update(sqlQueryOne, in);

            sqlQueryTwo = "UPDATE tasks SET \n " +
                    "task_series_id = :id, \n " +
                    "available = 0 \n " +
                    "WHERE tasks.id in (:connectedtasksIds)";

            MapSqlParameterSource in2 = new MapSqlParameterSource();
            in2.addValue("id", taskSeriesDto.getId());
            in2.addValue("connectedtasksIds", taskSeriesDto.getConnectedTasksIds());

            return namedParameterJdbcTemplate.update(sqlQueryTwo, in2) > 0;

        } else if (taskSeriesDto.getConnectedTasksIds().size() == 0) {
            sqlQueryThree = "UPDATE tasks SET \n " +
                    "task_series_id = null, \n " +
                    "available = 1 \n " +
                    "WHERE tasks.task_series_id = :id";

            MapSqlParameterSource in = new MapSqlParameterSource();
            in.addValue("id", taskSeriesDto.getId());
            return namedParameterJdbcTemplate.update(sqlQueryThree, in) > 0;
        } else {
            sqlQueryFour = "UPDATE tasks SET \n " +
                    "task_series_id = :id, \n" +
                    "available = 0 \n " +
                    "WHERE tasks.task_series_id IS NULL";

            MapSqlParameterSource in = new MapSqlParameterSource();
            in.addValue("id", taskSeriesDto.getId());
            return namedParameterJdbcTemplate.update(sqlQueryFour, in) > 0;
        }
    }

    @Override
    public List<TaskDto> getTaskSeriesById(long id) {

        String sqlQuery = "SELECT i.id as id, i.name as name, i.description AS description, i.lot AS taskLot, ins.task_series_qr_code as taskSeriesCode \n" +
                "FROM tasks as i \n" +
                "INNER JOIN tasks_series as ins on i.task_series_id = ins.id AND ins.id = :id";

        MapSqlParameterSource in = new MapSqlParameterSource("id", id);

        List<TaskDto> taskSeries = namedParameterJdbcTemplate.query(sqlQuery, in, new BeanPropertyRowMapper<>(TaskDto.class));

        if(taskSeries.isEmpty()) {
            String sqlQueryTwo = "select task_series_qr_code as taskSeriesCode from tasks_series where id = :id";
            return namedParameterJdbcTemplate.query(sqlQueryTwo, in, new BeanPropertyRowMapper<>(TaskDto.class));
        }

        return taskSeries;
    }

    @Override
    public boolean deleteTaskSeries(Long id) {
        MapSqlParameterSource in = new MapSqlParameterSource("id", id);
        String sqlQuery = "";
        String sqlQueryTwo = "";

        sqlQuery = "UPDATE tasks SET \n " +
                "task_series_id = null, \n " +
                "available = 1 \n " +
                "WHERE tasks.task_series_id = :id";

        namedParameterJdbcTemplate.update(sqlQuery, in);

        sqlQueryTwo = "DELETE FROM tasks_series WHERE id = :id";

        return namedParameterJdbcTemplate.update(sqlQueryTwo, in) > 0;
    }
}
