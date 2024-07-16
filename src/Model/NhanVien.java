package Model;
import java.sql.Date;
public class NhanVien {
    private int IDNHANVIEN;
    private String MANHANVIEN;
    private int IDCHUCVU;
    private String HOTEN;
    private Date NGAYSINH;
    private Boolean GIOITINH;
    private String DIACHI;
    private int SDT;
    private String EMAIL;
    private String MATKHAU;
    private int NGAYTAO;
    private int NGAYSUA;
    private String NGUOITAO;
    private String NGUOISUA;
    private Boolean TRANGTHAI;

    public NhanVien() {
    }

    public NhanVien(int IDNHANVIEN, String MANHANVIEN, int IDCHUCVU, String HOTEN, Date NGAYSINH, Boolean GIOITINH, String DIACHI, int SDT, String EMAIL, String MATKHAU, int NGAYTAO, int NGAYSUA, String NGUOITAO, String NGUOISUA, Boolean TRANGTHAI) {
        this.IDNHANVIEN = IDNHANVIEN;
        this.MANHANVIEN = MANHANVIEN;
        this.IDCHUCVU = IDCHUCVU;
        this.HOTEN = HOTEN;
        this.NGAYSINH = NGAYSINH;
        this.GIOITINH = GIOITINH;
        this.DIACHI = DIACHI;
        this.SDT = SDT;
        this.EMAIL = EMAIL;
        this.MATKHAU = MATKHAU;
        this.NGAYTAO = NGAYTAO;
        this.NGAYSUA = NGAYSUA;
        this.NGUOITAO = NGUOITAO;
        this.NGUOISUA = NGUOISUA;
        this.TRANGTHAI = TRANGTHAI;
    }

    public int getIDNHANVIEN() {
        return IDNHANVIEN;
    }

    public void setIDNHANVIEN(int IDNHANVIEN) {
        this.IDNHANVIEN = IDNHANVIEN;
    }

    public String getMANHANVIEN() {
        return MANHANVIEN;
    }

    public void setMANHANVIEN(String MANHANVIEN) {
        this.MANHANVIEN = MANHANVIEN;
    }

    public int getIDCHUCVU() {
        return IDCHUCVU;
    }

    public void setIDCHUCVU(int IDCHUCVU) {
        this.IDCHUCVU = IDCHUCVU;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public Date getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(Date NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public Boolean getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(Boolean GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public int getNGAYTAO() {
        return NGAYTAO;
    }

    public void setNGAYTAO(int NGAYTAO) {
        this.NGAYTAO = NGAYTAO;
    }

    public int getNGAYSUA() {
        return NGAYSUA;
    }

    public void setNGAYSUA(int NGAYSUA) {
        this.NGAYSUA = NGAYSUA;
    }

    public String getNGUOITAO() {
        return NGUOITAO;
    }

    public void setNGUOITAO(String NGUOITAO) {
        this.NGUOITAO = NGUOITAO;
    }

    public String getNGUOISUA() {
        return NGUOISUA;
    }

    public void setNGUOISUA(String NGUOISUA) {
        this.NGUOISUA = NGUOISUA;
    }

    public Boolean getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(Boolean TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
}
