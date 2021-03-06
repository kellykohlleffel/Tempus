/**
 * Copyright © 2016-2017 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.thingsboard.server.install.ThingsboardSchemaCreationService;

import java.util.Arrays;


@SpringBootConfiguration
@ComponentScan({
        "org.thingsboard.server.install.ThingsboardSchemaCreationService",
        "org.thingsboard.server.service.install.DatabaseSchemaService",
        "org.thingsboard.server.service.install.CassandraDatabaseSchemaService",
        "org.thingsboard.server.dao.cassandra.CassandraInstallCluster",
        "org.thingsboard.server.dao.cassandra.CassandraQueryOptions",
        "org.thingsboard.server.dao.cassandra.CassandraSocketOptions",
        "org.thingsboard.server.dao.cassandra.AbstractCassandraCluster"})
public class ThingsboardSchemaCreationApplication {
    private static final String SPRING_CONFIG_NAME_KEY = "--spring.config.name";
    private static final String DEFAULT_SPRING_CONFIG_PARAM = SPRING_CONFIG_NAME_KEY + "=" + "thingsboard";

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ThingsboardInstallApplication.class);
        application.setAdditionalProfiles("install");
        ConfigurableApplicationContext context = application.run(updateArguments(args));

        context.getBean(ThingsboardSchemaCreationService.class).performInstall();
    }

    private static String[] updateArguments(String[] args) {
        if (Arrays.stream(args).noneMatch(arg -> arg.startsWith(SPRING_CONFIG_NAME_KEY))) {
            String[] modifiedArgs = new String[args.length + 1];
            System.arraycopy(args, 0, modifiedArgs, 0, args.length);
            modifiedArgs[args.length] = DEFAULT_SPRING_CONFIG_PARAM;
            return modifiedArgs;
        }
        return args;
    }
}
