package com.exam.models.portal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter@Setter@EqualsAndHashCode
public class StudentScoreId  implements Serializable {
    private Long student;
    private Long quiz;
}
