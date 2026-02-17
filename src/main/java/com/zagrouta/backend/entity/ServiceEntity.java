package com.zagrouta.backend.entity;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "services") // اسم الجدول في الداتابيز
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // اسم الخدمة (مثلاً: باكيدج زفاف كامل)

    @Column(columnDefinition = "TEXT") // عشان الوصف ممكن يكون طويل
    private String description;

    @Column(nullable = false)
    private Double price; // سعر الخدمة

    private String location; // المحافظة أو المنطقة

    private String imageUrl; // (اختياري) لو هنرفع صورة للخدمة مستقبلاً

    // --- علاقة الربط مع اليوزر ---
    // هنا بنقول إن الخدمة دي "بتاعة" يوزر معين
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore // 👈 دي هتمنع ظهور اليوزر في الرد وتريحنا من كل مشاكله
    @lombok.ToString.Exclude  // 👈 ودي عشان تمنع لومبوك إنه يعمل مشكلة وهو بيطبع البيانات
    private User user;
}