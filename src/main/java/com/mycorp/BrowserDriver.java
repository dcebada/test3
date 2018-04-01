package com.mycorp;

import org.openqa.selenium.WebDriver;

public interface BrowserDriver {

	/**
	 * Obtener el driver del navegador correspondiente
	 * @param browser browser
	 * @return driver del browser
	 */
	WebDriver selectDriver(BrowserManagerEnum browser);

	/**
	 * Obtener una instancia del driver de chrome
	 * @return WebDriver
	 */
	WebDriver getChromeDriver();

	/**
	 * Obtener una instancia del driver de firefox
	 * @return WebDriver
	 */
	WebDriver getFirefoxDriver();

	/**
	 * Obtener una instancia del driver de Internet Explorer
	 * @return WebDriver
	 */
	WebDriver getIEDriver();

	/**
	 * Obtener una instanacia del driver de Edge
	 * @return WebDriver
	 */
	WebDriver getEdgeDriver();

	/**
	 * Obtener una instanacia del driver de Opera
	 * @return WebDriver
	 */
	WebDriver getOperaDriver();

	/**
	 * Obtener una instancia del driver de PhanthomJS
	 * @return WebDriver
	 */
	WebDriver getPhatomJSDriver();
}
