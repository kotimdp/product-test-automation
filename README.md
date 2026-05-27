# product-test-automation

     Pre requisites to run the tests
          1. Make sure Maven and Java is installed globally. you can test with the below command
             mvn --version  
             java --version
                      
      Running Tests and check the report 
      
          1) Once you have cloned the repository, Please be on the main folder
          2) Run the below command to run all the API tests
                   mvn clean verify

          3) you can check the report in below folder
              target/site/index.html    
             
             In Reports you can see all the scenarios.And each step is producing a screenshot and attached next to the step.# product-test-automation
Note: Due to restrictions on the allowable number of API calls during execution, I was unable to generate the complete report containing all successful test cases. The API hit limit prevented the system from executing the full suite of validations in a single run.

However, I have followed all required steps and executed as many test scenarios as the API constraints permitted. The available results have been captured and are included under the Test Evidence folder. These represent all test outcomes that could be successfully generated within the current API usage limits.