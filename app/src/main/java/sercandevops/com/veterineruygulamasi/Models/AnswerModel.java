package sercandevops.com.veterineruygulamasi.Models;

public class AnswerModel
{
	private String cevapid;
	private String cevap;
	private boolean tf;
	private String soruid;
	private String soru;


	public String getCevapid() {
		return cevapid;
	}

	public void setCevapid(String cevapid) {
		this.cevapid = cevapid;
	}

	public String getCevap() {
		return cevap;
	}

	public void setCevap(String cevap) {
		this.cevap = cevap;
	}

	public boolean isTf() {
		return tf;
	}

	public void setTf(boolean tf) {
		this.tf = tf;
	}

	public String getSoruid() {
		return soruid;
	}

	public void setSoruid(String soruid) {
		this.soruid = soruid;
	}

	public String getSoru() {
		return soru;
	}

	public void setSoru(String soru) {
		this.soru = soru;
	}

	@Override
 	public String toString(){
		return 
			"AnswerModel{" + 
			"cevapid = '" + cevapid + '\'' + 
			",cevap = '" + cevap + '\'' + 
			",tf = '" + tf + '\'' + 
			",soruid = '" + soruid + '\'' + 
			",soru = '" + soru + '\'' + 
			"}";
		}
}
