package com.weidai.action.service;

import com.weidai.action.model.BaseObject;
import com.weidai.action.model.Course;
import org.springframework.stereotype.Service;


@Service
public interface CourseService {
	BaseObject<Course> getCoursebyId(Integer courseId);


}
