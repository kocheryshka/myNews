package com.example.my_news.aop;

import com.example.my_news.exception.AccessDeniedException;
import com.example.my_news.model.RoleType;
import com.example.my_news.security.AppUserPrincipal;
import com.example.my_news.service.CommentService;
import com.example.my_news.service.NewsService;
import com.example.my_news.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessAspect {

    private final NewsService newsService;
    private final CommentService commentService;
    private final UserService userService;

    @Before("@annotation(checkAccess)")
    public void checkAccess(JoinPoint joinPoint, CheckAccess checkAccess){

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        UUID id = UUID.fromString(pathVariables.get("id"));

        var user = (AppUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getAuthorities().contains(new SimpleGrantedAuthority(RoleType.ROLE_ADMIN.name()))){
            return;
        }

        if (checkAccess.entityType() == EntityType.NEWS
                && !newsService.findById(id).getUser().getUsername().equals(user.getUsername())){
            throw new AccessDeniedException("Access denied for this action!");
        }

        if (checkAccess.entityType() == EntityType.COMMENT
                && !commentService.findById(id).getUser().getUsername().equals(user.getUsername())){
            throw new AccessDeniedException("Access denied for this action!");
        }

        if (checkAccess.entityType() == EntityType.USER
                && !userService.findById(id).getUsername().equals(user.getUsername())){
            throw new AccessDeniedException("Access denied for this action!");
        }
        //Также вы можете извлечь и GET-параметры запроса:
        //request.getParameter(“paramName”);
    }

}
