package com.mytests.spring.springprogrammaticbeansreposrouters;

import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.web.servlet.function.RouterFunctions.nest;
import static org.springframework.web.servlet.function.RouterFunctions.route;

public class InlinedNestedRoutesRegistrar implements BeanRegistrar {
  @Override
  public void register(BeanRegistry registry, Environment env) {
   registry.registerBean(RouterFunction.class, myRoutesSpec ->
    myRoutesSpec.supplier(supplierContext -> nest(
            path("/nested/inlined1").or(path("/nested/inlined2")),
     route(GET("/test1"), request -> ServerResponse.ok().body("inlined nested test1"))
                  .and(
            route(GET("/test2"), request -> ServerResponse.ok().body("inlined nested test2"))))));
  }
}

@Configuration
@Import(InlinedNestedRoutesRegistrar.class)
class InlinedNestedRoutesRegistrarConfiguration {

}