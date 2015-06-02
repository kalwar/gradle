/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.artifacts.configurations;

import org.gradle.api.artifacts.*;
import org.gradle.api.internal.tasks.AbstractTaskDependency;
import org.gradle.api.internal.tasks.TaskDependencyResolveContext;
import org.gradle.api.tasks.TaskDependency;

class ConfigurationDirectBuildDependencies extends AbstractTaskDependency {
    private final DependencySet dependencies;
    private final PublishArtifactSet publishArtifacts;

    public static TaskDependency forDependenciesOnly(Configuration configuration) {
        return new ConfigurationDirectBuildDependencies(configuration.getAllDependencies(), null);
    }

    public static TaskDependency forDependenciesAndArtifacts(Configuration configuration) {
        return new ConfigurationDirectBuildDependencies(configuration.getAllDependencies(), configuration.getAllArtifacts());
    }

    private ConfigurationDirectBuildDependencies(DependencySet dependencies, PublishArtifactSet artifacts) {
        this.dependencies = dependencies;
        this.publishArtifacts = artifacts;
    }

    public void resolve(TaskDependencyResolveContext context) {
        for (SelfResolvingDependency dependency : dependencies.withType(SelfResolvingDependency.class)) {
            if (!(dependency instanceof ProjectDependency)) {
                context.add(dependency);
            }
        }
        if (publishArtifacts != null) {
            context.add(publishArtifacts);
        }
    }
}
