package io.github.makssent.jmix.firebird;

import io.jmix.core.annotation.JmixModule;
import io.jmix.eclipselink.EclipselinkConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@ConfigurationPropertiesScan
@JmixModule(dependsOn = EclipselinkConfiguration.class)
@PropertySource(name = "io.github.makssent.jmix.firebird", value = "classpath:/io/github/makssent/jmix/firebird/module.properties")
public class FirebirdConfiguration {
}