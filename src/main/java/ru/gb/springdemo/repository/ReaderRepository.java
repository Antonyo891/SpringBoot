package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,Long> {

}
