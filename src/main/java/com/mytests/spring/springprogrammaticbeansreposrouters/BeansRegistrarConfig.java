package com.mytests.spring.springprogrammaticbeansreposrouters;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AllBeansRegistrar.class)
public class BeansRegistrarConfig {


}


