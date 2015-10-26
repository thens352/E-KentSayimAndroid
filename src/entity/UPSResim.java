package entity;

import java.io.Serializable;
import java.util.Date;


public class UPSResim implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5797639422342285062L;

	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	private Date gununTarihi;

	private String upsResimDosyasi;

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

	public String getUpsResimDosyasi() {
		return upsResimDosyasi;
	}

	public void setUpsResimDosyasi(String upsResimDosyasi) {
		this.upsResimDosyasi = upsResimDosyasi;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UPSResim other = (UPSResim) obj;
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
