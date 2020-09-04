package com.br.eduriatest.model;

import lombok.Data;

@Data
public class EnrollmentDto {

    private Long enrollmentId;
    private Long studentId;
    private Long courseId;
    private Long qtableId;
    private StateEnum state;
    private Double score;

}