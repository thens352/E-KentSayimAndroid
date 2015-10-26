package entity;

import java.io.Serializable;
import java.util.Date;


public class KsKamera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8071846352480338758L;
	
	private String barkod;

	
	
	
	private Integer kullaniciID;

	
	
	
	private Integer bolgeID;

	private Date gununTarihi;

	@Override
	public String toString() {
		return "KsKamera [barkod=" + barkod + ", kullaniciID=" + kullaniciID
				+ ", bolgeID=" + bolgeID + ", gununTarihi=" + gununTarihi
				+ ", cihazSeriNo=" + cihazSeriNo + ", marka=" + marka
				+ ", otobusKoseNo=" + otobusKoseNo + ", kameraninYeri="
				+ kameraninYeri + ", termId=" + termId + ", durum=" + durum
				+ "]";
	}

	private String cihazSeriNo;

	private String marka;

	private String otobusKoseNo;

	private String kameraninYeri;

	private String termId;// Ekranda olmayacak

	
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

	public String getOtobusKoseNo() {
		return otobusKoseNo;
	}

	public void setOtobusKoseNo(String otobusKoseNo) {
		this.otobusKoseNo = otobusKoseNo;
	}

	public String getKameraninYeri() {
		return kameraninYeri;
	}

	public void setKameraninYeri(String kameraninYeri) {
		this.kameraninYeri = kameraninYeri;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
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
		result = prime * result + ((barkod == null) ? 0 : barkod.hashCode());
		result = prime * result + ((bolgeID == null) ? 0 : bolgeID.hashCode());
		result = prime * result
				+ ((cihazSeriNo == null) ? 0 : cihazSeriNo.hashCode());
		result = prime * result + ((durum == null) ? 0 : durum.hashCode());
		result = prime * result
				+ ((gununTarihi == null) ? 0 : gununTarihi.hashCode());
		result = prime * result
				+ ((kameraninYeri == null) ? 0 : kameraninYeri.hashCode());
		result = prime * result
				+ ((kullaniciID == null) ? 0 : kullaniciID.hashCode());
		result = prime * result + ((marka == null) ? 0 : marka.hashCode());
		result = prime * result
				+ ((otobusKoseNo == null) ? 0 : otobusKoseNo.hashCode());
		result = prime * result + ((termId == null) ? 0 : termId.hashCode());
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
		KsKamera other = (KsKamera) obj;
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
		if (gununTarihi == null) {
			if (other.gununTarihi != null)
				return false;
		} else if (!gununTarihi.equals(other.gununTarihi))
			return false;
		if (kameraninYeri == null) {
			if (other.kameraninYeri != null)
				return false;
		} else if (!kameraninYeri.equals(other.kameraninYeri))
			return false;
		if (kullaniciID == null) {
			if (other.kullaniciID != null)
				return false;
		} else if (!kullaniciID.equals(other.kullaniciID))
			return false;
		if (marka == null) {
			if (other.marka != null)
				return false;
		} else if (!marka.equals(other.marka))
			return false;
		if (otobusKoseNo == null) {
			if (other.otobusKoseNo != null)
				return false;
		} else if (!otobusKoseNo.equals(other.otobusKoseNo))
			return false;
		if (termId == null) {
			if (other.termId != null)
				return false;
		} else if (!termId.equals(other.termId))
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
