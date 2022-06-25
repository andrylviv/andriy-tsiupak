package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("bean-prop.properties")
public class OtherConfig {
    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn(value = {"beanD"})
    public BeanB beanB(@Value("${beanB.name}") String nameB, @Value("${beanB.value}") int valueB) {
        return new BeanB(nameB, valueB);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn(value = {"beanB"})
    public BeanC beanC(@Value("${beanC.name}") String nameC, @Value("${beanC.value}") int valueC) {
        return new BeanC(nameC, valueC);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD beanD(@Value("${beanD.name}") String nameD, @Value("${beanD.value}") int valueD) {
        return new BeanD(nameD, valueD);
    }
}
