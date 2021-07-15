package nguyenhuuhung.b17dccn298.service;

import nguyenhuuhung.b17dccn298.Repository.HoaDonRepository;
import nguyenhuuhung.b17dccn298.error.BadRequestException;
import nguyenhuuhung.b17dccn298.model.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    public HoaDon createOrUpdate(HoaDon hoaDon){
        if(hoaDon.getMaHoaDon()!=null){
            Optional<HoaDon> byId = this.hoaDonRepository.findById(hoaDon.getMaHoaDon());
            if(!byId.isPresent()){
                throw new BadRequestException("id không tồn tại!");
            }
            HoaDon tmp=byId.get();
            if(tmp.getKhachHang()!=null){
                tmp.setKhachHang(hoaDon.getKhachHang());
            }
            if(tmp.getThoiGianTaoHoaDon()!=null){
                tmp.setThoiGianTaoHoaDon(hoaDon.getThoiGianTaoHoaDon());
            }
            if(tmp.getItem()!=null){
                tmp.setItem(hoaDon.getItem());
            }
            return this.hoaDonRepository.save(tmp);
        }
        return this.hoaDonRepository.save(hoaDon);
    }

    public List<HoaDon> getAll(){
        return this.hoaDonRepository.findAll();
    }

    public boolean deleteByid(Long id){
        Optional<HoaDon> byId = this.hoaDonRepository.findById(id);
        if(byId.isPresent()){
            this.hoaDonRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
