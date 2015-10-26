package entity;

import java.io.Serializable;
import java.util.Date;


public class POS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2092756951013139820L;
	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String gpsKoordinati;

	private String adres;

	private String bulunduguBayiTicariAdi;

	private String bulunduguBayiVergiNo;

	private String bulunduguBayiVergiDairesi;

	private boolean padCihaziVarMi;

	private String padCihazSeriNo;

	private String anaEkipman;

	
	private String durum;

	public String getBulunduguBayiVergiDairesi() {
		return bulunduguBayiVergiDairesi;
	}

	public void setBulunduguBayiVergiDairesi(String bulunduguBayiVergiDairesi) {
		this.bulunduguBayiVergiDairesi = bulunduguBayiVergiDairesi;
	}

	public String getAnaEkipman() {
		return anaEkipman;
	}

	public void setAnaEkipman(String anaEkipman) {
		this.anaEkipman = anaEkipman;
	}

	public Integer getBolgeID() {
		return bolgeID;
	}

	public void setBolgeID(Integer bolgeID) {
		this.bolgeID = bolgeID;
	}

	public Date getGununTarihi() {
		return gununTarihi;
	}

	public void setGununTarihi(Date gununTarihi) {
		this.gununTarihi = gununTarihi;
	}

	public String getBarkod() {
		return barkod;
	}

	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}

	public String getCihazSeriNo() {
		return cihazSeriNo;
	}

	public void setCihazSeriNo(String cihazSeriNo) {
		this.cihazSeriNo = cihazSeriNo;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getGpsKoordinati() {
		return gpsKoordinati;
	}

	public void setGpsKoordinati(String gpsKoordinati) {
		this.gpsKoordinati = gpsKoordinati;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getBulunduguBayiTicariAdi() {
		return bulunduguBayiTicariAdi;
	}

	public void setBulunduguBayiTicariAdi(String bulunduguBayiTicariAdi) {
		this.bulunduguBayiTicariAdi = bulunduguBayiTicariAdi;
	}

	public String getBulunduguBayiVergiNo() {
		return bulunduguBayiVergiNo;
	}

	public void setBulunduguBayiVergiNo(String bulunduguBayiVergiNo) {
		this.bulunduguBayiVergiNo = bulunduguBayiVergiNo;
	}

	public boolean isPadCihaziVarMi() {
		return padCihaziVarMi;
	}

	public void setPadCihaziVarMi(boolean padCihaziVarMi) {
		this.padCihaziVarMi = padCihaziVarMi;
	}

	public String getPadCihazSeriNo() {
		return padCihazSeriNo;
	}

	public void setPadCihazSeriNo(String padCihazSeriNo) {
		this.padCihazSeriNo = padCihazSeriNo;
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POS other = (POS) obj;
		if (barkod != other.barkod)
			return false;
		return true;
	}


	public Integer getKullaniciID() {
		return kullaniciID;
	}

	public void setKullaniciID(Integer kullaniciID) {
		this.kullaniciID = kullaniciID;
	}

}
