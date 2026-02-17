package com.zagrouta.backend.service;

import com.zagrouta.backend.entity.ServiceEntity; // انتبه: بنستخدم الـ Entity بتاعتنا
import com.zagrouta.backend.entity.User;
import com.zagrouta.backend.repository.ServiceRepository;
import com.zagrouta.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository; // محتاجين ده عشان نجيب اليوزر صاحب الخدمة

    public ServiceService(ServiceRepository serviceRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    // 1. إضافة خدمة جديدة (لازم نعرف مين صاحبها بالـ ID)
    public ServiceEntity addService(ServiceEntity service, Long userId) {
        // الأول نتأكد إن اليوزر ده موجود
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            service.setUser(user); // ربطنا الخدمة باليوزر
            return serviceRepository.save(service); // حفظنا الخدمة
        } else {
            return null; // لو اليوزر مش موجود، منسجلش حاجة
        }
    }

    // 2. هاتلي كل الخدمات (عشان الصفحة الرئيسية)
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    // 3. هاتلي خدمات فيندور معين (لما ندخل بروفايله)
    public List<ServiceEntity> getServicesByVendor(Long vendorId) {
        return serviceRepository.findAllByUserId(vendorId);
    }
}