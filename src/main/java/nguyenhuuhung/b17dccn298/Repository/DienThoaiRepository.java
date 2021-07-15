package nguyenhuuhung.b17dccn298.Repository;

import nguyenhuuhung.b17dccn298.model.DienThoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DienThoaiRepository extends JpaRepository<DienThoai, Long> {
    @Query("select d from DienThoai d where d.tenDienThoai like %?1%")
    List<DienThoai> findAllByTen(String keyword);
}
