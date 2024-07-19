package ru.gb.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.demo.model.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,Long> {

}
