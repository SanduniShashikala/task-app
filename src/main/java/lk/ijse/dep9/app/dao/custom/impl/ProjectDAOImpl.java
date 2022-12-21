package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.ProjectDAO;
import lk.ijse.dep9.app.dao.util.ConnectionUtil;
import lk.ijse.dep9.app.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectDAOImpl implements ProjectDAO {
    private final JdbcTemplate jdbc;

    public ProjectDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Project save(Project project) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Project (name, username) VALUES (?, ?)");
            stm.setString(1, project.getName());
            stm.setString(2, project.getUsername());
            return stm;
        }, keyHolder);
        project.setId(keyHolder.getKey().intValue());
        return project;

    }

    @Override
    public void update(Project project) {
        jdbc.update("UPDATE Project SET name=? AND username=? WHERE id=?",
                project.getName(), project.getUsername(), project.getId());
    }

    @Override
    public void deleteById(Integer id) {
        jdbc.update("DELETE FROM Project WHERE id=?", id);
    }

    @Override
    public Optional<Project> findById(Integer id) {
        return jdbc.query("SELECT * FROM Project WHERE id=?", rst ->{
            return Optional.of(new Project(rst.getInt("id"),
                    rst.getString("name"),
                    rst.getString("username")));
        }, id);
    }

    @Override
    public List<Project> findAll() {
        return jdbc.query("SELECT * FROM Project", (rst, rowInd) ->
                new Project(rst.getInt("id"),
                    rst.getString("name"),
                    rst.getString("username"));
    }

    @Override
    public long count() {
        return jdbc.queryForObject("SELECT COUNT(id) FROM Project", Long.class);
    }

    @Override
    public boolean existById(Integer id) {
        return findById(id).isPresent();
    }

    @Override
    public List<Project> findAllProjectsByUsername(String username) {
        return jdbc.query("SELECT * FROM Project WHERE username = ?", (rst, rowIndex) ->
                new Project(rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("username")), username);
    }

}
