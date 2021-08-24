package com.example.demo.cxf;

import com.alineservices.ObjectFactory;
import com.alineservices.StudentPort;
import com.alineservices.StudentPortService;
import com.example.demo.DemoApplication;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfBeans {

    @Value("${endpoint.wsdl}")
    private String SOAP_URL;


    @Bean(name = "cxfConvertTemp")
    public CxfEndpoint buildCxfEndpoint() {
        System.out.println("passou aqui");
        CxfEndpoint cxf = new CxfEndpoint();
        cxf.setAddress(SOAP_URL);
        cxf.setServiceClass(StudentPort.class);
        return cxf;
    }
}