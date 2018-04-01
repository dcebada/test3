package com.mycorp;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.VoidDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


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
	public void shouldCheckOfChrome() {
		BrowserManagerEnum of = BrowserManagerEnum.of("chrome");
		// Mockeamos los métodos del helper para ayudarnos con la selección de drivers
		PowerMockito.mockStatic(BrowserDriverHelper.class);
		Mockito.when(BrowserDriverHelper.getChromeDriver()).thenReturn(getChromeMockDriver());

		assertThat(BrowserManagerEnum.CHROME).isEqualTo(of);
		assertThat(of.getBrowserManager()).isInstanceOf(ChromeDriverManager.class);
		assertThat(of.getBrowserManager("1")).isInstanceOf(ChromeDriverManager.class);
		assertThat(of.getDriver()).isInstanceOf(RemoteWebDriver.class);
	}

	private WebDriver getChromeMockDriver() {
		log.debug("Creando mock del driver de Chrome");
		final DesiredCapabilities dc = new DesiredCapabilities(BrowserType.CHROME, "chrome-mock-version", Platform
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
				setSessionId("mock");
			}
		};
		return mock;
	}

}
