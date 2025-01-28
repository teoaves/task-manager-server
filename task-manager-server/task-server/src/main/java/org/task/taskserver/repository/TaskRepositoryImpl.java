package org.task.taskserver.repository;

import org.task.taskserver.dto.SortField;
import org.task.taskserver.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository("TaskRepository")
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<TaskDto> fetchTasks() {

        String sqlQuery = "select distinct(i.name) as name \n" +
                "from tasks as i\n" +
                "order by i.name asc";
        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(TaskDto.class));
    }

    @Override
    public List<TaskDto> fetchTasksSeriesCodes() {

        String sqlQuery = "select ins.id as id, ins.task_series_qr_code as taskSeriesCode \n" +
                "from tasks_series as ins \n";
        return namedParameterJdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(TaskDto.class));
    }

    @Override
    public Page<TaskDto> getTasksList(Pageable pageable, TaskDto dto) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String sqlFromClause = "From tasks as i\n" +
                "left join tasks_series as os on i.task_series_id = os.id\n";

        String sqlWhereClause = "WHERE i.id is not null\n";

        MapSqlParameterSource in = new MapSqlParameterSource();

        if (dto.getName() != null && !dto.getName().isEmpty()) {
            sqlWhereClause += "AND i.name like :name\n";
            in.addValue("name", "%" + dto.getName() + "%");
        }

        if (dto.getPurchaseDateFrom() != null) {
            sqlWhereClause += "AND i.purchase_date >= :purchaseDateFrom ";
            in.addValue("purchaseDateFrom", formatter.format(dto.getPurchaseDateFrom()));
        }

        if (dto.getPurchaseDateTo() != null) {
            sqlWhereClause += "AND i.purchase_date <= :purchaseDateTo ";
            in.addValue("purchaseDateTo", formatter.format(dto.getPurchaseDateTo()));
        }

        if (dto.getTaskSeriesCodesList() != null) {
            sqlWhereClause += "AND i.task_series_id in ( :taskSeriesCodesList)";
            in.addValue("taskSeriesCodesList", dto.getTaskSeriesCodesList());
        }

        List<String> validSortColumns = new ArrayList<String>();
        validSortColumns.add("TASK_NAME");
        validSortColumns.add("TASK_REF");
        validSortColumns.add("TASK_LOT");
        validSortColumns.add("TASK_MANUFACTURER");
        validSortColumns.add("TASK_PURCHASE_DATE");
        validSortColumns.add("TASK_SERIES_CODE");

        List<Sort.Order> sortOrders = pageable.getSort().stream().collect(Collectors.toList());
        Sort.Order order = sortOrders.get(0);

        String sqlPaginationClause = "ORDER BY ";

        if (validSortColumns.contains(order.getProperty())) {
            sqlPaginationClause += SortField.Field.valueOf(order.getProperty()).getValue();
            if (order.getDirection().isAscending()) {
                sqlPaginationClause += " ASC ";
            } else {
                sqlPaginationClause += " DESC ";
            }
        }

        sqlPaginationClause += "limit :offset,:size";

        in.addValue("offset", pageable.getOffset());
        in.addValue("size", pageable.getPageSize());

        String rowCountSql = "SELECT count(*) AS row_count " +
                sqlFromClause + sqlWhereClause;
        ;

        int total = this.namedParameterJdbcTemplate.queryForObject(
                rowCountSql, in, Integer.class);

        String sqlQuery = "Select i.id as id, i.name as name, i.description as description,\n" +
                "i.ref as taskRef, i.lot as taskLot, i.manufacturer as taskManufacturer,\n" +
                "i.purchase_date as taskPurchaseDate, i.notes as taskNotes,\n" +
                "os.task_series_qr_code as taskSeriesQrCode \n" +
                sqlFromClause + sqlWhereClause + sqlPaginationClause;

        List<TaskDto> res = namedParameterJdbcTemplate.query(sqlQuery, in, new BeanPropertyRowMapper<>(TaskDto.class));

        return new PageImpl<>(res, pageable, total);
    }


    @Override
    public long createTask(TaskDto taskDto) {

        String sqlQuery = " INSERT INTO tasks (\n" +
                "name,\n" +
                "description,\n" +
                "ref,\n" +
                "lot,\n" +
                "manufacturer,\n" +
                "purchase_date,\n" +
                "notes,\n" +
                "available\n" +
                ") VALUES (\n" +
                ":name,\n" +
                ":description,\n" +
                ":taskRef,\n" +
                ":taskLot,\n" +
                ":taskManufacturer,\n" +
                ":taskPurchaseDate,\n" +
                ":taskNotes,\n" +
                "1" +
                ")";

        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("name", taskDto.getName());
        in.addValue("description", taskDto.getDescription());
        in.addValue("taskRef", taskDto.getTaskRef());
        in.addValue("taskLot", taskDto.getTaskLot());
        in.addValue("taskLot", taskDto.getTaskLot());
        in.addValue("taskManufacturer", taskDto.getTaskManufacturer());
        in.addValue("taskPurchaseDate", taskDto.getTaskPurchaseDate());
        in.addValue("taskNotes", taskDto.getTaskNotes());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlQuery, in, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();

    }

    @Override
    public boolean updateTask(TaskDto taskDto) {

        String sqlQuery = "UPDATE tasks SET\n " +
                "name = :name,\n " +
                "description = :description,\n " +
                "ref = :taskRef,\n " +
                "lot = :taskLot,\n " +
                "manufacturer = :taskManufacturer,\n " +
                "purchase_date = :taskPurchaseDate,\n " +
                "notes = :taskNotes\n " +
                "WHERE id = :id";

        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", taskDto.getId());
        in.addValue("name", taskDto.getName());
        in.addValue("description", taskDto.getDescription());
        in.addValue("taskRef", taskDto.getTaskRef());
        in.addValue("taskLot", taskDto.getTaskLot());
        in.addValue("taskManufacturer", taskDto.getTaskManufacturer());
        in.addValue("taskPurchaseDate", taskDto.getTaskPurchaseDate());
        in.addValue("taskNotes", taskDto.getTaskNotes());

        return namedParameterJdbcTemplate.update(sqlQuery, in) > 0;
    }

    @Override
    public TaskDto getTaskById(long id) {

        String sqlQuery = "SELECT i.id as id,\n" +
                "i.name as name,\n" +
                "i.description as description,\n" +
                "i.ref as taskRef,\n" +
                "i.lot as taskLot,\n" +
                "i.manufacturer as taskManufacturer,\n" +
                "i.purchase_date as taskPurchaseDate,\n" +
                "i.notes as taskNotes \n" +
                "FROM tasks AS i\n" +
                "WHERE i.id = :taskId";

        MapSqlParameterSource in = new MapSqlParameterSource("taskId", id);

        return namedParameterJdbcTemplate.queryForObject(sqlQuery, in, (resultSet, i) -> {

            TaskDto taskDto = new TaskDto();
            taskDto.setId(resultSet.getLong("id"));
            taskDto.setName(resultSet.getNString("name"));
            taskDto.setDescription(resultSet.getNString("description"));
            taskDto.setTaskRef(resultSet.getNString("taskRef"));
            taskDto.setTaskLot(resultSet.getNString("taskLot"));
            taskDto.setTaskManufacturer(resultSet.getNString("taskManufacturer"));
            taskDto.setTaskPurchaseDate(resultSet.getDate("taskPurchaseDate"));
            taskDto.setTaskNotes(resultSet.getNString("taskNotes"));

            return taskDto;
        });
    }
    @Override
    public boolean deleteTask(Long id) {
        MapSqlParameterSource in = new MapSqlParameterSource("id", id);
        String sqlQuery = "DELETE FROM tasks WHERE id = :id";
        return namedParameterJdbcTemplate.update(sqlQuery, in) > 0;
    }
}
