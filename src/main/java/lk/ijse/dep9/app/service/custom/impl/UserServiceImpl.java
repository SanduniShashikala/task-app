package lk.ijse.dep9.app.service.custom.impl;

import lk.ijse.dep9.app.repository.ProjectRepository;
import lk.ijse.dep9.app.repository.TaskRepository;
import lk.ijse.dep9.app.repository.UserRepository;
import lk.ijse.dep9.app.dto.UserDTO;
import lk.ijse.dep9.app.entity.Project;
import lk.ijse.dep9.app.entity.Task;
import lk.ijse.dep9.app.exception.AuthenticationException;
import lk.ijse.dep9.app.service.custom.UserService;
import lk.ijse.dep9.app.util.Transformer;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserServiceImpl implements UserService {
    private final TaskRepository taskDAO;
    private final ProjectRepository projectDAO;
    private final UserRepository userDAO;
    private final Transformer transformer;

    public UserServiceImpl(TaskRepository taskDAO, ProjectRepository projectDAO, UserRepository userDAO, Transformer transformer) {
        this.taskDAO = taskDAO;
        this.projectDAO = projectDAO;
        this.userDAO = userDAO;
        this.transformer = transformer;
    }

    @Override
    public void createNewUserAccount(UserDTO userDTO) {
        userDTO.setPassword(DigestUtils.sha256Hex(userDTO.getPassword()));
        userDAO.save(transformer.toUser(userDTO));
    }

    @Override
    public UserDTO verifyUser(String username, String password) {
        UserDTO user = userDAO.findById(username).map(transformer::toUserDTO).orElseThrow(AuthenticationException::new);
        if (DigestUtils.sha256Hex(password).equals(user.getPassword())){
            return user;
        }
        throw new AuthenticationException();
    }

    @Override
    public UserDTO getUserAccountDetails(String username) {
        return userDAO.findById(username).map(transformer::toUserDTO).get();
    }

    @Override
    public void updateUserAccountDetails(UserDTO userDTO) {
        userDTO.setPassword(DigestUtils.sha256Hex(userDTO.getPassword()));
        userDAO.save(transformer.toUser(userDTO));
    }

    @Override
    public void deleteUserAccount(String username) {
        List<Project> projectList = projectDAO.findAllProjectsByUsername(username);
        for (Project project : projectList){
            List<Task> taskList = taskDAO.findAllTaskByProjectId(project.getId());
            taskList.forEach(task -> taskDAO.deleteById(task.getId()));
            projectDAO.deleteById(project.getId());
        }
        userDAO.deleteById(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userDAO.findById(username).map(transformer::toUserDTO).
                orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());

    }
}
