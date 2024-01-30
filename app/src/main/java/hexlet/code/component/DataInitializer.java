package hexlet.code.component;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    private final Map<String, String> taskStatuses = Map.of(
            "Draft", "draft",
            "ToReview", "to_review",
            "ToBeFixed", "to_be_fixed",
            "ToPublish", "to_publish",
            "Published", "published");

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var email = "hexlet@example.com";

        if (userRepository.findByEmail(email).isEmpty()) {
            var userData = new UserCreateDTO();
            userData.setFirstName("Main");
            userData.setLastName("Admin");
            userData.setEmail(email);
            userData.setPassword("qwerty");
            userService.createUser(userData);
        } else {
            System.out.println("User with email " + email + " already exists.");
        }

        var taskStatusNames = taskStatuses.keySet();
        for (String name : taskStatusNames) {
            String slug = taskStatuses.get(name);
            if (taskStatusRepository.findBySlug(slug).isEmpty()) {
                var data = new TaskStatus();
                data.setName(name);
                data.setSlug(slug);
                taskStatusRepository.save(data);
            } else {
                System.out.println("Task with slug " + slug + " already exists.");
            }
        }
    }
}

