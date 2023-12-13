package com.springbootinpractice.listener;

import com.springbootinpractice.exception.UrlNotAccessibleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UrlAccessibilityHandler {

    @Value("${api.url:https://dog.ceo/}")
    private String url;

    @EventListener(classes = ContextRefreshedEvent.class)
    public void listen() {
        // 외부 API URL 에 접근할 수 없는 상태를 만들기 위해
        /// 의도적으로 UrlNotAccessibleException 예외를 던지도록 작성
//        throw new UrlNotAccessibleException(url);
    }
}
