package com.mycorp;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;

import java.util.Map;

public class BrowserDriverService implements BrowserDriver {

	@Override
	public WebDriver selectDriver(@NotNull BrowserManagerEnum browser) {
		switch (browser) {
			case CHROME:
				return getChromeDriver();
			case FIREFOX:
				return getFirefoxDriver();
			case EDGE:
				return getEdgeDriver();
			case IE:
				return getIEDriver();
			case MARIONETTE:
				return getFirefoxDriver();
			case OPERA:
				return getOperaDriver();
			case PHANTOMJS:
				return getPhatomJSDriver();
			case NONE:
			default:
				final DesiredCapabilities dc = new DesiredCapabilities(BrowserType.MOCK, "mock-version", Platform.ANY);
				final RemoteWebDriver mock = new RemoteWebDriver(dc) {
					/**
					 * {@inheritDoc}
					 *
					 * @see RemoteWebDriver#execute(String, Map)
					 */
					@Override
					protected Response execute(final String driverCommand, final Map<String, ?> parameters) {
						return new Response();
					}

					/**
					 * {@inheritDoc}
					 *
					 * @see RemoteWebDriver#startSession(Capabilities, Capabilities)
					 */
					@Override
					protected void startSession(final Capabilities desiredCapabilities, final Capabilities
							requiredCapabilities) {
						setSessionId("mock");
					}
				};
				return mock;
		}
	}

	@Override
	public WebDriver getChromeDriver() {
		return new ChromeDriver();
	}

	@Override
	public WebDriver getFirefoxDriver() {
		return new FirefoxDriver();
	}

	@Override
	public WebDriver getIEDriver() {
		return new InternetExplorerDriver();
	}

	@Override
	public WebDriver getEdgeDriver() {
		return new EdgeDriver();
	}

	@Override
	public WebDriver getOperaDriver() {
		return new OperaDriver();
	}

	@Override
	public WebDriver getPhatomJSDriver() {
		return new PhantomJSDriver();
	}
}
