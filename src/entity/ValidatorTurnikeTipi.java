package entity;

import java.io.Serializable;
import java.util.Date;


public class ValidatorTurnikeTipi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4983831652269000123L;

	
	private String barkod;

	
	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String ozellik;

	private String altTipi;

	private String kullanimSekli;

	private String adres;

	private String gpsKoordinati;

	private String istasyon_DurakAdi;

	private String turnikeNo;

	
	private String durum;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	public String getTurnikeNo() {
		return turnikeNo;
	}

	public void setTurnikeNo(String turnikeNo) {
		this.turnikeNo = turnikeNo;
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

	public String getOzellik() {
		return ozellik;
	}

	public void setOzellik(String ozellik) {
		this.ozellik = ozellik;
	}

	public String getAltTipi() {
		return altTipi;
	}

	public void setAltTipi(String altTipi) {
		this.altTipi = altTipi;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getGpsKoordinati() {
		return gpsKoordinati;
	}

	public void setGpsKoordinati(String gpsKoordinati) {
		this.gpsKoordinati = gpsKoordinati;
	}

	public String getIstasyon_DurakAdi() {
		return istasyon_DurakAdi;
	}

	public void setIstasyon_DurakAdi(String istasyon_DurakAdi) {
		this.istasyon_DurakAdi = istasyon_DurakAdi;
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
		ValidatorTurnikeTipi other = (ValidatorTurnikeTipi) obj;
		if (barkod != other.barkod)
			return false;
		return true;
	}

	public String getKullanimSekli() {
		return kullanimSekli;
	}

	public void setKullanimSekli(String kullanimSekli) {
		this.kullanimSekli = kullanimSekli;
	}

	public Integer getKullaniciID() {
		return kullaniciID;
	}

	public void setKullaniciID(Integer kullaniciID) {
		this.kullaniciID = kullaniciID;
	}

	public Integer getBolgeID() {
		return bolgeID;
	}

	public void setBolgeID(Integer bolgeID) {
		this.bolgeID = bolgeID;
	}

}
