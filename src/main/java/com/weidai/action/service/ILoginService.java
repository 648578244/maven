package com.weidai.action.service;

import com.weidai.action.model.UserBean;
import org.springframework.stereotype.Service;

/**
 * Created by fuck on 17/1/6.
 */
public interface ILoginService {
    public UserBean login(String username ,String password);
}
