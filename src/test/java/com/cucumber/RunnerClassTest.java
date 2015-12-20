/**
 * THIS CODE IS AN INTELLECTUAL PROPERTY OF FISSION LABS.© 2014 FISSION LABS. ALL RIGHTS RESERVED.
 */

package com.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(
		features = {"src/test/resources"},
		format = {"pretty", "html:target/","json:target/cucumber.json"}
		
		)

public class RunnerClassTest {

}
