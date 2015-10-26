package entity;

import java.io.Serializable;
import java.util.Date;


public class Masa_Sandalye_Dolap_YanginTupu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5164674128566229512L;

	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	private Integer bolgeID;

	private Date gununTarihi;

	private String lokasyon_Ofis_SubeAdi;

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

	public String getLokasyon_Ofis_SubeAdi() {
		return lokasyon_Ofis_SubeAdi;
	}

	public void setLokasyon_Ofis_SubeAdi(String lokasyon_Ofis_SubeAdi) {
		this.lokasyon_Ofis_SubeAdi = lokasyon_Ofis_SubeAdi;
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
		Masa_Sandalye_Dolap_YanginTupu other = (Masa_Sandalye_Dolap_YanginTupu) obj;
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
