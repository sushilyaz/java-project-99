package hexlet.code.service;

import hexlet.code.dto.UserCreateDTO;
import hexlet.code.dto.UserDTO;
import hexlet.code.dto.UserUpdateDTO;
import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.mapper.UserMapper;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUser() {
        var users = userRepository.findAll();
        return users.stream()
                .map(userMapper::map)
                .toList();
    }

    public UserDTO getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + "not found"));
        return userMapper.map(user);
    }

    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        User user = userMapper.map(userCreateDTO);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
        return userMapper.map(user);
    }
// $2a$10$0Pw6XuHYnFzzjNMLJX0poeULcKxBWqPxRo2CKBFLDNfCjmKRE6qeS
    public UserDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + "not found"));
        userMapper.update(userUpdateDTO, user);
        userRepository.save(user);
        return userMapper.map(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
