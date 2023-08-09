package com.evaluation.ProjectManagementBoardAPI.Models;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.MappedSuperclass;
import java.util.Date;


@Getter
@Setter
@Data
public class BaseEntity {

    Long id;
    Date createdAt;
    Date updatedAt;


}

