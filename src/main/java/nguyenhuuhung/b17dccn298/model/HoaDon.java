package nguyenhuuhung.b17dccn298.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon{
    @TableGenerator(name = "hoa_don",table = "tbl_hoa_don",initialValue = 100000,allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hoa_don")
    @Column(name = "id")
    private Long maHoaDon;
    private Date thoiGianTaoHoaDon;
    @OneToOne
    private KhachHang khachHang;
    @OneToMany(mappedBy = "hoaDon")
    private List<Item> item;
}
