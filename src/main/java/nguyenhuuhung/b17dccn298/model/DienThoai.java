package nguyenhuuhung.b17dccn298.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DienThoai{
    @TableGenerator(name = "dien_thoai",table = "tbl_dien_thoai",initialValue = 100000,allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "dien_thoai")
    protected Long maDienThoai;
    protected String tenDienThoai;
    protected String hangSanXuat;
    protected String model;
    protected Float donGia;
    @OneToOne(mappedBy = "dienThoai")
    protected Item item;
}
