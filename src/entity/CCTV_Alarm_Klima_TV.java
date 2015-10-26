package entity;

import java.io.Serializable;
import java.util.Date;


public class CCTV_Alarm_Klima_TV implements Serializable {

	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -1811474502290515816L;

	
	private String barkod;

	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String bulunduguOfis_SubeAdi;

	private String cesit;

	
	private String durum;

	public String getCesit() {
		return cesit;
	}

	public void setCesit(String cesit) {
		this.cesit = cesit;
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
		CCTV_Alarm_Klima_TV other = (CCTV_Alarm_Klima_TV) obj;
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
