//package com.example.demo;
//
//import com.alineservices.DeleteOneRequest;
//import com.alineservices.DeleteOneResponse;
//import com.alineservices.GetOneRequest;
//import com.alineservices.GetOneResponse;
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.cxf.common.message.CxfConstants;
//import org.apache.cxf.message.MessageContentsList;
//
//public class RotaDeleteStudent extends RouteBuilder {
//    @Override
//    public void configure() throws Exception {
//
//        from("direct:deleteRequest")
//                .removeHeaders("CamelHttp*")
//                .routeId("deleteRequest")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        DeleteOneRequest c = new DeleteOneRequest();
//                        c.setNome(exchange.getIn().getHeader("nome").toString());
//                        exchange.getIn().setBody(c);
//                    }
//                })
//                .setHeader(CxfConstants.OPERATION_NAME, constant("{{endpoint.operation.delete}}"))
//                .setHeader(CxfConstants.OPERATION_NAMESPACE, constant("{{endpoint.namespace}}"))
//                .to("cxf:bean:cxfConvertTemp")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//
//                        MessageContentsList response = (MessageContentsList) exchange.getIn().getBody();
//                        DeleteOneResponse r = (DeleteOneResponse) response.get(0);
//                       // exchange.getIn().setBody(r.getStudent().toString());
//                    }
//                })
//                .end();
//
//    }
//}
