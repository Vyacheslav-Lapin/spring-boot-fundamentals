package ru.vlapin.experiments.springbootfundamentals.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.experiments.springbootfundamentals.model.Cat;

public interface CatRepository extends JpaRepository<Cat, UUID> {
}
