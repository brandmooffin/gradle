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

package org.gradle.nativeplatform.toolchain.internal.gcc

import org.gradle.nativeplatform.toolchain.internal.CommandLineToolContext
import org.gradle.nativeplatform.toolchain.internal.NativeCompileSpec
import org.gradle.nativeplatform.toolchain.internal.NativeCompiler
import org.gradle.nativeplatform.toolchain.internal.compilespec.CppPCHCompileSpec

class CppPCHCompilerTest extends GccCompatibleNativeCompilerTest {
    @Override
    protected NativeCompiler getCompiler(CommandLineToolContext invocationContext, String objectFileExtension, boolean useCommandFile) {
        return new CppPCHCompiler(compilerOutputFileNamingSchemeFactory, invocationContext, objectFileExtension, useCommandFile)
    }

    @Override
    protected Class<? extends NativeCompileSpec> getCompileSpecType() {
        return CppPCHCompileSpec
    }

    @Override
    protected List<String> getCompilerSpecificArguments(File includeDir) {
        return [ '-x', 'c++-header' ] + super.getCompilerSpecificArguments(includeDir)
    }
}
