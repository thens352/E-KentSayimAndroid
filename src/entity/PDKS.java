package entity;

import java.io.Serializable;
import java.util.Date;


public class PDKS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4870579169717531060L;

	
	private String barkod;

	private Date gununTarihi;

	
	
	
	private Integer bolgeID;

	
	
	
	private Integer kullaniciID;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String bulunduguOfis_SubeAdi;

	
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

	public String getBulunduguOfis_SubeAdi() {
		return bulunduguOfis_SubeAdi;
	}

	public void setBulunduguOfis_SubeAdi(String bulunduguOfis_SubeAdi) {
		this.bulunduguOfis_SubeAdi = bulunduguOfis_SubeAdi;
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
		PDKS other = (PDKS) obj;
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
