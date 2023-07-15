package com.oldandsea.pcb.config;

import com.oldandsea.pcb.domain.entity.Member;
import com.oldandsea.pcb.domain.entity.Session;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Slf4j
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final SessionService sessionService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // @Login 어노테이션이 붙어있어야 하고
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        // @Login이 붙은 것은 타입이 Member 클래스여야 한다.
        boolean hasMemberType = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotation && hasMemberType;
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Cookie[] cookies = request.getCookies();
        String sessionIdFromCookie = null;
        for (Cookie cookie : cookies) {
            if ("PCBSESSIONID".equals(cookie.getName())) {
                sessionIdFromCookie = cookie.getValue();
                break;
            }
        }
        Session dbSession = sessionService.session_findByKey(sessionIdFromCookie);
        if (dbSession.getSessionId() ==null) {
            throw new IllegalArgumentException("Please login first");
        }
        return dbSession.getMemberId();
    }
}