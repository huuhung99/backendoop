package nguyenhuuhung.b17dccn298.service;

import nguyenhuuhung.b17dccn298.Repository.DienThoaiRepository;
import nguyenhuuhung.b17dccn298.error.BadRequestException;
import nguyenhuuhung.b17dccn298.model.DienThoai;
import nguyenhuuhung.b17dccn298.model.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DienThoaiService {
    @Autowired
    private DienThoaiRepository dienThoaiRepository;

    public DienThoai createOrUpdate(DienThoai dienThoai){
            if(dienThoai.getMaDienThoai()!=null){
                Optional<DienThoai> byId = dienThoaiRepository.findById(dienThoai.getMaDienThoai());
                if(!byId.isPresent()){
                    throw new BadRequestException("id không tồn tại!");
                }
                DienThoai tmp = byId.get();
                if(dienThoai.getTenDienThoai()!=null&&!dienThoai.getTenDienThoai().isBlank()){
                    tmp.setTenDienThoai(dienThoai.getTenDienThoai());
                }
                if(dienThoai.getDonGia()!=null){
                    tmp.setDonGia(dienThoai.getDonGia());
                }
                if(dienThoai.getHangSanXuat()!=null&&!dienThoai.getHangSanXuat().isBlank()){
                    tmp.setHangSanXuat(dienThoai.getHangSanXuat());
                }
                if(dienThoai.getModel()!=null&&!dienThoai.getModel().isBlank()){
                    tmp.setModel(dienThoai.getModel());
                }
                return dienThoaiRepository.save(tmp);
            }
            return dienThoaiRepository.save(dienThoai);
    }

    public List<DienThoai> getAll(){
        return this.dienThoaiRepository.findAll();
    }

    public boolean deleteById(Long id){
        Optional<DienThoai> byId = dienThoaiRepository.findById(id);
        if(byId.isPresent()){
            this.dienThoaiRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DienThoai> getById(Long id) {
        Optional<DienThoai> byId = this.dienThoaiRepository.findById(id);
        if(!byId.isPresent()){
            throw new BadRequestException("id điện thoại không tồn tại");
        }
        return Arrays.asList(byId.get());
    }
    public List<DienThoai> search(String keyword){
        return this.dienThoaiRepository.findAllByTen(keyword);
    }
}
