package com.mycorp;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.VoidDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * Unit test for simple BrowserManagerEnumTest.
 */
public class BrowserManagerEnumTest {

	@Test
	public void shouldCheckOf() {
		BrowserManagerEnum of = BrowserManagerEnum.of("test");
		Assert.assertEquals(BrowserManagerEnum.NONE, of);
		of = BrowserManagerEnum.of("perry");
		Assert.assertEquals(BrowserManagerEnum.NONE, of);
		of = BrowserManagerEnum.of(null);
		Assert.assertEquals(BrowserManagerEnum.NONE, of);
		Assert.assertTrue(of.getBrowserManager() instanceof VoidDriverManager);
		Assert.assertTrue(of.getBrowserManager("1") instanceof VoidDriverManager);
		Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
	}

	@Test
	public void shouldCheckOfChrome() {
		BrowserManagerEnum of = BrowserManagerEnum.of("chrome");
		Assert.assertEquals(BrowserManagerEnum.CHROME, of);
		Assert.assertTrue(of.getBrowserManager() instanceof ChromeDriverManager);
		Assert.assertTrue(of.getBrowserManager("1") instanceof ChromeDriverManager);
		Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
	}

}
