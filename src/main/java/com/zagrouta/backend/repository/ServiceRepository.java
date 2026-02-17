package com.zagrouta.backend.repository;

import com.zagrouta.backend.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    // الحركة الصايعة: هاتلي كل الخدمات اللي الـ User ID بتاعها كذا
    // دي مهمة جداً عشان نعرض خدمات كل فيندور لوحده
    List<ServiceEntity> findAllByUserId(Long userId);
    
    // (ممكن قدام نحتاج بحث بالاسم)
    // List<ServiceEntity> findByNameContaining(String name);
}