package nguyenhuuhung.b17dccn298.Repository;

import nguyenhuuhung.b17dccn298.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    @Query("select k from KhachHang k where k.hoTen like %?1%")
    List<KhachHang> findAllByHoTen(String hoTen);
}
