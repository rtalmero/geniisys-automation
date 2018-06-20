package com.geniisys.automation.common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.geniisys.automation.BaseTest;

public class Listeners implements ITestListener{

//	private BrowserDriver driverx;
	
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		((BaseTest)arg0.getInstance()).getDriver().takeScreenShot(arg0.getName());
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

}
