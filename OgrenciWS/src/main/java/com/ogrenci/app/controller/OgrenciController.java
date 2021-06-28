package com.ogrenci.app.controller;

import com.ogrenci.app.bean.Ogrenci;
import com.ogrenci.app.service.OgrenciService;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 01.12.2020
 */
@RestController
public class OgrenciController {

	@Autowired
	OgrenciService ogrenciService;

	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getHomepage() {
		String home = ogrenciService.getHomepage();
		return home;
	}

	@RequestMapping(value = "/ogrenciler", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Ogrenci> getOgrenci() {
		List<Ogrenci> ogrenciListesi = ogrenciService.getAllOgrenciler();
		return ogrenciListesi;
	}

	@RequestMapping(value = "/ogrenci/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Ogrenci getOgrenciById(@PathVariable int id) {
		return ogrenciService.getOgrenci(id);
	}

	@RequestMapping(value = "/ogrenciler", method = RequestMethod.POST, headers = "Accept=application/json")
	public Ogrenci ekleOgrenci(@RequestBody Ogrenci ogrenci) {
		return ogrenciService.ekleOgrenci(ogrenci);
	}

	@RequestMapping(value = "/ogrenciEkle", method = RequestMethod.GET, headers = "Accept=application/json")
	public Ogrenci ekleOgrenci2() {

		Ogrenci ogrenci1 = new Ogrenci();
		ogrenci1.setAd("hasan");
		ogrenci1.setSoyad("pekmez");
		ogrenci1.setAlan("Software");
		Random random = new Random();
		ogrenci1.setId(random.nextInt(100) );

		// ilgili endpointe istek atilir ve zuul tarafindan ogrenci-ws uygulamasindaki
		// endpointe yonlendirilir
		//// call ws endpoint after zull routes
		RestTemplate restTemplate = new RestTemplate();
		Ogrenci ogrenciSonuc = restTemplate.postForObject("http://localhost:8091/Api/ogrenciler", ogrenci1,
				Ogrenci.class);

		return ogrenciSonuc;

	}

	@RequestMapping(value = "/ogrenciler", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Ogrenci guncelleOgrenci(@RequestBody Ogrenci ogrenci) {
		return ogrenciService.guncelleOgrenci(ogrenci);
	}

	@RequestMapping(value = "/ogrenci/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void silOgrenci(@PathVariable("id") int id) {
		ogrenciService.silOgrenci(id);
	}

}
