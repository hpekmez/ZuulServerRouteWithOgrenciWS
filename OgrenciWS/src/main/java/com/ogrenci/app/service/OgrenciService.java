package com.ogrenci.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ogrenci.app.bean.Ogrenci;

/**
 * Created on 01.12.2020
 */
@Service
public class OgrenciService {

	private static HashMap<Integer, Ogrenci> ogrenciHashMap = getOgrenciHashMap();

	public OgrenciService() {
		super();
		if (ogrenciHashMap == null) {

			// database yerine, hashmap kullanip verilerimizi cekecegiz
			ogrenciHashMap = new HashMap<Integer, Ogrenci>();

			Ogrenci ogrenci1 = new Ogrenci(1, "Victor", "Hugo", "Edebiyat");
			Ogrenci ogrenci2 = new Ogrenci(2, "ibni", "Sina", "Felsefe");

			ogrenciHashMap.put(1, ogrenci1);
			ogrenciHashMap.put(2, ogrenci2);
		}
	}

	public static int getMaximumId() {
		int max = 0;
		for (int id : ogrenciHashMap.keySet()) {
			if (max <= id) {
				max = id;
			}
		}
		return max;
	}

	public static HashMap<Integer, Ogrenci> getOgrenciHashMap() {
		return ogrenciHashMap;
	}

	public String getHomepage() {
		return "welcome";
	}

	public List<Ogrenci> getAllOgrenciler() {
		List<Ogrenci> ogrenciler = new ArrayList<Ogrenci>(ogrenciHashMap.values());
		return ogrenciler;
	}

	public Ogrenci getOgrenci(int id) {
		Ogrenci ogrenci = ogrenciHashMap.get(id);
		return ogrenci;
	}

	public Ogrenci ekleOgrenci(Ogrenci ogrenci) {
		ogrenci.setId(getMaximumId() + 1);
		ogrenciHashMap.put(ogrenci.getId(), ogrenci);
		return ogrenci;
	}

	public Ogrenci guncelleOgrenci(Ogrenci ogrenci) {
		if (ogrenci.getId() <= 0)
			return null;
		ogrenciHashMap.put(ogrenci.getId(), ogrenci);
		return ogrenci;
	}

	public void silOgrenci(int id) {
		ogrenciHashMap.remove(id);
	}
}
