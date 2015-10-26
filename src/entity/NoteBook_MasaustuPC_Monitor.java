package entity;

import java.io.Serializable;
import java.util.Date;


public class NoteBook_MasaustuPC_Monitor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7159042017975222593L;

	/**
	 * 
	 */

	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String lokasyon_Ofis_SubeAdi;

	private String productNo;

	private String urunSahibi;

	private String cesit;

	private String uzerindekiIsletimSistemi;

	
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

	public String getLokasyon_Ofis_SubeAdi() {
		return lokasyon_Ofis_SubeAdi;
	}

	public void setLokasyon_Ofis_SubeAdi(String lokasyon_Ofis_SubeAdi) {
		this.lokasyon_Ofis_SubeAdi = lokasyon_Ofis_SubeAdi;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getUrunSahibi() {
		return urunSahibi;
	}

	public void setUrunSahibi(String urunSahibi) {
		this.urunSahibi = urunSahibi;
	}

	public String getCesit() {
		return cesit;
	}

	public void setCesit(String cesit) {
		this.cesit = cesit;
	}

	public String getUzerindekiIsletimSistemi() {
		return uzerindekiIsletimSistemi;
	}

	public void setUzerindekiIsletimSistemi(String uzerindekiIsletimSistemi) {
		this.uzerindekiIsletimSistemi = uzerindekiIsletimSistemi;
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
		NoteBook_MasaustuPC_Monitor other = (NoteBook_MasaustuPC_Monitor) obj;
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
