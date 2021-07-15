package nguyenhuuhung.b17dccn298.service;

import nguyenhuuhung.b17dccn298.Repository.KhachHangRepository;
import nguyenhuuhung.b17dccn298.error.BadRequestException;
import nguyenhuuhung.b17dccn298.model.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    public KhachHang createOrUpdate(KhachHang khachHang){
        if(khachHang.getMaKhachHang()!=null){
            Optional<KhachHang> byId = khachHangRepository.findById(khachHang.getMaKhachHang());
            if(!byId.isPresent()){
                throw new BadRequestException("id không tồn tại!");
            }
            KhachHang tmp = byId.get();
            if(khachHang.getHoTen()!=null&&!khachHang.getHoTen().isBlank()){
                tmp.setHoTen(khachHang.getHoTen());
            }
            if(khachHang.getDiaChi()!=null&&!khachHang.getDiaChi().isBlank()){
                tmp.setDiaChi(khachHang.getDiaChi());
            }
            if(khachHang.getNhomKhachHang()!=null&&!khachHang.getNhomKhachHang().isBlank()){
                tmp.setNhomKhachHang(khachHang.getNhomKhachHang());
            }
            return khachHangRepository.save(tmp);
        }
        return khachHangRepository.save(khachHang);
    }

    public List<KhachHang> getAll(){
        return this.khachHangRepository.findAll();
    }

    public boolean deleteById(Long id){
        Optional<KhachHang> byId = this.khachHangRepository.findById(id);
        if(byId.isPresent()){
            this.khachHangRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
