package com.mycorp;

import io.github.bonigarcia.wdm.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Unit test for simple BrowserManagerEnumTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(BrowserDriverHelper.class)
@PowerMockIgnore("javax.net.ssl.*")
@Slf4j
public class BrowserManagerEnumTest {

	@Mock
	private ChromeDriver chromeDriver;

	@Test
	public void shouldCheckOf() {
		BrowserManagerEnum of = BrowserManagerEnum.of("test");
		assertThat(BrowserManagerEnum.NONE).isEqualTo(of);
		of = BrowserManagerEnum.of("perry");
		assertThat(BrowserManagerEnum.NONE).isEqualTo(of);
		of = BrowserManagerEnum.of(null);
		assertThat(BrowserManagerEnum.NONE).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(VoidDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(VoidDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}

	@Test
	public void shouldCheckOfChrome()  {
		BrowserManagerEnum of = BrowserManagerEnum.of("chrome");
		// Mockeamos los métodos del helper para ayudarnos con la selección de drivers
		mockStatic(BrowserDriverHelper.class);
		when(BrowserDriverHelper.selectDriver(BrowserManagerEnum.CHROME)).thenReturn(getChromeMockDriver());

		assertThat(BrowserManagerEnum.CHROME).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(ChromeDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(ChromeDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}

	@Test
	public void shouldCheckOfFirefox() {
		BrowserManagerEnum of = BrowserManagerEnum.of("firefox");
		// Mockeamos los métodos del helper para ayudarnos con la selección de drivers
		mockStatic(BrowserDriverHelper.class);
		when(BrowserDriverHelper.selectDriver(BrowserManagerEnum.FIREFOX)).thenReturn(getFirefoxMockDriver());

		assertThat(BrowserManagerEnum.FIREFOX).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(FirefoxDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(FirefoxDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}


	@Test
	public void shouldCheckOfOpera() {
		BrowserManagerEnum of = BrowserManagerEnum.of("opera");
		// Mockeamos los métodos del helper para ayudarnos con la selección de drivers
		mockStatic(BrowserDriverHelper.class);
		when(BrowserDriverHelper.selectDriver(BrowserManagerEnum.OPERA)).thenReturn(getOperaMockDriver());

		assertThat(BrowserManagerEnum.OPERA).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(OperaDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(OperaDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}

	@Test
	public void shouldCheckOfEdge() {
		BrowserManagerEnum of = BrowserManagerEnum.of("edge");
		// Mockeamos los métodos del helper para ayudarnos con la selección de drivers
		mockStatic(BrowserDriverHelper.class);
		when(BrowserDriverHelper.selectDriver(BrowserManagerEnum.EDGE)).thenReturn(getEdgeMockDriver());

		assertThat(BrowserManagerEnum.EDGE).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(EdgeDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(EdgeDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}

	@Test
	public void shouldCheckOfIE() {
		BrowserManagerEnum of = BrowserManagerEnum.of("ie");
		// Mockeamos los métodos del helper para ayudarnos con la selección de drivers
		mockStatic(BrowserDriverHelper.class);
		when(BrowserDriverHelper.selectDriver(BrowserManagerEnum.IE)).thenReturn(getIEMockDriver());

		assertThat(BrowserManagerEnum.IE).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(InternetExplorerDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(InternetExplorerDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}

	@Test
	public void shouldCheckOfMarionette() {
		BrowserManagerEnum of = BrowserManagerEnum.of("marionette");
		// Mockeamos los métodos del helper para ayudarnos con la selección de drivers
		mockStatic(BrowserDriverHelper.class);
		when(BrowserDriverHelper.selectDriver(BrowserManagerEnum.MARIONETTE)).thenReturn(getMarionetteMockDriver());

		assertThat(BrowserManagerEnum.MARIONETTE).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(FirefoxDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(FirefoxDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}

	@Test
	public void shouldCheckOfPhantomJS() {
		BrowserManagerEnum of = BrowserManagerEnum.of("phantomjs");
		// Mockeamos los métodos del helper para ayudarnos con la selección de drivers
		mockStatic(BrowserDriverHelper.class);
		when(BrowserDriverHelper.selectDriver(BrowserManagerEnum.PHANTOMJS)).thenReturn(getPhantomJSMockDriver());

		assertThat(BrowserManagerEnum.PHANTOMJS).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(PhantomJsDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(PhantomJsDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}

	private WebDriver getMockDriver(final String browserType, final String version) {
		log.debug("Creando mock del driver para {}", browserType);
		final DesiredCapabilities dc = new DesiredCapabilities(browserType, version, Platform
				.ANY);
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
				setSessionId(version);
			}
		};
		return mock;
	}

	private WebDriver getChromeMockDriver() {
		return getMockDriver(BrowserType.CHROME, "chrome-mock-version");
	}

	private WebDriver getFirefoxMockDriver() {
		return getMockDriver(BrowserType.FIREFOX, "firefox-mock-version");
	}

	private WebDriver getOperaMockDriver() { return getMockDriver(BrowserType.OPERA_BLINK, "opera-mock-version"); }

	private WebDriver getEdgeMockDriver() { return getMockDriver(BrowserType.EDGE, "edge-mock-version"); }

	private WebDriver getIEMockDriver() { return getMockDriver(BrowserType.IEXPLORE, "ie-mock-version"); }

	private WebDriver getMarionetteMockDriver() { return getFirefoxMockDriver(); }

	private WebDriver getPhantomJSMockDriver() { return getMockDriver(BrowserType.PHANTOMJS, "ie-mock-version"); }
}
