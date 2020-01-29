/*
 * Copyright 2006-2016 the original author or authors.
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

package todo;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.config.CitrusSpringConfig;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Christoph Deppisch
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@ContextConfiguration(classes = CitrusSpringConfig.class)
public class TodoSteps {

    /** Test designer filled with actions by step definitions */
    @CitrusResource
    private TestDesigner designer;

    @Autowired
    private HttpClient todoListClient;

    @Given("^Say greeting$")
    public void sayGreeting() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        designer.http()
                .client(todoListClient)
                .send()
                .get("/greeting");

        designer.http()
                .client(todoListClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.PLAINTEXT);
    }
}
