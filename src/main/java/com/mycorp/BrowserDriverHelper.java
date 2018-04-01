package com.mycorp;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class BrowserDriverHelper {

	public static WebDriver selectDriver(@NonNull BrowserManagerEnum browser) {
		log.debug("Seleccionando driver para el browser {}", browser);

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
				return getPhantomJSDriver();
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

	public static WebDriver getChromeDriver() {
		return new ChromeDriver();
	}

	public static WebDriver getFirefoxDriver() {
		return new FirefoxDriver();
	}

	public static WebDriver getIEDriver() {
		return new InternetExplorerDriver();
	}

	public static WebDriver getEdgeDriver() {
		return new EdgeDriver();
	}

	public static WebDriver getOperaDriver() {
		return new OperaDriver();
	}

	public static WebDriver getPhantomJSDriver() {
		return new PhantomJSDriver();
	}
}
