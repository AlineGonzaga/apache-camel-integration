package com.example.demo;

import com.alineservices.SetOneRequest;
import com.alineservices.SetOneResponse;
import com.alineservices.Student;
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


         //   restConfiguration().component("netty-http").host("localhostname").bindingMode(RestBindingMode.auto);

            rest("student")
                    .get("/{nome}")
                    .produces("application/json")
                    .param().name("nome").type(RestParamType.path).dataType("string").endParam()
                    .to("direct:getOneRequest")

                    .delete("/{nome}")
                    .consumes("application/json")
                    .produces("application/json")
                    .param().name("nome").type(RestParamType.path).dataType("string").endParam()
                    .to("direct:deleteRequest")

                    .post()
                    .consumes("application/json")

                    .type(Student.class).outType(Student.class)
                    .to("direct:postRequest");
            ;
    }
}
