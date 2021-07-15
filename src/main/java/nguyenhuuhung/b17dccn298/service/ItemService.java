package nguyenhuuhung.b17dccn298.service;

import nguyenhuuhung.b17dccn298.Repository.ItemRepository;
import nguyenhuuhung.b17dccn298.error.BadRequestException;
import nguyenhuuhung.b17dccn298.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item createOrUpdate(Item item){
            if(item.getId()!=null){
                Optional<Item> byId = itemRepository.findById(item.getId());
                if(!byId.isPresent()){
                    throw new BadRequestException("id không tồn tại!");
                }
                Item tmp = byId.get();
                if(item.getGiaBan()!=null){
                    tmp.setGiaBan(item.getGiaBan());
                }
                if(item.getSoLuong()!=null){
                    tmp.setSoLuong(item.getSoLuong());
                }
                return itemRepository.save(tmp);
            }
            return itemRepository.save(item);
    }

    public List<Item> getAll(){
        return this.itemRepository.findAll();
    }

    public boolean deleteById(Long id){
        Optional<Item> byId = itemRepository.findById(id);
        if(byId.isPresent()){
            this.itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
