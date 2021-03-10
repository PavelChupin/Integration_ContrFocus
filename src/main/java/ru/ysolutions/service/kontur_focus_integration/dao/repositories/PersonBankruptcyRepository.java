package ru.ysolutions.service.kontur_focus_integration.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ysolutions.service.kontur_focus_integration.dao.entities.PersonBankruptcy;

@Repository
public interface PersonBankruptcyRepository extends JpaRepository<PersonBankruptcy,Long> {

}
