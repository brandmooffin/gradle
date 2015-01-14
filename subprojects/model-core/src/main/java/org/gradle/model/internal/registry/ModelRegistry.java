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

package org.gradle.model.internal.registry;

import org.gradle.api.Nullable;
import org.gradle.model.internal.core.*;
import org.gradle.model.internal.type.ModelType;

public interface ModelRegistry extends ModelRegistrar {

    /**
     * Get the fully defined model element at the given path as the given type.
     * <p>
     * No attempt to mutate the returned object should be made.
     *
     * @param path the path for the node
     * @param type the type to project the node as
     * @param <T> the type to project the node as
     * @return the node as the given type
     */
    public <T> T realize(ModelPath path, ModelType<T> type);

    /**
     * Returns the node at the given path at the desired state, if it exists.
     * <p>
     * If there is no known node at that path, {@code null} is returned.
     * <p>
     * If the node exists but is at a later state than the requested state an exception will be thrown.
     * If the node is at an earlier state it will be irrevocably transitioned to the desired state and returned.
     * If it is at the desired state it is returned.
     *
     * @param path the path for the node
     * @param state the desired node state
     * @return the node at the desired state, or null if node is unknown
     */
    @Nullable
    public ModelNode atState(ModelPath path, ModelNode.State state);

    /**
     * Returns the node at the given path at the desired state or later, if it exists.
     * <p>
     * If there is no known node at that path, {@code null} is returned.
     * <p>
     * If the node is at an earlier state than desired it will be irrevocably transitioned to the desired state and returned.
     * If it is at the desired state or later it is returned.
     *
     * @param path the path for the node
     * @param state the desired node state
     * @return the node at the desired state, or null if node is unknown
     */
    @Nullable
    public ModelNode atStateOrLater(ModelPath path, ModelNode.State state);

    public ModelNode.State state(ModelPath path);

    @Nullable
    <T> T find(ModelPath path, ModelType<T> type);

    @Nullable
    MutableModelNode node(ModelPath path);

    @Nullable
    MutableModelNode realizeNode(ModelPath path);

    void remove(ModelPath path);

    void validate() throws UnboundModelRulesException;

    @Override
    ModelRegistry create(ModelCreator creator);

    @Override
    <T> ModelRegistry apply(ModelActionRole role, ModelAction<T> action);
}
