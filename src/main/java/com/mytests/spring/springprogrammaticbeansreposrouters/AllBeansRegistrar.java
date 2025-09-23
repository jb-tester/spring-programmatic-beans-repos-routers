package com.mytests.spring.springprogrammaticbeansreposrouters;

import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.function.RouterFunction;

public class AllBeansRegistrar implements BeanRegistrar {
    @Override
    public void register(BeanRegistry registry, Environment env) {
        if (env.matchesProfiles("h2")) {
            registry.registerBean(
                    PersonRepository.class,
                    (BeanRegistry.Spec<PersonRepository> spec) -> spec
                            .supplier(ctx -> new H2PersonRepository() {
                            })
            );
        } else {
            registry.registerBean(
                    PersonRepository.class,
                    (BeanRegistry.Spec<PersonRepository> spec) -> spec
                            .supplier(ctx -> new NoDBPersonRepository())
            );
        }
        registry.registerBean(InitDbService.class,
                (BeanRegistry.Spec<InitDbService> spec) -> spec
                        .supplier(ctx -> new InitDbService(ctx.bean(PersonRepository.class)))
        );
        registry.registerBean(RoutesHandler.class,
                (BeanRegistry.Spec<RoutesHandler> spec) -> spec
                        .supplier(ctx -> new RoutesHandler(ctx.bean(PersonRepository.class)))
        );

          registry.registerBean(PersonRoutes.class,
                (BeanRegistry.Spec<PersonRoutes> spec) -> spec
                        .supplier(ctx -> new PersonRoutes(ctx.bean(RoutesHandler.class))));

        registry.registerBean(RouterFunction.class,
                ( spec -> spec
                       .supplier(ctx -> ctx.bean(PersonRoutes.class).routes())
        ));

    }
}