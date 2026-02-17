package com.zagrouta.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zagrouta.backend.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // دالة للبحث عن المستخدم عن طريق البريد الإلكتروني (مهمة عشان الـ Login)
    Optional<User> findByEmail(String email);

    // دالة للتأكد هل البريد الإلكتروني موجود مسبقاً ولا لأ (عشان الـ Register)
    Boolean existsByEmail(String email);
    
    // لو حبيت تجيب المستخدمين بناءً على دورهم (مثلاً هاتلي كل مقدمي الخدمات)
    // List<User> findByRole(String role);
    
}