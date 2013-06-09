package com.mgnyniuk.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mgnyniuk.ejb.LogService;
import com.mgnyniuk.jpa.Log;
import com.mgnyniuk.jpa.User;

@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {
	private String localeCode;
	private LogService logService;

	private static Map<String, Object> countries;

	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("English", new Locale("en", "US")); // label, value
		countries.put("Russian", new Locale("ru", "RU"));
		countries.put("Ukrainian", new Locale("uk", "UA"));
	}

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot()
			.getLocale();

	public Locale getLocale() {
		return locale;
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	@PostConstruct
	public void init() {
		try {
			logService = (LogService) InitialContext
					.doLookup("java:module/LogService");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e) {

		String newLocaleValue = e.getNewValue().toString();

		// loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {

			if (entry.getValue().toString().equals(newLocaleValue)) {

				FacesContext.getCurrentInstance().getViewRoot()
						.setLocale((Locale) entry.getValue());
				locale = (Locale) entry.getValue();

			}
		}
		if (FacesContext.getCurrentInstance() == null) {
			logService
					.add(new Log("User: "
							+ ((User) FacesContext.getCurrentInstance()
									.getExternalContext().getSessionMap()
									.get("user")).getUsername()
							+ " change language to " + locale.toString(),
							new Timestamp((new Date().getTime()))));
		}
	}

}
