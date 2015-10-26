package entity;

import java.io.Serializable;
import java.util.Date;


public class UPS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4687433520616515021L;
	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String uretici;

	private String guc;

	private String sonAkuDegisimTarihi;

	private String lokasyon_Ofis_SubeAdi;

	private String gpsKoordinati;

	private String garantiBaslamaTarihi;

	private String garantiBitisTarihi;

	private String hizmetVerenTeknikServis;

	private String bulunduguOda;

	
	private String durum;

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

	public String getUretici() {
		return uretici;
	}

	public void setUretici(String uretici) {
		this.uretici = uretici;
	}

	public String getGuc() {
		return guc;
	}

	public void setGuc(String guc) {
		this.guc = guc;
	}

	public String getSonAkuDegisimTarihi() {
		return sonAkuDegisimTarihi;
	}

	public void setSonAkuDegisimTarihi(String sonAkuDegisimTarihi) {
		this.sonAkuDegisimTarihi = sonAkuDegisimTarihi;
	}

	public String getLokasyon_Ofis_SubeAdi() {
		return lokasyon_Ofis_SubeAdi;
	}

	public void setLokasyon_Ofis_SubeAdi(String lokasyon_Ofis_SubeAdi) {
		this.lokasyon_Ofis_SubeAdi = lokasyon_Ofis_SubeAdi;
	}

	public String getGpsKoordinati() {
		return gpsKoordinati;
	}

	public void setGpsKoordinati(String gpsKoordinati) {
		this.gpsKoordinati = gpsKoordinati;
	}

	public String getGarantiBaslamaTarihi() {
		return garantiBaslamaTarihi;
	}

	public void setGarantiBaslamaTarihi(String garantiBaslamaTarihi) {
		this.garantiBaslamaTarihi = garantiBaslamaTarihi;
	}

	public String getGarantiBitisTarihi() {
		return garantiBitisTarihi;
	}

	public void setGarantiBitisTarihi(String garantiBitisTarihi) {
		this.garantiBitisTarihi = garantiBitisTarihi;
	}

	public String getHizmetVerenTeknikServis() {
		return hizmetVerenTeknikServis;
	}

	public void setHizmetVerenTeknikServis(String hizmetVerenTeknikServis) {
		this.hizmetVerenTeknikServis = hizmetVerenTeknikServis;
	}

	public String getBulunduguOda() {
		return bulunduguOda;
	}

	public void setBulunduguOda(String bulunduguOda) {
		this.bulunduguOda = bulunduguOda;
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
		UPS other = (UPS) obj;
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
