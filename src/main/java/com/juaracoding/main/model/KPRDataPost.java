package com.juaracoding.main.model;

public class KPRDataPost {
	
	private String df;
	private int platfond ;
    private double bunga ;
    private int lamapijaman ;
    
  
    
    public KPRDataPost() {
    	
    }


	public KPRDataPost(String df, int lamapijaman, int platfond, double bunga) {
		super();
		this.df = df;
		this.platfond = platfond;
		this.bunga = bunga;
		this.lamapijaman = lamapijaman;
		
	}


	public String getDf() {
		return df;
	}


	public void setDf(String df) {
		this.df = df;
	}


	public int getLamapijaman() {
		return lamapijaman;
	}


	public void setLamapijaman(int lamapijaman) {
		this.lamapijaman = lamapijaman;
	}


	public int getPlatfond() {
		return platfond;
	}


	public void setPlatfond(int platfond) {
		this.platfond = platfond;
	}


	public double getBunga() {
		return bunga;
	}


	public void setBunga(double bunga) {
		this.bunga = bunga;
	}
    
    
    
    
	

}