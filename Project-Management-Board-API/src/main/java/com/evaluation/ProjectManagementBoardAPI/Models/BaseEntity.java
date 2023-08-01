package com.evaluation.ProjectManagementBoardAPI.Models;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@Data
public class BaseEntity {

    Long id;
    Date createdAt;
    Date updatedAt;
}

