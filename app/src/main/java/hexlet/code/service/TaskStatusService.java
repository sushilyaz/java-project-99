package hexlet.code.service;

import hexlet.code.dto.TaskStatusCreateDTO;
import hexlet.code.dto.TaskStatusDTO;
import hexlet.code.dto.TaskStatusUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskStatusService {

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TaskStatusMapper taskStatusMapper;

    public List<TaskStatusDTO> getAllTasks() {
        var tasks = taskStatusRepository.findAll();
        return tasks.stream()
                .map(o -> taskStatusMapper.map(o))
                .toList();
    }

    public TaskStatusDTO getTaskById(Long id) {
        var task = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        return taskStatusMapper.map(task);
    }

    public TaskStatusDTO getTaskBySlug(String slug) {
        var task = taskStatusRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Task with slug " + slug + " not found"));
        return taskStatusMapper.map(task);
    }

    public TaskStatusDTO create(TaskStatusCreateDTO dto) {
        TaskStatus taskStatus = taskStatusMapper.map(dto);
        taskStatusRepository.save(taskStatus);
        return taskStatusMapper.map(taskStatus);
    }

    public TaskStatusDTO update(TaskStatusUpdateDTO dto, Long id) {
        var task = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        taskStatusMapper.update(dto, task);
        taskStatusRepository.save(task);
        return taskStatusMapper.map(task);
    }

    public void deleteTask(Long id) {
        taskStatusRepository.deleteById(id);
    }
}
