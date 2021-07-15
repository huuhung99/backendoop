package nguyenhuuhung.b17dccn298.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item{
    @TableGenerator(name = "item",table = "tbl_item",initialValue = 100000,allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "item")
    private Long id;
    private Float giaBan;
    private Integer soLuong;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dien_thoai_id")
    private DienThoai dienThoai;
//    @ManyToOne
//    @JoinColumn(name = "hoa_don_id") // thông qua khóa ngoại id
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private HoaDon hoaDon;
    @Column(name = "hoa_don_id")
    private Long hoaDon;
}
