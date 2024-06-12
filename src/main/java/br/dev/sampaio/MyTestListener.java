package br.dev.sampaio;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyTestListener implements ConcurrentEventListener {
    private static final Logger LOG = LogManager.getLogger(MyTestListener.class);

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        TestCase testCase = event.getTestCase();
        Result result = event.getResult();
        Status status = result.getStatus();
        Throwable error = result.getError();
        String scenarioName = testCase.getName();
        if(error != null) {
            LOG.info(error);
        }
        LOG.info("*****************************************************************************************");
        LOG.info("	Scenario: "+scenarioName+" --> "+status.name());
        LOG.info("*****************************************************************************************");
    }
}
