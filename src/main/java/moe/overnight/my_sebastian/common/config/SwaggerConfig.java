package moe.overnight.my_sebastian.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEME = "bearer-jwt";

    /** 메인 스펙 (Info/보안/외부문서 등) */
    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My SebastianAPI")
                        .version("0.0.1v")
                        .description("시간표 기반 여행 스케줄러 API")
                        .contact(new Contact().name("Overnight").email("overnight@overnight.moe"))
                        .license(new License().name("Apache-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("API 가이드")
                        .url("https://example.com/docs"))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME));
    }

    /** 기본 그룹: 애플리케이션 공개 API */
    @Bean
    public GroupedOpenApi publicApi(OpenApiCustomizer globalCustomizer) {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.example.api")  // 컨트롤러 패키지
                .addOpenApiCustomizer(globalCustomizer)
                .build();
    }

    /** 어드민/내부 API (선택) */
    @Bean
    public GroupedOpenApi adminApi(OpenApiCustomizer globalCustomizer) {
        return GroupedOpenApi.builder()
                .group("admin")
                .packagesToScan("com.example.admin.api")
                .pathsToMatch("/admin/**")
                .addOpenApiCustomizer(globalCustomizer)
                .build();
    }

    /** 공통 커스터마이저(글로벌 응답/태그 등) */
    @Bean
    public OpenApiCustomizer globalCustomizer() {
        return openApi -> {
            // 예: 공통 태그/서술 추가, 서버 목록 주입 등
            // openApi.addServersItem(new Server().url("https://api.example.com"));
        };
    }
}
