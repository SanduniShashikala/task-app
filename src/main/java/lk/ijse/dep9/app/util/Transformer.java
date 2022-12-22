package lk.ijse.dep9.app.util;

import lk.ijse.dep9.app.dto.ProjectDTO;
import lk.ijse.dep9.app.dto.TaskDTO;
import lk.ijse.dep9.app.dto.UserDTO;
import lk.ijse.dep9.app.entity.Project;
import lk.ijse.dep9.app.entity.Task;
import lk.ijse.dep9.app.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Transformer {
    private final ModelMapper mapper;

    public Transformer(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User toUser(UserDTO dto){
        return mapper.map(dto, User.class);
    }
    public UserDTO toUserDTO(User user){return mapper.map(user, UserDTO.class);}

    public Project toProject(ProjectDTO dto){
        return mapper.map(dto, Project.class);
    }
    public ProjectDTO toProjectDTO(Project projectEntity){
        return mapper.map(projectEntity, ProjectDTO.class);
    }

    public Task toTask(TaskDTO dto){
        return mapper.map(dto, Task.class);
    }
    public TaskDTO toTaskDTO(Task taskEntity){
        mapper.typeMap(Task.class, TaskDTO.class)
                .addMapping(Task::getStatus,TaskDTO::setCompleted);
        mapper.typeMap(Task.Status.class, Boolean.class).setConverter(pr -> pr.getSource() == Task.Status.COMPLETED);
        return mapper.map(taskEntity, TaskDTO.class);
    }


}
