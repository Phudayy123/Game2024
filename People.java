package CSDL;

public class People {
    private String TenNguoiChoi;
    private int Diem;
	
    
    
    @Override
	public String toString() {
		return "People [TenNguoiChoi=" + TenNguoiChoi + ", Diem=" + Diem + "]";
	}

	public String getTenNguoiChoi() {
		return TenNguoiChoi;
	}

	public void setTenNguoiChoi(String tenNguoiChoi) {
		TenNguoiChoi = tenNguoiChoi;
	}

	public int getDiem() {
		return Diem;
	}

	public void setDiem(int diem) {
		Diem = diem;
	}


	public People() {
		super();
	}

	public People(String tenNguoiChoi, int diem) {
		super();
		TenNguoiChoi = tenNguoiChoi;
		Diem = diem;
	}
	
    
    
   
    
    
     
     
}
