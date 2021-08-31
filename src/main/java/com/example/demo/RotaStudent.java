package com.example.demo;

import com.alineservices.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.stereotype.Component;

@Component
public class RotaStudent extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:getOneRequest")
                .removeHeaders("CamelHttp*")
                .routeId("getOneRequest")

                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        GetOneRequest c = new GetOneRequest();
                        c.setNome(exchange.getIn().getHeader("nome").toString());
                        exchange.getMessage().setBody(c);
                    }
                })
                .setHeader(CxfConstants.OPERATION_NAME, constant("{{endpoint.operation.get}}"))
                .setHeader(CxfConstants.OPERATION_NAMESPACE, constant("{{endpoint.namespace}}"))
                .to("cxf:bean:cxfConvertTemp")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        MessageContentsList response = (MessageContentsList) exchange.getMessage().getBody();
                        GetOneResponse r = (GetOneResponse) response.get(0);
                        exchange.getMessage().setBody(r);
                    }
                })
                .removeHeaders("CamelHttp*")
//                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//                .choice()
//                    .when().simple("${body} == null")
//                    .log("no one")
//
//                .otherwise()
                .marshal().json(JsonLibrary.Jackson)
                .log("${body}")
                .filter(simple("${body} == null"))
                .throwException(new IllegalArgumentException("it is empty!"))
                .end();

                from("direct:deleteRequest")
                .removeHeaders("CamelHttp*")
                .routeId("deleteRequest")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        DeleteOneRequest c = new DeleteOneRequest();
                        c.setNome(exchange.getIn().getHeader("nome").toString());
                        exchange.getIn().setBody(c);
                    }
                })

                .setHeader(CxfConstants.OPERATION_NAME, constant("{{endpoint.operation.delete}}"))
                .setHeader(CxfConstants.OPERATION_NAMESPACE, constant("{{endpoint.namespace}}"))
                .to("cxf:bean:cxfConvertTemp")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getBody().toString());
                    }
                })
                .end();

        from("direct:postRequest")
                .removeHeaders("CamelHttp*")
                .routeId("postRequest")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        //SetOneRequest c = new SetOneRequest();
                        SetOneRequest setOneRequest = (SetOneRequest) exchange.getMessage().getBody();
                     //   System.out.println();
                      //  c.setStudent(s);
                        exchange.getMessage().setBody(setOneRequest);
                    }
                })
                .setHeader(CxfConstants.OPERATION_NAME, constant("{{endpoint.operation.post}}"))
                .setHeader(CxfConstants.OPERATION_NAMESPACE, constant("{{endpoint.namespace}}"))
                .to("cxf:bean:cxfConvertTemp")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("post");
                        MessageContentsList response = (MessageContentsList) exchange.getMessage().getBody();
                        SetOneResponse r = (SetOneResponse) response.get(0);
                        exchange.getMessage().setBody(r.getStudent());
                    }
                })
                .end();
    }

}
