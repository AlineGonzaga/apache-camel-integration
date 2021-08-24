package com.example.demo;

import com.google.maps.model.GeocodingResult;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.rest.RestParamType.query;

@Component
public class MyCamelRouter  extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // rest-dsl is also configured in the application.properties file

//        rest("/geocoder").description("Geocoder REST service")
//                .consumes("application/json")
//                .produces("application/json")
//
//                .get().description("Geocoder address lookup").outType(GeocodingResult[].class)
//                .param().name("address").type(query).description("The address to lookup").dataType("string").endParam()
//                .responseMessage().code(200).message("Geocoder successful").endResponseMessage()
//                // call the geocoder to lookup details from the provided address
//                .toD("geocoder:address:${header.address})");


         //   restConfiguration().component("netty-http").host("localhost").port(9090).bindingMode(RestBindingMode.auto);

//        restConfiguration()
//                .component("undertow").host("0.0.0.0").port(9090).bindingMode(RestBindingMode.auto).scheme("http")
//                .dataFormatProperty("prettyPrint", "true")
//                .contextPath("/")
//                .apiContextPath("/api-doc")
//                .apiProperty("api.title", "Camel2Soap")
//                .apiProperty("api.version", "1.0")
//                .apiProperty("host","")
//                .enableCORS(true);

            rest("student")
                    .get("/{nome}")
                    .consumes("application/json")
                    .produces("application/json")
                    .param().name("nome").type(RestParamType.path).dataType("string").endParam()

                    .to("direct:getOneRequest");


    }
}
