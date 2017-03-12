/*
 *       Copyright (c) 2017.  Preston Garno
 *
 *        Licensed under the Apache License, Version 2.0 (the "License");
 *        you may not use this file except in compliance with the License.
 *        You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 *        Unless required by applicable law or agreed to in writing, software
 *        distributed under the License is distributed on an "AS IS" BASIS,
 *        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *        See the License for the specific language governing permissions and
 *        limitations under the License.
 */

package edu.gvsu.prestongarno.sourcegentests;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.JavaFileObjects;
import edu.gvsu.prestongarno.MVProcessor;
import edu.gvsu.prestongarno.sourcegentests.TestUtil.TestUtil;
import org.junit.Test;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;

/**
 * *************************************************
 * Dynamic-MVP - edu.gvsu.prestongarno.sourcegentests - by Preston Garno on 3/10/17
 ***************************************************/
public class CompilerTests1 {

    /**
     * Simple test compile without any annotation processing
     */
    @Test
    public void simpleTestCompile() throws Exception {
        Compilation compilation = javac().compile(
                JavaFileObjects.forSourceString("HelloWorld", "final class HelloWorld {}"));
        assertThat(compilation).succeeded();
    }

    @Test
    public void testWithProcessor() throws Exception {
        Compilation compilation =
                javac()
                        .withProcessors(new MVProcessor())
                        .compile(TestUtil.loadClassSet(0));
        assertThat(compilation).succeededWithoutWarnings();

        TestUtil.outputDiagnostics(compilation);
    }

    @Test
    public void testMultipleFileCompilation() throws Exception {
    }
}