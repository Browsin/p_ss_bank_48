package com.bank.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "new_entity_json")
    private String newEntityJSON;

    @Column(name = "entity_json")
    private String entityJSON;

}
