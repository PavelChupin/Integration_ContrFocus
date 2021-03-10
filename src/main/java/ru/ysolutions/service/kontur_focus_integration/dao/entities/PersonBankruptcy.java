package ru.ysolutions.service.kontur_focus_integration.dao.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person_bankruptcy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonBankruptcy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "inn")
    private String inn;
    @Column(name = "fio")
    private String fio;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "snils")
    private String snils;
    @Column(name = "stage")
    private String stage;
    @Column(name = "stage_date")
    private Date stageDate;
    @Column(name = "case_number")
    private String caseNumber;
    @Column(name = "last_message_date")
    private Date lastMessageDate;


}
