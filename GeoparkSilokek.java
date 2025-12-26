import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

// Class utama program
// extends DataBaseConfig untuk koneksi database
// implements CrudService untuk CRUD
public class GeoparkSiilokek extends DataBaseConfig implements CrudService {

    Scanner input = new Scanner(System.in);
    ArrayList<ObjekGeologi> list = new ArrayList<>();

    // Method utama
    public static void main(String[] args) {
        GeoparkSiilokek app = new GeoparkSiilokek();
        app.menu();
    }

    // Menu utama program
    void menu() {
        int pilih;
        do {
            System.out.println("\n=== SISTEM INFORMASI GEOPARK SILOKEK ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampil Data");
            System.out.println("3. Update Data");
            System.out.println("4. Hapus Data");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 1:
                    tambah();
                    break;
                case 2:
                    tampil();
                    break;
                case 3:
                    ubah();
                    break;
                case 4:
                    hapus();
                    break;
                case 0:
                    System.out.println("Program selesai");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia");
            }
        } while (pilih != 0);
    }

    // Menambah data objek geologi
    @Override
    public void tambah() {
        try {
            System.out.print("Nama Objek: ");
            String nama = input.nextLine();
            System.out.print("Lokasi: ");
            String lokasi = input.nextLine();
            System.out.print("Jenis: ");
            String jenis = input.nextLine();

            // Membuat objek
            ObjekGeologi og = new ObjekGeologi(nama, lokasi, jenis);
            list.add(og);

            Connection conn = getConnection();
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO objek_geologi (nama_objek, lokasi, jenis) VALUES ('"
                    + og.getNama() + "','" + og.getLokasi() + "','" + og.getJenis() + "')";
            stmt.executeUpdate(sql);

            System.out.println("Data berhasil ditambahkan");
            System.out.println("Tanggal input: " + LocalDate.now());

            conn.close();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    // Menampilkan data dari database
    @Override
    public void tampil() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM objek_geologi");

            int total = 0;
            System.out.println("\nID | Nama | Lokasi | Jenis");

            while (rs.next()) {
                total++;
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("nama_objek") + " | " +
                        rs.getString("lokasi") + " | " +
                        rs.getString("jenis")
                );
            }
            System.out.println("Total data: " + total);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mengubah data berdasarkan ID
    @Override
    public void ubah() {
        try {
            System.out.print("ID yang diubah: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.print("Nama baru: ");
            String nama = input.nextLine();

            Connection conn = getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(
                    "UPDATE objek_geologi SET nama_objek='" + nama + "' WHERE id=" + id
            );

            System.out.println("Data berhasil diupdate");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Menghapus data berdasarkan ID
    @Override
    public void hapus() {
        try {
            System.out.print("ID yang dihapus: ");
            int id = input.nextInt();

            Connection conn = getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("DELETE FROM objek_geologi WHERE id=" + id);
            System.out.println("Data berhasil dihapus");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
