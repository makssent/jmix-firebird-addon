package io.github.makssent.jmix.autoconfigure.firebird;

import io.github.makssent.jmix.firebird.FirebirdConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({FirebirdConfiguration.class})
public class FirebirdAutoConfiguration {
}

