package entity;

import java.io.Serializable;
import java.util.Date;


public class NcSwitch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1371012940911659272L;

	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String gpsKoordinat;

	private String adresi;

	private String lokasyon_Ofis_SubeAdi;

	private String hangiOdada;

	private String portSayisi;

	private int anaEkipman;

	
	private String durum;

	public Date getGununTarihi() {
		return gununTarihi;
	}

	public void setGununTarihi(Date gununTarihi) {
		this.gununTarihi = gununTarihi;
	}

	public int getAnaEkipman() {
		return anaEkipman;
	}

	public void setAnaEkipman(int anaEkipman) {
		this.anaEkipman = anaEkipman;
	}

	public Integer getBolgeID() {
		return bolgeID;
	}

	public void setBolgeID(Integer bolgeID) {
		this.bolgeID = bolgeID;
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

	public String getGpsKoordinat() {
		return gpsKoordinat;
	}

	public void setGpsKoordinat(String gpsKoordinat) {
		this.gpsKoordinat = gpsKoordinat;
	}

	public String getAdresi() {
		return adresi;
	}

	public void setAdresi(String adresi) {
		this.adresi = adresi;
	}

	public String getLokasyon_Ofis_SubeAdi() {
		return lokasyon_Ofis_SubeAdi;
	}

	public void setLokasyon_Ofis_SubeAdi(String lokasyon_Ofis_SubeAdi) {
		this.lokasyon_Ofis_SubeAdi = lokasyon_Ofis_SubeAdi;
	}

	public String getHangiOdada() {
		return hangiOdada;
	}

	public void setHangiOdada(String hangiOdada) {
		this.hangiOdada = hangiOdada;
	}

	public String getPortSayisi() {
		return portSayisi;
	}

	public void setPortSayisi(String portSayisi) {
		this.portSayisi = portSayisi;
	}

	@Override
	public String toString() {
		return "NcSwitch [barkod=" + barkod + ", kullaniciID=" + kullaniciID
				+ ", bolgeID=" + bolgeID + ", gununTarihi=" + gununTarihi
				+ ", cihazSeriNo=" + cihazSeriNo + ", marka=" + marka
				+ ", model=" + model + ", gpsKoordinat=" + gpsKoordinat
				+ ", adresi=" + adresi + ", lokasyon_Ofis_SubeAdi="
				+ lokasyon_Ofis_SubeAdi + ", hangiOdada=" + hangiOdada
				+ ", portSayisi=" + portSayisi + ", anaEkipman=" + anaEkipman
				+ ", durum=" + durum + "]";
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresi == null) ? 0 : adresi.hashCode());
		result = prime * result + anaEkipman;
		result = prime * result + ((barkod == null) ? 0 : barkod.hashCode());
		result = prime * result + ((bolgeID == null) ? 0 : bolgeID.hashCode());
		result = prime * result
				+ ((cihazSeriNo == null) ? 0 : cihazSeriNo.hashCode());
		result = prime * result + ((durum == null) ? 0 : durum.hashCode());
		result = prime * result
				+ ((gpsKoordinat == null) ? 0 : gpsKoordinat.hashCode());
		result = prime * result
				+ ((gununTarihi == null) ? 0 : gununTarihi.hashCode());
		result = prime * result
				+ ((hangiOdada == null) ? 0 : hangiOdada.hashCode());
		result = prime * result
				+ ((kullaniciID == null) ? 0 : kullaniciID.hashCode());
		result = prime
				* result
				+ ((lokasyon_Ofis_SubeAdi == null) ? 0 : lokasyon_Ofis_SubeAdi
						.hashCode());
		result = prime * result + ((marka == null) ? 0 : marka.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result
				+ ((portSayisi == null) ? 0 : portSayisi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NcSwitch other = (NcSwitch) obj;
		if (adresi == null) {
			if (other.adresi != null)
				return false;
		} else if (!adresi.equals(other.adresi))
			return false;
		if (anaEkipman != other.anaEkipman)
			return false;
		if (barkod == null) {
			if (other.barkod != null)
				return false;
		} else if (!barkod.equals(other.barkod))
			return false;
		if (bolgeID == null) {
			if (other.bolgeID != null)
				return false;
		} else if (!bolgeID.equals(other.bolgeID))
			return false;
		if (cihazSeriNo == null) {
			if (other.cihazSeriNo != null)
				return false;
		} else if (!cihazSeriNo.equals(other.cihazSeriNo))
			return false;
		if (durum == null) {
			if (other.durum != null)
				return false;
		} else if (!durum.equals(other.durum))
			return false;
		if (gpsKoordinat == null) {
			if (other.gpsKoordinat != null)
				return false;
		} else if (!gpsKoordinat.equals(other.gpsKoordinat))
			return false;
		if (gununTarihi == null) {
			if (other.gununTarihi != null)
				return false;
		} else if (!gununTarihi.equals(other.gununTarihi))
			return false;
		if (hangiOdada == null) {
			if (other.hangiOdada != null)
				return false;
		} else if (!hangiOdada.equals(other.hangiOdada))
			return false;
		if (kullaniciID == null) {
			if (other.kullaniciID != null)
				return false;
		} else if (!kullaniciID.equals(other.kullaniciID))
			return false;
		if (lokasyon_Ofis_SubeAdi == null) {
			if (other.lokasyon_Ofis_SubeAdi != null)
				return false;
		} else if (!lokasyon_Ofis_SubeAdi.equals(other.lokasyon_Ofis_SubeAdi))
			return false;
		if (marka == null) {
			if (other.marka != null)
				return false;
		} else if (!marka.equals(other.marka))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (portSayisi == null) {
			if (other.portSayisi != null)
				return false;
		} else if (!portSayisi.equals(other.portSayisi))
			return false;
		return true;
	}

	public Integer getKullaniciID() {
		return kullaniciID;
	}

	public void setKullaniciID(Integer kullaniciID) {
		this.kullaniciID = kullaniciID;
	}

	public String getBarkod() {
		return barkod;
	}

	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}
}
