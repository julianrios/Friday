package com.sandbox.friday;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sandbox.friday.webscraper.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FridayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FridayApplication.class, args);

		// TODO scrape for multiple queries or add some sort of UI
		String searchQuery = "1967 Chevy Impala";
		WebScraper scraper = new WebScraper();
		scraper.scrape(searchQuery);
	}

}
