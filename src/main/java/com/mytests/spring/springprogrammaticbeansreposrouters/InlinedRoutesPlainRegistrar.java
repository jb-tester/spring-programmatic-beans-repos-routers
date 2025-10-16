package com.mytests.spring.springprogrammaticbeansreposrouters;

import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

public class InlinedRoutesPlainRegistrar implements BeanRegistrar {
  @Override
  public void register(BeanRegistry registry, Environment env) {
   registry.registerBean(RouterFunction.class, myRoutesSpec ->
    myRoutesSpec.supplier(supplierContext ->
     route(GET("/inlined/test1"), request -> ServerResponse.ok().body("inlined plain test1"))
     .andRoute(GET("/inlined/test2"), request -> ServerResponse.ok().body("inlined plain test2"))
             .and(route(GET("/inlined/test3"), request -> ServerResponse.ok().body("inlined plain test3")))));
  }
}

@Configuration
@Import(InlinedRoutesPlainRegistrar.class)
class InlinedRoutesPlainRegistrarConfiguration {

}
