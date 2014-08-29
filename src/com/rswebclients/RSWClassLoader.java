package com.rswebclients;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RSWClassLoader extends URLClassLoader{
	
	public static RSWClassLoader getRSWJar(String jar) throws MalformedURLException {
		return new RSWClassLoader(String.format("http://cdn.rsw.rs/files/jar/dl/%s.jar", jar.replaceAll(" ", "%20")));
	}
	
	public static RSWClassLoader getExternalJar(String jar) throws Exception {
		return new RSWClassLoader(getJarLocation(jar));
	}
	
	public RSWClassLoader(String url) throws MalformedURLException {
		super(new URL[]{new URL(url)});
	}
	
	public static String getJarLocation(String serverName) throws Exception{
		final Document doc = Jsoup.connect("http://rswebclients.com/info/"+serverName).timeout(60000).get();
		final Elements elements = doc.select("a[href]");
		for(Element e : elements) {
			final String link = e.attr("abs:href");
			if(link.endsWith(".jar") || link.toLowerCase().endsWith("server="+serverName)) {
				return formatForLoad(link);
			}
		}
		for(Element e : doc.getElementsByTag("a")) {
			if(!e.text().equalsIgnoreCase("Visit the Website"))
				continue;
			final String url = e.attr("href");
			return getJarLocationFromOL(url.replaceAll("www.rswebclients.com/l/", ""));
		}
		return null;
	}
	
	public static final String getJarLocationFromOL(String url) throws Exception {
		final Document doc = Jsoup.connect(url).timeout(60000).
				userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36").get();
		final Elements elements = doc.select("a[href]");
		for(Element e : elements) {
			final String link = e.attr("abs:href");
			if(link.endsWith(".jar")) {
				return link.replaceAll(" ", "%20");
			}
		}
		return null;
	}
	
	public static String formatForLoad(String link) {
		link = link.replaceAll("www.rswebclients.com/l/", "");
		if(link.contains("dropbox")) {
			//link = link.replaceAll("?dl=0", "");
			link = link + "?dl=1";
			link = link.replaceAll("http:", "https:");
		}
		return link;
	}
}
