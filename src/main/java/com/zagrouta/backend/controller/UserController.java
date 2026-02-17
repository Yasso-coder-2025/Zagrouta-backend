package com.zagrouta.backend.controller;

import com.zagrouta.backend.entity.User;
import com.zagrouta.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // بنعرف سبرينج إن ده كلاس بيستقبل طلبات ويب
@RequestMapping("/api/users") // أي رابط هيبدأ بـ /api/users هيجي هنا
public class UserController {

    private final UserService userService;

    // بنحقن الـ Service عشان نستخدم دوالها
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. Endpoint لتسجيل مستخدم جديد (POST Request)
    // الرابط هيكون: http://localhost:8080/api/users/register
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        // نتأكد الأول إن الإيميل مش مستخدم قبل كده
        if (userService.isEmailTaken(user.getEmail())) {
            return "Error: Email is already taken!";
        }
        
        userService.saveUser(user);
        return "User registered successfully!";
    }

    // 2. Endpoint تجيب بيانات يوزر معين بالإيميل (GET Request)
    // الرابط هيكون: http://localhost:8080/api/users/{email}
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email).orElse(null);
    }

    // 3. Endpoint تجيب كل اليوزرز (للتجربة بس) (GET Request)
    // الرابط هيكون: http://localhost:8080/api/users/all
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    // 4. Endpoint لتسجيل الدخول
    // الرابط: http://localhost:8080/api/users/login
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());

        if (loggedInUser != null) {
            return "Login Successful! Welcome " + loggedInUser.getFullName();
        } else {
            return "Invalid Email or Password!";
        }
    }
}