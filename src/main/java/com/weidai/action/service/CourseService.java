package com.weidai.action.service;

import com.weidai.action.model.Course;
import org.springframework.stereotype.Service;


@Service
public interface CourseService {
	Course getCoursebyId(Integer courseId);


}
