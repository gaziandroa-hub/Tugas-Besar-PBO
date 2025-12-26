import java.time.LocalDate;

public class ObjekGeologi {

    private String nama;
    private String lokasi;
    private String jenis;
    private LocalDate tanggalInput;

    public ObjekGeologi(String nama, String lokasi, String jenis) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.jenis = jenis;
        this.tanggalInput = LocalDate.now();
    }

    public String getNama() {
        return nama.toUpperCase();
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getJenis() {
        return jenis;
    }

    public LocalDate getTanggalInput() {
        return tanggalInput;
    }
}
