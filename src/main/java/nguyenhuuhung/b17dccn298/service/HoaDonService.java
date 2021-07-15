package nguyenhuuhung.b17dccn298.service;

import nguyenhuuhung.b17dccn298.Repository.HoaDonRepository;
import nguyenhuuhung.b17dccn298.Repository.ItemRepository;
import nguyenhuuhung.b17dccn298.error.BadRequestException;
import nguyenhuuhung.b17dccn298.model.HoaDon;
import nguyenhuuhung.b17dccn298.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private ItemRepository itemRepository;

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
            HoaDon save = this.hoaDonRepository.save(tmp);
            saveItem(hoaDon.getItem(),save.getMaHoaDon());
            return this.getById(save.getMaHoaDon());
        }
        HoaDon save = this.hoaDonRepository.save(hoaDon);
        saveItem(hoaDon.getItem(),save.getMaHoaDon());
        return this.getById(save.getMaHoaDon());
    }
    public void saveItem(List<Item> items,Long maHoaDon){
        if(!items.isEmpty())
            for(int i=0;i<items.size();i++){
                Item item = items.get(i);
                item.setId(null);
                item.setHoaDon(maHoaDon);
                System.out.println("----------");
                System.out.println(item);
                Item save = this.itemRepository.save(item);
                System.out.println(save);
            }
    }
    public List<HoaDon> getAll(){
        return this.hoaDonRepository.findAll();
    }
    public HoaDon getById(Long id){
        Optional<HoaDon> byId = this.hoaDonRepository.findById(id);
        if(!byId.isPresent()){
            throw new BadRequestException("id không tồn tại!");
        }
        return byId.get();
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
