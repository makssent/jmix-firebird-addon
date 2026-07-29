package io.github.makssent.jmix.autoconfigure.firebird;

import io.github.makssent.jmix.firebird.${project_id.capitalize()}Configuration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({${project_id.capitalize()}Configuration.class})
public class ${project_id.capitalize()}AutoConfiguration {
}

