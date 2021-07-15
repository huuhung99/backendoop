package nguyenhuuhung.b17dccn298.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang{
    @TableGenerator(name = "khach_hang",table = "tbl_khach_hang",initialValue = 100000,allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "khach_hang")
    @Column(name = "id")
    private Long maKhachHang;
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "nhom_khach_hang")
    private String nhomKhachHang;
}
