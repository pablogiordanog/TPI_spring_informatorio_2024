package com.informatorio.resetascocina_app.repository;

import com.informatorio.resetascocina_app.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
