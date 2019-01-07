package sercandevops.com.veterineruygulamasi.Models;

public class AsiModel{
	private boolean tf;
	private String petid;
	private String petresim;
	private String pettur;
	private Object asitarih;
	private Object asiisim;
	private String petisim;
	private String petcins;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setPetid(String petid){
		this.petid = petid;
	}

	public String getPetid(){
		return petid;
	}

	public void setPetresim(String petresim){
		this.petresim = petresim;
	}

	public String getPetresim(){
		return petresim;
	}

	public void setPettur(String pettur){
		this.pettur = pettur;
	}

	public String getPettur(){
		return pettur;
	}

	public void setAsitarih(Object asitarih){
		this.asitarih = asitarih;
	}

	public Object getAsitarih(){
		return asitarih;
	}

	public void setAsiisim(Object asiisim){
		this.asiisim = asiisim;
	}

	public Object getAsiisim(){
		return asiisim;
	}

	public void setPetisim(String petisim){
		this.petisim = petisim;
	}

	public String getPetisim(){
		return petisim;
	}

	public void setPetcins(String petcins){
		this.petcins = petcins;
	}

	public String getPetcins(){
		return petcins;
	}

	@Override
 	public String toString(){
		return 
			"AsiModel{" + 
			"tf = '" + tf + '\'' + 
			",petid = '" + petid + '\'' + 
			",petresim = '" + petresim + '\'' + 
			",pettur = '" + pettur + '\'' + 
			",asitarih = '" + asitarih + '\'' + 
			",asiisim = '" + asiisim + '\'' + 
			",petisim = '" + petisim + '\'' + 
			",petcins = '" + petcins + '\'' + 
			"}";
		}
}
