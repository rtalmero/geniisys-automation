package com.geniisys.automation.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.geniisys.automation.BaseTest;

public class Listeners implements ITestListener{
	
	private static final Logger LOGGER = LogManager.getLogger(BrowserDriver.class.getName());
	
	@Override
	public void onStart(ITestContext arg0) {
		LOGGER.info(arg0.getCurrentXmlTest().getName() + " test STARTED");
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		((BaseTest)arg0.getInstance()).getDriver().takeScreenShot(arg0.getName());
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		//DO NOTHING
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		//DO NOTHING
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		//DO NOTHING
	}
	
	@Override
	public void onFinish(ITestContext arg0) {
		//DO NOTHING
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		//DO NOTHING
	}

}
