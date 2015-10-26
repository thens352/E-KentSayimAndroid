package entity;

import java.io.Serializable;
import java.util.Date;


public class ValidatorOtobusTipi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2739374890488450633L;

	
	private String barkod;

	
	
	
	private Integer bolgeID;

	
	
	
	private Integer kullaniciID;

	
	private Date gununTarihi;

	
	private String cihazSeriNo;

	private String marka;

	private String model;

	private String ozellik;

	private String altTipi;

	private String kullanimSekli;

	private String otobusKoseNumarasi;

	
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

	public String getOtobusKoseNumarasi() {
		return otobusKoseNumarasi;
	}

	public void setOtobusKoseNumarasi(String otobusKoseNumarasi) {
		this.otobusKoseNumarasi = otobusKoseNumarasi;
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
		ValidatorOtobusTipi other = (ValidatorOtobusTipi) obj;
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

}
