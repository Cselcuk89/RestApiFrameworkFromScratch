package pojo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCourse {
    private String url;
    private String services;
    private String expertise;
    private Courses Courses;
    private String instructor;
    private String linkedIn;
}
