package entity;

import java.io.Serializable;
import java.util.Date;


public class MufettisElTerminali implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5129564502762281206L;
	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	
	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String kullaniciAdiSoyadi;

	
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

	public String getKullaniciAdiSoyadi() {
		return kullaniciAdiSoyadi;
	}

	public void setKullaniciAdiSoyadi(String kullaniciAdiSoyadi) {
		this.kullaniciAdiSoyadi = kullaniciAdiSoyadi;
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
		MufettisElTerminali other = (MufettisElTerminali) obj;
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