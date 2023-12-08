package com.springbootinpractice.customproperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;


// 설정 파일에서 app.sbip.ct 가 붙어 있는 properties들을 바인딩 해준다.
// @ConstructorBinding -> 생성자가 여러개가 아니라면 명시적으로 쓸 필요가 없다고 한다.
@ConfigurationProperties("app.sbip.ct")
// 설정 정보를 외부화하고 타입 안정성을 확보할 수 있으며 구조화된 방식으로 관리할 수 있다.
@Getter
@ToString
@AllArgsConstructor
public class AppProperties {

    private final String name;
    private final String ip;
    private final int port;
    private final Security security;

    @AllArgsConstructor
    @ToString
    @Getter
    public static class Security {
        private boolean enabled;
        private final String token;
        private final List<String> roles;
    }
}
