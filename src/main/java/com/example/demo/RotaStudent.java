package com.example.demo;

import com.alineservices.GetOneRequest;
import com.alineservices.GetOneResponse;
import com.alineservices.Student;
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
                        System.out.println("fkjhdkjfdutyrugdfiyhfiosd fysdjugy");
                        GetOneRequest c = new GetOneRequest();
                        c.setNome(exchange.getIn().getHeader("nome").toString());
                        exchange.getIn().setBody(c);
                    }
                })
                .setHeader(CxfConstants.OPERATION_NAME, constant("{{endpoint.operation.fahrenheit.to.celsius}}"))
                .setHeader(CxfConstants.OPERATION_NAMESPACE, constant("{{endpoint.namespace}}"))
                .to("cxf:bean:cxfConvertTemp")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        MessageContentsList response = (MessageContentsList) exchange.getIn().getBody();
                        GetOneResponse r = (GetOneResponse) response.get(0);
                        exchange.getIn().setBody(r.getStudent());

                    }
                })
                .end();





    }

}
