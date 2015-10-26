package entity;

import java.io.Serializable;
import java.util.Date;


public class LCD implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5421508215780353141L;

	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String gpsKoordinat;

	private String adresi;

	private String productNo;

	private String lokasyon_Ofis_SubeAdi;

	private String ekranBoyutu;

	
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

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getLokasyon_Ofis_SubeAdi() {
		return lokasyon_Ofis_SubeAdi;
	}

	public void setLokasyon_Ofis_SubeAdi(String lokasyon_Ofis_SubeAdi) {
		this.lokasyon_Ofis_SubeAdi = lokasyon_Ofis_SubeAdi;
	}

	public String getEkranBoyutu() {
		return ekranBoyutu;
	}

	public void setEkranBoyutu(String ekranBoyutu) {
		this.ekranBoyutu = ekranBoyutu;
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
		LCD other = (LCD) obj;
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
