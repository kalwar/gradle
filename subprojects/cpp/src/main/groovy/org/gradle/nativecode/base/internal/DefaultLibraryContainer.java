/*
 * Copyright 2013 the original author or authors.
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

package org.gradle.nativecode.base.internal;

import org.gradle.api.internal.AbstractNamedDomainObjectContainer;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.internal.reflect.Instantiator;
import org.gradle.nativecode.base.Library;
import org.gradle.nativecode.base.LibraryContainer;

public class DefaultLibraryContainer extends AbstractNamedDomainObjectContainer<Library> implements LibraryContainer {
    private final FileResolver fileResolver;

    public DefaultLibraryContainer(Instantiator instantiator, FileResolver fileResolver) {
        super(Library.class, instantiator);
        this.fileResolver = fileResolver;
    }

    @Override
    protected Library doCreate(String name) {
        return getInstantiator().newInstance(DefaultLibrary.class, name, fileResolver);
    }
}
