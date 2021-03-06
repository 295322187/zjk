package com.zjk.module.common.base.configuration;

import com.zjk.module.common.base.annotation.CreateApiDocs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(
		basePackages = {"com.zjk.module.common.base"}
)
@EnableSwagger2
public class CommonBaseConfiguration {

	@Value("${com.zjk.module.common.config.swagger2.api.url:https://github.com/295322187/zjk}")
	private String apiUrl;
	@Value("${com.zjk.module.common.config.swagger2.api.description:zjk的公共组件}")
	private String description;

	// 忽略SSL
	{
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((m, n) -> true);
	}

	/****************************************************************************/
	// apidocs
	@Bean
	public Docket api() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("Authorization").description("签名").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());

		tokenPar = new ParameterBuilder();
		tokenPar.name("Login").description("用户编号").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());

		tokenPar = new ParameterBuilder();
		tokenPar.name("Lang").description("语言").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(CreateApiDocs.class))
				//.apis(RequestHandlerSelectors.basePackage("com.sbm.module"))
				.paths(PathSelectors.any())
				.build()
				// 添加全局参数
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("microservice")
				.description(description)
				.contact(new Contact("张骏恺", apiUrl, "junkai.zhang@superbrandmall.com"))
				.version("1.0")
				.build();
	}


}
