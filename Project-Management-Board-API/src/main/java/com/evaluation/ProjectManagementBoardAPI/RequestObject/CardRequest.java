package com.evaluation.ProjectManagementBoardAPI.RequestObject;

import lombok.Builder;

import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class CardRequest {

     String title;
     String description;
     Integer section;
}
